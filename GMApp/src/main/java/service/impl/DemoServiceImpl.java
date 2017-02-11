package service.impl;

import dao.DemoDAO;
import entity.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DemoService;
import vo.DemoVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenm on 2016/5/6.
 */
@Service
@Transactional
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDAO demoDao;

    public void saveUsers(List<DemoVO> vos) {
        for (DemoVO vo : vos) {
            DemoEntity entity = new DemoEntity();
            entity.setUsername(vo.username);
            demoDao.save(entity);
        }
    }

    public List<DemoVO> getAllUsernames() {
        List<DemoEntity> demoEntities = demoDao.findAll();
        List<DemoVO> demoVOList = new ArrayList<DemoVO>();
        for (DemoEntity demoEntity : demoEntities) {
            DemoVO vo = new DemoVO();
            vo.username = demoEntity.getUsername();
            vo.id = demoEntity.getId();
            demoVOList.add(vo);
        }

        return demoVOList;
    }
}
