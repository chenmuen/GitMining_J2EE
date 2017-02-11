package daotest;

import base.BaseTest;
import dao.CommitDAO;
import dao.LanguageDAO;
import dao.UserDAO;
import entity.Language;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.enums.UserSortType;

import java.util.List;

/**
 * Created by chenm on 2016/5/12.
 */
public class UserDAOTest extends BaseTest {
    @Autowired
    UserDAO userDAO;

    @Autowired
    CommitDAO commitDAO;

    @Autowired
    LanguageDAO languageDAO;

    @Test
    public void testGetRepos() {
//        List<String> repos = userDAO.getRepoNamesOfUser("JackDanger");
//        List<Object> names = userDAO.getPropertyList("starredCount", true);
//        for (Object name : names) {
//            System.out.println(name.toString());
//        }
//        System.out.println(names.size());

//        List<Object> commits = commitDAO.getPropertyListByColumn("commits", "repoFullName", "mojombo/god", true);
//        for (Object commit : commits) {
//            System.out.println(commit.toString());
//        }

        List<Language> languages = languageDAO.getLanguageByUser("mojombo");
        for (Language language : languages) {
            System.out.println(language.getRepoFullName()+" "+language.getLanguage());
        }
    }

    @Test
    public void testScreen() {
        System.out.println(userDAO.getUserList("", UserSortType.REPO, "Ruby", 2015, 1).get(0));
    }

    @Test
    public void testGetReposList() {
        System.out.println(userDAO.getRepoOfUser("mojombo"));
    }

    @Test
    public void getByLangTest() {
        System.out.println(userDAO.getUserListByLanguage("Java", 1, 5));
    }
}
