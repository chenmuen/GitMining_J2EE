package service;

import vo.DemoVO;

import java.util.List;

/**
 * Created by chenm on 2016/5/6.
 */
public interface DemoService {
    public void saveUsers(List<DemoVO> vos);
    public List<DemoVO> getAllUsernames();
}
