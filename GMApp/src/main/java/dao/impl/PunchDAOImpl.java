package dao.impl;

import dao.PunchDAO;
import entity.Punch;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenm on 2016/5/11.
 */
@Repository
public class PunchDAOImpl extends BaseDAOImpl<Punch> implements PunchDAO {
    public List<Punch> getPunchCard(String repoName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Punch.class);
        criteria.add(Restrictions.eq("repoFullName", repoName));
        criteria.addOrder(Order.asc("day")).addOrder(Order.asc("hour"));

        return criteria.list();
    }
}
