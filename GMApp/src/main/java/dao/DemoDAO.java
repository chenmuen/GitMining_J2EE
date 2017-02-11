package dao;

import entity.DemoEntity;

import java.util.List;

/**
 * Created by chenm on 2016/5/6.
 */
public interface DemoDAO {
    public int save(DemoEntity u);
    public List<DemoEntity> findAll();
}
