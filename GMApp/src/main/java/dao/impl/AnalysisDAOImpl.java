package dao.impl;

import dao.AnalysisDAO;
import entity.AnaRet;
import entity.AnaValues;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenm on 2016/6/18.
 */
@Repository
public class AnalysisDAOImpl extends BaseDAOImpl<AnaValues> implements AnalysisDAO {

    @Override
    public List<AnaValues> getAnaValues(int groupId) {
        return getListByColumn("groupId", groupId);
    }

    @Override
    public List<AnaRet> getAllAnaRet() {
        return sessionFactory.getCurrentSession().createQuery("FROM AnaRet").list();
    }
}
