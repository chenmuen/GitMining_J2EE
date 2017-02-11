package dao;


import entity.Commit;

import java.util.List;

/**
 * Created by chenm on 2016/5/12.
 */
public interface CommitDAO extends BaseDAO<Commit>{

    public List<Commit> getCommitsOfContributor(String repoName, String contributorLogin);

    public List<Commit> getCommitsByRepo(String repoName);

    public List<Commit> getCommitByUser(String userLogin);
}
