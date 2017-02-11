package dao.impl;

import dao.DemoDAO;
import entity.DemoEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenm on 2016/5/6.
 */
@Repository
public class DemoDAOImpl implements DemoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public int save(DemoEntity u) {
        return (Integer) sessionFactory.getCurrentSession().save(u);
    }

    public List<DemoEntity> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DemoEntity.class);
        return criteria.list();
    }
}
