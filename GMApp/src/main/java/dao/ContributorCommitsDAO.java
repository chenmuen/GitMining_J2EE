package dao;

import entity.ContributorCommits;

import java.util.List;

/**
 * Created by chenm on 2016/5/11.
 */
public interface ContributorCommitsDAO extends BaseDAO<ContributorCommits> {
    /**
     * 根据仓库名返回一个贡献者提交记录的列表（统计用）
     * @param repoName-仓库名
     * @return List<ContributorCommits>
     */
    public List<ContributorCommits> getCommits(String repoName);
}
