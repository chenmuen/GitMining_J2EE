package daotest;

import base.BaseTest;
import dao.LanguageDAO;
import dao.RepoDAO;
import entity.Language;
import entity.Repo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenm on 2016/5/26.
 */
public class LanguageDAOTest extends BaseTest {
    @Autowired
    LanguageDAO languageDAO;

    @Autowired
    RepoDAO repoDAO;

    @Test
    public void getLanguageTest() {
        List<String> repos = new ArrayList<>();
    }

    @Test
    public void getAllLanguageTest() {
        List<String> languages = languageDAO.getAllLanguage();
        for (String language : languages) {
            System.out.println(language);
        }
    }

    @Test
    public void getLanguageListTest() {
        List<String> repos = new ArrayList<>();

        List<Repo> repoList = repoDAO.getAllByPage(1,20000);

        for (Repo repo : repoList) {
            repos.add(repo.getFullName());
        }

        List<Language> language = languageDAO.getLanguage(repos);

        System.out.println(language);
    }

    @Test
    public void getAllTest() {
        System.out.println(languageDAO.getAll());
    }

    @Test
    public void getRepoByTwoLanguageTest() {
//        System.out.println(languageDAO.getRepoByTwoLanguage("HTML", "CSS").size());
        System.out.println(languageDAO.getCommonRepoCount("Java", "JavaScript"));
    }

    @Test
    public void getSumTest() {
        Map sums = languageDAO.getPropertySum("Ruby", "stars_count", "score").get(1);

        System.out.println("year:"+sums.get("year") + " stars_count:" + sums.get("stars_count") + " issues_count:" + sums.get("score"));

        sums = languageDAO.getPropertySum("Java", "stars_count", "forks_count").get(2);

        System.out.println("year:"+sums.get("year") + " stars_count:" + sums.get("stars_count") + " fork_count:" + sums.get("forks_count"));
    }

    @Test
    public void getSizeTest() {
        List<Map> maps = languageDAO.getSize();
        for (Map map : maps) {
            System.out.println(map.get("language")+" "+ map.get("size"));
        }
    }

    @Test
    public void getRepoPerYearTest() {
        System.out.println(languageDAO.getLanguageSumPerYear("JavaScript"));
    }

}
