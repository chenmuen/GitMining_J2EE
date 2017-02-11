package service;

import entity.Repo;
import vo.RepoVO;

import java.util.List;

/**
 * Created by raychen on 16/5/26.
 */
public interface RepoToolService {

    /**
     * 转换po--vo
     * @param repos
     * @return
     */
    public List<RepoVO> toVOList(List<Repo> repos);
}
