package service.impl.languageimpl;

import controller.uitities.CommonUtil;
import dao.LanguageDAO;
import dao.SingleLanguageDAO;
import entity.LangCommon;
import entity.Quote;
import entity.SingleLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.LanguageService;
import service.LanguageStatisticService;
import service.evaluate.Rank;
import service.utilities.CommonLists;
import util.*;
import vo.LanguageVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/6/3.
 */
@Service
@Transactional
public class LanguageStatImpl implements LanguageStatisticService {

    private static final int topSize = 5;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private SingleLanguageDAO singleLanguageDAO;
    @Autowired
    private LanguageDAO languageDAO;

    @Override
    public List<LanguageVO> getTopLangsByTag(String tag) {
        List<LanguageVO> languageVOs = languageService.toVOList(singleLanguageDAO.getLanguageListByTag(tag));
        Rank.rankLanguage(languageVOs, -1);
        return languageVOs.stream().limit(topSize).collect(Collectors.toList());
    }

    @Override
    public List<LanguageTrends> getAllLangTrends() {
        List<LanguageVO> languageVOs = languageService.getAll();
        List<LanguageTrends> ret = new LinkedList<>();
        for (int i = 0; i < 11; i++) {
            String name = languageVOs.get(i).languageName;
            LanguageTrends trends = languageService.getTrends(name);
            ret.add(trends);
        }
        return ret;
    }

    @Override
    public Map<Integer, String> getHallOfFame() {
        Map<Integer, String> maps = new HashMap<>();
        maps.put(2008,"C");
        maps.put(2009,"Go");
        maps.put(2010,"Python");
        maps.put(2011,"Objective-C");
        maps.put(2012,"Objective-C");
        maps.put(2013,"JavaScript");
        maps.put(2014,"JavaScript");
        maps.put(2015,"Java");
        maps.put(2007,"Python");
        maps.put(2016,"Java");
//        Map<Integer, List<LanguageVO>> rankedAll = CommonUtil.rankedLangs!=null?CommonUtil.rankedLangs:CommonUtil.getRankedLangs(languageService.getAll(), languageService.getAllYearStats());
//        String[] years = CommonLists.years;
//        for (int i = 1; i < years.length; i++) {
//            int year = Integer.parseInt(years[i]);
//            if (rankedAll.containsKey(year)){
//                maps.put(year, rankedAll.get(year).get(0).languageName);
//            }
//        }
        return maps;
    }

    @Override
    public String[] getRandomQuote() {
        String[] ret = new String[2];
        Quote quote = singleLanguageDAO.getQuote();
        ret[0] = quote.getContent();
        ret[1] = quote.getSource();
        return ret;
    }

    @Override
    public LanguageRelation getAllRelations() {
        LanguageRelation relation = new LanguageRelation();
        List<LanguageVO> allLanguages_before = languageService.getAll();
        List<LanguageVO> allLanguages = allLanguages_before.stream()
                .filter(languageVO -> !languageVO.languageName.equals("Shell"))
                .limit(150).collect(Collectors.toList());
        for (int i = 0; i < allLanguages.size(); i++) {
            LanguageVO singleLanguage = allLanguages.get(i);
            relation.categories.add(singleLanguage.languageName);
            relation.values.add(new RelationSingleNode(singleLanguage.languageName,singleLanguage.repos,i,singleLanguage.repos));
        }

        List<LangCommonNode> nodes = allLanguages.stream()
                .map(singleLanguage -> new LangCommonNode(singleLanguage.languageName))
                .collect(Collectors.toList());
        List<String> indexs = new LinkedList<>();
        for (int i = 0; i < nodes.size(); i++) indexs.add(nodes.get(i).name);
        List<LangCommon> allCommon = languageDAO.getAllCommonRepoCount().stream()
                .filter(langCommon -> {
                    int i1 = indexs.indexOf(langCommon.getLang1());
                    int i2 = indexs.indexOf(langCommon.getLang2());
                    return i1>=0&&i2>=0;
                }).collect(Collectors.toList());

        //
        List<LangCommonLine> lines = new LinkedList<>();
        for (int i = 0; i < allCommon.size(); i++) {
            LangCommon langCommon = allCommon.get(i);
            int i1 = indexs.indexOf(langCommon.getLang1());
            int i2 = indexs.indexOf(langCommon.getLang2());
            LangCommonNode node1 = nodes.get(i1);
            LangCommonNode node2 = nodes.get(i2);
            lines.add(new LangCommonLine(node1, node2, -langCommon.getCommonRepoNum(), node1.v));
            node1.v = i;
        }
        getLinks(relation.links, nodes, lines);
        //

        boolean filtered = false;
        while (!filtered){
            int max = relation.values.stream().mapToInt(node -> node.value).max().getAsInt();
            if (max > 400) relation.values.forEach(node->{
                if (node.value>15) node.value/=2;
            });
            else if (max>150) relation.values.forEach(node->{
                if (node.value>7) node.value/=1.05;
            });
            else filtered = true;
        }
        relation.values.forEach(node -> node.symbolSize=node.value);
        return relation;
    }
    
    private void getLinks(List<RelationLine> links, List<LangCommonNode> nodes, List<LangCommonLine> allLines){
        boolean find;
        int c = 0;
        do {
            System.out.println("times: "+(c++));
            find = false;
            nodes.forEach(langCommonNode -> langCommonNode.father = langCommonNode);
            List<LangCommonLine> lines = new LinkedList<>();
            int n = 0;
            for (int i = 0; i < nodes.size(); i++) {
                if (!nodes.get(i).visited){
                    find = true;
                    n = dfs(nodes.get(i), lines, allLines);
                    break;
                }
            }
            System.out.println(n+" "+lines.size());
            if (find){
                Collections.sort(lines, (o1, o2) -> o1.value-o2.value);
                int count=0, i=0;
                while (count < n-1){
                    LangCommonLine line = lines.get(i);
                    LangCommonNode xfather = getFather(line.x);
                    LangCommonNode yfather = getFather(line.y);
                    if (!xfather.name.equals(yfather.name)){
                        System.out.println(line.x.name+" and "+line.y.name);
                        links.add(new RelationLine(line.x.name, line.y.name));
                        count++;
                        xfather.father = yfather;
                    }
                    i++;
                }
            }
        }while (find);
    }

    private LangCommonNode getFather(LangCommonNode node){
        if (!node.father.name.equals(node.name)) node.father = getFather(node.father);
        return node.father;
    }
    
    private int dfs(LangCommonNode node, List<LangCommonLine> lines, List<LangCommonLine> all){
        node.visited = true;
        int i = node.v;
        int sum=0;
        while (i != -1){
            LangCommonLine line = all.get(i);
            LangCommonLine newLine = new LangCommonLine(line.x, line.y, line.value, line.next);
            lines.add(newLine);
            if (!line.y.visited) {
                sum+=dfs(line.y, lines, all);
            }
            i = line.next;
        }
        return sum+1;
    }

}
