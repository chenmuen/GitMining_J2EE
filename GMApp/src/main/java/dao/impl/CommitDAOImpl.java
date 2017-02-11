package dao.impl;

import dao.CommitDAO;
import entity.Commit;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenm on 2016/5/12.
 */
@Repository
public class CommitDAOImpl extends BaseDAOImpl<Commit> implements CommitDAO {

    @Override
    public List<Commit> getCommitsOfContributor(String repoName, String contributorLogin) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Commit.class);
        criteria.add(Restrictions.eq("repoFullName", repoName));
        criteria.add(Restrictions.eq("contributorLogin", contributorLogin));
        return criteria.list();
    }

    @Override
    public List<Commit> getCommitsByRepo(String repoName) {
        return getListByColumn("repoFullName", repoName);
    }

    @Override
    public List<Commit> getCommitByUser(String userLogin) {
        return getListByColumn("contributorLogin", userLogin);
    }
}
