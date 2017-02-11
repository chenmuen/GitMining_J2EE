package servicetest;

import base.BaseTest;
import dao.LanguageDAO;
import dao.SingleLanguageDAO;
import entity.Language;
import entity.RecommendSite;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.LanguageService;
import service.LanguageStatisticService;
import service.RepoService;
import service.evaluate.Rank;
import util.LanguageRelation;
import util.enums.LangTrendsTypeByYear;
import vo.LanguageStaVO;
import vo.LanguageVO;
import vo.RepoVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/6/16.
 */
public class LangFinalTest extends BaseTest{

    @Autowired
    private LanguageService languageService;
    @Autowired
    private SingleLanguageDAO singleLanDao;
    @Autowired
    private LanguageDAO lanDao;
    @Autowired
    private LanguageStatisticService langStat;

    @Test
    public void name() throws Exception {
//        int size1 = lanDao.getRepoByLanguage("Java").size();
//        int size2 = lanDao.getRepoByLanguage("JavaScript").size();
//        int size3 = lanDao.getRepoByLanguage("Ruby").size();
//        int size4 = lanDao.getRepoByLanguage("Scala").size();
//        int t1 = languageService.getStatByTime("Java", 2011, LangTrendsTypeByYear.STARS);
//        int t2 = languageService.getStatByTime("Html", 2011, LangTrendsTypeByYear.STARS);

//        System.out.println(t1+" "+t2);
        List<RepoVO> relatedVOs = languageService.getMostRelatedRepos("HTML");
        for (RepoVO vo: relatedVOs) {
            List<Language> langs = lanDao.getLanguage(vo.fullName);
            List<Language> my = langs.stream().filter(language -> language.getLanguage().equals("HTML")).collect(Collectors.toList());
            System.out.println((double)my.get(0).getSize() / langs.stream().mapToLong(language -> language.getSize()).sum());
        }
    }

    @Test
    public void rankLang() throws Exception {
        List<LanguageVO> allLang = languageService.getAll();
        Rank.rankLanguage(allLang, -1);
        for (int i = 0; i < 100; i++) {
            System.out.println(allLang.get(i).languageName+" "+allLang.get(i).score.final_score);
        }
    }

    @Test
    public void relation() throws Exception {
        LanguageRelation allRelation = langStat.getAllRelations();
        System.out.println(allRelation.links.size()+" "+allRelation.categories.size());
    }

    @Test
    public void testdao() throws Exception {
//        List<LanguageStaVO> all = languageServic
    }

    @Test
    public void setScore() throws Exception {
        List<LanguageVO> allvo = languageService.getAll();
        allvo.forEach(languageVO -> languageVO.single.setScore((int)(languageVO.score.final_score*10000)));
    }

    @Test
    public void finall() throws Exception {
        List<RecommendSite> sites = singleLanDao.getRecommendSite("JavaScript");
        System.out.println(sites.size());
    }

    @Test
    public void getbyyear() throws Exception {
        String[] fortest = {"Java", "JavaScript", "C", "C++", "PHP", "Python", "CSS", "Shell", "HTML"};
        for (int i = 2009; i < 2016; i++) {
            System.out.println(i);
            for (int j = 0; j < fortest.length; j++) {
                String lang = fortest[j];
                long k = languageService.getSumRepoByYear(lang, i);
                Map map = languageService.getStatByTime(lang, LangTrendsTypeByYear.SCORE);
                long d1 = (long) map.get(i);
                System.out.print(lang+"!: ");
                System.out.println((double)d1/k);
            }
            System.out.println();
        }
    }

    @Test
    public void sumperyear() throws Exception {
        Map map = languageService.getStatByTime("Ruby", LangTrendsTypeByYear.SCORE);
        System.out.println((long)map.get("score"));
    }

    @Test
    public void test22() throws Exception {
        List<LanguageVO> apps = languageService.toVOList(singleLanDao.getLanguageListByTag("Mobile Application"));
        System.out.println(apps.size());
    }
}
