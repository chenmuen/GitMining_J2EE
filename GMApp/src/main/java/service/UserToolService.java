package service;

import entity.User;
import vo.UserVO;

import java.util.List;

/**
 * Created by raychen on 16/5/26.
 */
public interface UserToolService {

    /**
     * 转换po -- vo
     * @param pos
     * @return
     */
    public List<UserVO> toVOList(List<User> pos);
}
