package service;

import vo.*;

import java.util.List;

/**
 * Created by raychen on 16/5/11.
 */
public interface UserStatisticService {

    /**
     * 雷达图
     * @param userName
     * @return
     */
    public List<RadarVO> getRadar(String userName);

    /**
     * 单个用户语言
     * @param userName
     * @return
     */
    public List<LanguageStaVO> getUserLanguage(String userName);

    /**
     * 单个用户贡献率
     * @param userName
     * @return
     */
    public List<RateVO> getContributeRates(String userName);

    /**
     * 单个用户commit
     * @param userName
     * @return
     */
    public List<CommitVO> getCommits(String userName);

    /**
     * 用户公司
     * @return
     */
    public List<RateVO> getCompanyRates();

    /**
     * 用户类型
     * @return
     */
    public List<RateVO> getTypeRates();

    /**
     * 用户仓库
     * @return
     */
    public List<SectorVO> getRepoSector();

    /**
     * 用户gists
     * @return
     */
    public List<SectorVO> getGistSector();

    /**
     * 用户follower
     * @return
     */
    public List<SectorVO> getFollowerSector();

    /**
     * 用户following
     * @return
     */
    public List<SectorVO> getFollowingSector();

    /**
     * 用户创建时间
     * @return
     */
    public List<SectorVO> getCreateAtSector();
}
