package daotest;

import base.BaseTest;
import dao.PunchDAO;
import entity.Punch;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chenm on 2016/5/11.
 */
public class PunchDAOTest extends BaseTest {
    @Autowired
    PunchDAO punchDAO;

    @Test
    public void test(){
        List<Punch> punches = punchDAO.getPunchCard("mojombo/grit");

        System.out.println(punches);
    }
}
