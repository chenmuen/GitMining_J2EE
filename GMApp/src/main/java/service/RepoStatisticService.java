package service;

import vo.*;

import java.util.List;

/**
 * Created by raychen on 16/5/12.
 */
public interface RepoStatisticService {

    /**
     * 获取雷达图数据
     * @param repoName
     * @return
     */
    public List<RadarVO> getRadar(String repoName);

    /**
     * 饼图,语言占比
     * @param repoName
     * @return
     */
    public List<LanguageStaVO> getRepoLanguage(String repoName);


    /**
     * 代码行数增减
     * @param repoName
     * @return
     */
    public List<CommitVO> getCommitsRepo(String repoName);

    /**
     * 仓库每个用户commit
     * @param repoName
     * @return
     */
    public List<ContributorCommitsVO> getCommitsContributors(String repoName);

    /**
     * Punch card
     * @param repoName
     * @return
     */
    public List<PunchVO> getPunches(String repoName);

    /**
     * 所有仓库语言分布
     * @return
     */
    public List<LanguageStaVO> getAllLanguage();

    /**
     * 所有仓库创建时间分布
     * @return
     */
    public List<SectorVO> getReposCreateAt();

    /**
     * 所有仓库star分布
     * @return
     */
    public List<SectorVO> getReposStar();

    /**
     * 所有仓库fork分布
     * @return
     */
    public List<SectorVO> getReposFork();

    /**
     * 所有仓库contributor分布
     * @return
     */
    public List<SectorVO> getRepoContritutor();

    /**
     * 所有仓库collaborator分布
     * @return
     */
    public List<SectorVO> getRepoCollaborator();
}
