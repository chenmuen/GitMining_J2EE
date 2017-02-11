package service.impl.languageimpl;

import controller.uitities.CommonUtil;
import dao.LanguageDAO;
import dao.RepoDAO;
import dao.SingleLanguageDAO;
import dao.UserDAO;
import entity.*;
import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.*;
import service.evaluate.Evaluate;
import service.evaluate.Rank;
import service.utilities.CommonLists;
import util.LanguageRelation;
import util.LanguageTrends;
import util.RelationLine;
import util.RelationSingleNode;
import util.enums.LangSortType;
import util.enums.LangTrendsTypeByYear;
import vo.LanguageJoinVO;
import vo.LanguageVO;
import vo.RepoVO;
import vo.UserVO;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/22.
 */
@Service
@Transactional
public class LanguageImpl implements LanguageService{

    private static final int maxRelatedSize = 5;
    private static final int maxRelationSize = 10;
    private static final int initValue = 100;

    @Autowired
    private LanguageDAO languageDAO;
    @Autowired
    private UserToolService userToolService;
    @Autowired
    private RepoToolService repoToolService;
    @Autowired
    private SingleLanguageDAO singleLanguageDAO;
    @Autowired
    private RepoDAO repoDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<LanguageVO> toVOList(List<SingleLanguage> languages){
        List<LanguageVO> vos = new LinkedList<>();
        languages.forEach(language -> {
            LanguageVO languageVO = new LanguageVO(language,
                    singleLanguageDAO.getTagList(language.getLanguage(), false),
                    singleLanguageDAO.getTagList(language.getLanguage(), true));
            Quote quote = singleLanguageDAO.getQuote(language.getLanguage());
            if (quote!=null) {
                languageVO.quoteContent = quote.getContent();
                languageVO.quoteFrom = quote.getSource();
            }
            vos.add(languageVO);
        });
        return vos;
    }

    @Override
    public LanguageVO getLanguage(String languageName) {
        SingleLanguage singleLanguage = singleLanguageDAO.getLanguage(languageName);
        LanguageVO language = new LanguageVO(singleLanguage,
                singleLanguageDAO.getTagList(languageName, false),
                singleLanguageDAO.getTagList(languageName, true));
        Quote quote = singleLanguageDAO.getQuote(languageName);
        if (quote!=null) {
            language.quoteContent = quote.getContent();
            language.quoteFrom = quote.getSource();
        }
        return language;
    }

    @Override
    public List<RepoVO> getMostRelatedRepos(String languageName) {
        List<RepoVO> relatedRepos = repoToolService.toVOList(repoDAO.getRepoListByLanguage(languageName, 1, maxRelatedSize));
        return relatedRepos;
    }

    @Override
    public List<UserVO> getMostRelatedUsers(String languageName) {
        List<UserVO> users = userToolService.toVOList(userDAO.getUserListByLanguage(languageName, 1, maxRelatedSize));
        return users;
    }

    @Override
    public List<LanguageVO> getAll() {
        List<LanguageVO> vos = toVOList(singleLanguageDAO.getAll());
        Map<Integer, List<LanguageVO>> langs = CommonUtil.rankedLangs!=null?CommonUtil.rankedLangs:CommonUtil.getRankedLangs(vos, getAllYearStats());
        return langs.get(-1);
    }

    @Override
    public List<LanguageVO> showLangs(String keywords, int page, LangSortType sortType, String lang_category, String lang_app) {
        return toVOList(singleLanguageDAO.getLanguageList(keywords!=null?keywords:"", lang_category!=null?lang_category:"", lang_app!=null?lang_app:"", sortType, page));
    }

    @Override
    public int getPage(String keywords, String lang_category, String lang_app) {
        return singleLanguageDAO.getPages(keywords, lang_category!=null?lang_category:"", lang_app!=null?lang_app:"");
    }

    @Override
    public List<String> getAllTag(boolean isApp) {
        return singleLanguageDAO.getAllTag(isApp);
    }

    @Override
    public LanguageTrends getTrends(String langName) {
        LanguageTrends trends = new LanguageTrends(langName);
        Map<Integer, List<LanguageVO>> rankedAll;
        if (CommonUtil.rankedLangs != null) rankedAll = CommonUtil.rankedLangs;
        else rankedAll = CommonUtil.getRankedLangs(getAll(), getAllYearStats());
        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            int year = Integer.parseInt(years[i]);
            if (rankedAll.containsKey(year)){
                List<LanguageVO> vos = rankedAll.get(year).stream()
                        .filter(languageVO -> languageVO.languageName.equals(langName))
                        .collect(Collectors.toList());
                if (vos.size()==0) trends.rates.put(year, 0);
                else trends.rates.put(year, vos.get(0).scoreOfYear.get(year));
            }
        }
        return trends;
    }

    @Override
    public Map<Integer, Long> getStatByTime(String langName, LangTrendsTypeByYear type) {
        Map<Integer, Long> ret = new HashMap<>();
        switch (type){
            case REPOS: {
                List<Map> maps = languageDAO.getPropertySum(langName, "size");
                for (Map map: maps) {
                    BigDecimal value = (BigDecimal) map.get("size");
//                    System.out.println(value.longValue());
                    ret.put((int) map.get("year"), value.longValue());
                }
                break;
            }
            case STARS: {
                List<Map> maps = languageDAO.getPropertySum(langName, "stars_count");
                for (Map map: maps) {
                    BigDecimal value = (BigDecimal) map.get("stars_count");
//                    System.out.println(value.longValue());
                    ret.put((int) map.get("year"), value.longValue());
                }
                break;
            }
            case FORKS: {
                List<Map> maps = languageDAO.getPropertySum(langName, "forks_count");
                for (Map map: maps) {
                    BigDecimal value = (BigDecimal) map.get("forks_count");
                    ret.put((int) map.get("year"), value.longValue());
                }
                break;
            }
            case ISSUES: {
                List<Map> maps = languageDAO.getPropertySum(langName, "issues_count");
                for (Map map: maps) {
                    BigDecimal value = (BigDecimal) map.get("issues_count");
                    ret.put((int) map.get("year"), value.longValue());
                }
                break;
            }
            case WATCHES: {
                List<Map> maps = languageDAO.getPropertySum(langName, "subscribers_count");
                for (Map map: maps) {
                    BigDecimal value = (BigDecimal) map.get("subscribers_count");
                    ret.put((int) map.get("year"), value.longValue());
                }
                break;
            }
            case SCORE: {
                List<Map> maps = languageDAO.getPropertySum(langName, "score");
                for (Map map: maps) {
                    BigDecimal value = (BigDecimal) map.get("score");
                    ret.put((int) map.get("year"), value.longValue());
                }
                break;
            }
        }
        return ret;
    }

    @Override
    public Map<Integer, List<LanguageYearStat>> getAllYearStats() {
        Map<Integer, List<LanguageYearStat>> allStats = new HashMap<>();
        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            int year = Integer.parseInt(years[i]);
            List<LanguageYearStat> stats = singleLanguageDAO.getLanguageStatByYear(year);
            allStats.put(year, stats);
        }
        return allStats;
    }

    @Override
    public List<String> getTagsByLanguage(String langName) {
        List<String> tags = singleLanguageDAO.getTagList(langName, false);
        List<String> tags_app = singleLanguageDAO.getTagList(langName, true);
        tags.addAll(tags_app);
        return tags;
    }

    @Override
    public LanguageRelation relatedNodes(String langName) {
        LanguageRelation relation = new LanguageRelation();
        relation.categories.add(langName);
        relation.values.add(new RelationSingleNode(langName, initValue, 0, initValue));
        List<SingleLanguage> allLangs = singleLanguageDAO.getAll();
        List<RelationSingleNode> nodes = new LinkedList<>();
        allLangs.forEach(language -> {
            if (!language.getLanguage().equals(langName)){
                LangCommon common = languageDAO.getCommonRepoCount(langName, language.getLanguage());
                int common_repos = 0;
                if (common!=null) common_repos=common.getCommonRepoNum();
                RelationSingleNode node = new RelationSingleNode(language.getLanguage(), common_repos, -1, common_repos);
                nodes.add(node);
            }
        });
        Collections.sort(nodes, (o1, o2) -> o2.value-o1.value);
        List<RelationSingleNode> filteredNodes;
        if (nodes.size() > 10)  filteredNodes = nodes.stream().limit(maxRelationSize).collect(Collectors.toList());
        else filteredNodes = nodes;
        boolean filter = false;
        while (!filter){
            int max = filteredNodes.stream().mapToInt(node -> node.value).max().getAsInt();
            if (max > 200) filteredNodes.forEach(node->node.value/=2);
            else if (max>100) filteredNodes.forEach(node->node.value/=1.1);
            else filter = true;
        }
        filteredNodes.forEach(node -> {
            relation.categories.add(node.name);
            relation.values.add(node);
            relation.links.add(new RelationLine(langName, node.name));
        });
        relation.values.forEach(node -> {
            node.category = relation.categories.indexOf(node.name);
            node.symbolSize = node.value;
        });
        return relation;
    }

    @Override
    public List<LanguageJoinVO> toJoinList(List<Language> languages) {
        List<LanguageJoinVO> joinVOs = new LinkedList<>();
        languages.forEach(language -> {
            joinVOs.add(new LanguageJoinVO(language));
        });
        return joinVOs;
    }

    @Override
    public long getSumRepoByYear(String langName, int year) {
        List<Map> maps = languageDAO.getLanguageSumPerYear(langName);
        long sum = maps.stream().filter(map -> (int)map.get("year")==year)
                .mapToLong(map -> (long)map.get("sum")).sum();
        return sum;
    }

}
