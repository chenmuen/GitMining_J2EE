package daotest;

import base.BaseTest;
import dao.RepoDAO;
import dao.UserDAO;
import entity.Repo;
import entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.Constant;
import util.enums.RepoSortType;

import java.util.*;

/**
 * Created by chenm on 2016/5/10.
 */
public class RepoDAOTest extends BaseTest {
    @Autowired
    RepoDAO repoDAO;

    @Test
    public void test() {
//        List<Repo> ger = repoDAO.getRepoList("", RepoSortType.GENERAL, 1);
//        ger.forEach(repo -> System.out.println(repo.getFullName()));
//        System.out.println(repoDAO.getCounts());
//        System.out.println(repoDAO.getPages("",null,0,null));
    }

    @Test
    public void getAllTest() {
//        List<Repo> repo = repoDAO.getAll();
        List<Object> list = repoDAO.getPropertyList("starsCount", false);
        System.out.println();


    }

    @Test
    public void repoTest() {
        List<Repo> repos = repoDAO.getRepoList("", RepoSortType.GENERAL, 1);
        System.out.println(repos);
    }

    @Test
    public void screenTest() {
//        List<Repo> repos = repoDAO.getRepoList("", RepoSortType.GENERAL, "Web", 2008, "PHP", 1);
        System.out.println(repoDAO.getPages("", "Web", 2008, "PHP"));
    }

    @Test
    public void getTagsTest() {
        System.out.println(repoDAO.getRepoTags("macournoyer/thin"));
    }

    @Test
    public void getRepoByTagTest() {
        List<String> tags = new ArrayList<>();
        tags.add("Web");
        tags.add("Plugin");

        List<Repo> repos = repoDAO.getRepoListByTags(tags);
        for (Repo repo : repos) {
            System.out.println(repo.getFullName());
        }
        System.out.println(repos.size());
    }

    @Test
    public void getRankingTest() {
        System.out.println(repoDAO.getRanking("starsCount", 0));
        System.out.println(repoDAO.getRanking("starsCount", 5));
        System.out.println(repoDAO.getRanking("issuesCount", 5));
    }

    @Test
    public void getPropertyTest() {
        System.out.println(repoDAO.getPropertyMapList("starsCount", false, "forksCount", "issuesCount").get(0));
        System.out.println(repoDAO.getPropertyMapList("starsCount", false, "contributorsCount").get(0));
//        System.out.println(repoDAO.getPropertyList("starsCount", false, "star"));
//        System.out.println(repoDAO.getPropertyList("starsCount", false));
    }

    @Test
    public void getByLangTest() {
        System.out.println(repoDAO.getRepoListByLanguage("Java", 1, 3));
    }

    @Test
    public void getRandomlyTest() {
        System.out.println(repoDAO.getListRandomly("starsCount",40,20,10));
    }

}