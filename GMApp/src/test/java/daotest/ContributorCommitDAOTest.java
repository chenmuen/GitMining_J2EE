package daotest;

import base.BaseTest;
import dao.CommitDAO;
import dao.ContributorCommitsDAO;
import entity.Commit;
import entity.ContributorCommits;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by chenm on 2016/5/11.
 */
public class ContributorCommitDAOTest extends BaseTest {
    @Autowired
    ContributorCommitsDAO contributorCommitsDAO;

    @Autowired
    CommitDAO commitDAO;

    @Test
    public void test() {
        List<ContributorCommits> list = contributorCommitsDAO.getListByColumn("repoFullName", "mojombo/grit");

        System.out.println(list);
    }

    @Test
    public void commitTest() {
        List<Commit> list = commitDAO.getCommitsByRepo("mojombo/grit");
        System.out.println(list);
        Date date = new Date(list.get(0).getWeek());
        System.out.println(date);
    }
}
