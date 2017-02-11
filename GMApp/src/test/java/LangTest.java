import base.BaseTest;
import com.sun.org.apache.xpath.internal.operations.Quo;
import controller.uitities.CommonUtil;
import dao.LanguageDAO;
import dao.SingleLanguageDAO;
import entity.Language;
import entity.Quote;
import entity.Repo;
import entity.SingleLanguage;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.LanguageService;
import service.LanguageStatisticService;
import service.RepoService;
import service.evaluate.Evaluate;
import service.evaluate.Rank;
import service.utilities.CommonLists;
import util.LanguageRelation;
import util.LanguageTrends;
import util.enums.LangSortType;
import util.enums.LangTrendsTypeByYear;
import vo.LanguageVO;
import vo.RepoVO;
import vo.UserVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/6/4.
 */
public class LangTest extends BaseTest {

    private List<LanguageVO> allLang;

    @Autowired
    private LanguageService languageService;
    @Autowired
    private RepoService repoService;
    @Autowired
    private LanguageDAO languageDAO;
    @Autowired
    private LanguageStatisticService langStat;
    @Autowired
    private SingleLanguageDAO singleLanguageDAO;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void rankLang() throws Exception {
        allLang = languageService.getAll();
        List<RepoVO> repoVOs = repoService.getAll();
//        Evaluate.calculateLangSizeOfYear(allLang, repoVOs, languageDAO);
        Rank.rankLanguage(allLang, -1);
        allLang.forEach(languageVO -> System.out.println(languageVO.languageName+": "+languageVO.score.final_score));
    }

    @Test
    public void langRelated() throws Exception {
        List<UserVO> relatedRepos = languageService.getMostRelatedUsers("CSS");
        System.out.println(relatedRepos.size());
    }

    @Test
    public void getLang() throws Exception {
        LanguageVO lang = languageService.getLanguage("CSS");
        System.out.println(lang);
    }

    @Test
    public void testGetLang() throws Exception {
        Map<Integer, String> map = langStat.getHallOfFame();
        map.forEach((k,v) -> {
            System.out.println(k+" "+v);
        });
//        List<LanguageVO> allLangs = languageService.getAll();
////        allLangs.forEach(languageVO -> System.out.println(languageVO.languageName));
////        List<RepoVO> allRepos = repoService.getAll();
////        List<String> names = allRepos.stream().map(repoVO -> repoVO.fullName).collect(Collectors.toList());
//        System.out.println("before get lang2...");
////        List<Language> allLang2s = languageDAO.getLanguage(names);
//        System.out.println("before cal...");
//        Evaluate.calculateLangSizeOfYear(allLangs, languageDAO.getAll(), 2006);
//        allLangs.forEach(languageVO -> {
//            System.out.println(languageVO.languageName+": "+languageVO.reposOfYear.get(2006));
//        });
    }

    @Test
    public void showLine() throws Exception {
        List<LanguageVO> vos = languageService.showLangs("", 1, LangSortType.GENERAL, "", "");
        System.out.println(vos.size());
    }

    @Test
    public void langTrends() throws Exception {
        List<LanguageTrends> alltrends = langStat.getAllLangTrends();
        alltrends.forEach(trends -> {
            System.out.println(trends.langName);
        });
        System.out.println(alltrends.size());
//        LanguageTrends trends = languageService.getTrends("JavaScript");
//        trends.rates.forEach((k,v) -> {
//            System.out.println(k+" "+v);
//        });
    }

    @Test
    public void langByTime() throws Exception {
        Map<Integer, Long> data = languageService.getStatByTime("C", LangTrendsTypeByYear.STARS);
        Map<Integer, Long> data2 = languageService.getStatByTime("C", LangTrendsTypeByYear.REPOS);
    }

    @Test
    public void getAllLang() throws Exception {
        List<LanguageVO> allLangs = languageService.getAll();
        System.out.println(allLangs.size());
    }

    @Test
    public void lang_trends_con() throws Exception {
//        JSONObject obj = CommonUtil.getTrendsAll(langStat.getAllLangTrends());
//        System.out.println(obj.toJSONString());
//        LanguageTrends trends = languageService.getTrends("Html");
//        trends.rates.forEach((k,v)->{
//            System.out.println(k+" "+v);
//        });
        Map<Integer, List<LanguageVO>> map = CommonUtil.getRankedLangs(languageService.getAll(), languageService.getAllYearStats());
        List<LanguageVO> vos = map.get(2010);
        vos.forEach(languageVO -> System.out.println(languageVO.languageName));
        List<LanguageVO> html = vos.stream().filter(languageVO -> languageVO.languageName.equals("Html")).collect(Collectors.toList());
        System.out.println(html.size());
    }

    @Test
    public void lang_Tag() throws Exception {
        List<LanguageVO> vos = langStat.getTopLangsByTag("Server");
//        List<SingleLanguage> pos = singleLanguageDAO.getLanguageListByTag("Object-oriented");
        System.out.println(vos.size());
    }

    @Test
    public void relatedPair() throws Exception {
        LanguageRelation relation = languageService.relatedNodes("JavaScript");
        relation.values.forEach(relationSingleNode -> System.out.println(relationSingleNode.name+": "+relationSingleNode.value));
//        System.out.println(relation.links.size());
//        double sum = CommonLists.getRelationScore(100, 3);
//        System.out.println(sum);
//        List<LanguagePairRelation> pairs = languageService.getRelationsFrom("JavaScript");
//        System.out.println(pairs.size());
//        pairs.forEach(pair -> System.out.println(pair.target+": "+pair.common_tags.size()+" "+pair.common_repos.size()));
    }

    @Test
    public void relationAll() throws Exception {
        LanguageRelation all = langStat.getAllRelations();
        all.values.forEach(node -> System.out.println(node.name+": "+node.value));
//        List<Repo> repos = languageDAO.getRepoByTwoLanguage("CSS", "JavaScript");
//        System.out.println(repos.size());
    }

    @Test
    public void lang_repo() throws Exception {
        List<RepoVO> vos = languageService.getMostRelatedRepos("css");
        System.out.println(vos.size());
    }

    @Test
    public void getQuote() throws Exception {
        Quote quote = singleLanguageDAO.getQuote();
        System.out.println(quote.getContent()+" "+quote.getSource());
    }
}
