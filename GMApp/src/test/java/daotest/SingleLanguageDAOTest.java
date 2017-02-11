package daotest;

import base.BaseTest;
import dao.SingleLanguageDAO;
import entity.SingleLanguage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.enums.LangSortType;

import java.util.List;

/**
 * Created by chenm on 2016/6/6.
 */
public class SingleLanguageDAOTest extends BaseTest {
    @Autowired
    SingleLanguageDAO singleLanguageDAO;

    @Test
    public void getLanguageListTest() {
        System.out.println(singleLanguageDAO.getLanguageList("","Functional","",LangSortType.REPOS,2));
        System.out.println(singleLanguageDAO.getPages("","Functional",""));
    }

    @Test
    public void getTagListTest() {
        System.out.println(singleLanguageDAO.getTagList("Java", false));
    }

    @Test
    public void getListByTag() {
        System.out.println(singleLanguageDAO.getLanguageListByTag("Game"));
        System.out.println(singleLanguageDAO.getLanguageListByTag("Functional"));
    }

    @Test
    public void getTagListByTwoLanguageTest() {
        System.out.println(singleLanguageDAO.getTagListByTwoLanguage("Java", "C++"));
    }

    @Test
    public void getLanguageStatTest() {
        System.out.println(singleLanguageDAO.getLanguageStatByYear(2015));
    }

    @Test
    public void getQuoteTest() {
        System.out.println(singleLanguageDAO.getQuote());
        System.out.println(singleLanguageDAO.getQuote(""));
    }

    @Test
    public void getCourseTest() {
        System.out.println(singleLanguageDAO.getRecommendCourse("C++"));
    }

    @Test
    public void getSiteTest() {
        System.out.println(singleLanguageDAO.getRecommendSite("JavaScript"));
    }
}
