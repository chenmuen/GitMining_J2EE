package dao.impl;

import dao.ContributorCommitsDAO;
import entity.ContributorCommits;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenm on 2016/5/11.
 */
@Repository
public class ContributorCommitsDAOImpl extends BaseDAOImpl<ContributorCommits> implements ContributorCommitsDAO {

    public List<ContributorCommits> getCommits(String repoName) {
        List<ContributorCommits> contributorCommitses = getListByColumn("repoFullName", repoName);
        return contributorCommitses;
    }
}
