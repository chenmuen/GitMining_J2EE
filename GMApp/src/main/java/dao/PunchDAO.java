package dao;

import entity.Punch;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenm on 2016/5/11.
 */
public interface PunchDAO extends BaseDAO<Punch>{
    /**
     * 根据仓库名返回该仓库的打卡记录的列表（统计用）
     * @param repoName-仓库名
     * @return List<PunchPO>-大小为7*24的List，按照周日到周六，0点到23点依次排列
     */
    public List<Punch> getPunchCard(String repoName);

}
