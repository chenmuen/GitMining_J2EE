package dao.impl;

import dao.RepoDAO;
import entity.Language;
import entity.Repo;
import entity.RepoTag;
import javafx.scene.control.TableColumn;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;
import util.Constant;
import util.enums.RepoSortType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by chenm on 2016/5/9.
 */
@Repository
public class RepoDAOImpl extends BaseDAOImpl<Repo> implements RepoDAO {
    public Repo getRepo(String repoName) {
        return getByColumn("fullName", repoName);
    }

    public List<Repo> getRepoList(String word, RepoSortType sortType, int page) {
        return getListByLikeColumn("fullName", word, page, Constant.PAGE_SIZE,sortType.getOrderColumn(),false);
    }

    @Override
    public List<RepoTag> getRepoTags(String fullName) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RepoTag.class);
        criteria.add(Restrictions.eq("repoFullName", fullName));
        return criteria.list();
    }

    @Override
    public List<Repo> getRepoListByTags(List<String> tags) {
        if(tags == null || tags.size() == 0) {
            return null;
        }

        String hql = "FROM Repo WHERE fullName in (SELECT repoFullName FROM RepoTag WHERE ";

        for (String tag : tags) {
            if(tags.indexOf(tag) == 0) {
                hql += "LOWER(tagName) = LOWER(?) ";
            } else {
                hql += "OR LOWER(tagName) = LOWER(?) ";
            }
        }

        hql += ")";

        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        for(int i = 0; i < tags.size(); i++) {
            query.setString(i, tags.get(i));
        }

        return query.list();
    }

    public int getPages(String word) {
        int count = getCounts("fullName", word);
        return count/Constant.PAGE_SIZE + 1;
    }

    @Override
    public List<Repo> getRepoList(String word, RepoSortType sortType, String tag, int year, String language, int page) {
        boolean hasTag = (tag!=null);
        boolean hasYear = (year!=0);
        boolean hasLanguage = (language!=null);

        if(!hasTag && !hasYear && !hasLanguage) {
            return getRepoList(word, sortType, page);
        }

        Query query = getRepoQureyList(word,sortType,tag,year,language,false);
        query.setFirstResult((page-1)*Constant.PAGE_SIZE);
        query.setMaxResults(Constant.PAGE_SIZE);

        return query.list();

    }

    @Override
    public int getPages(String word, String tag, int year, String language) {
        Query query = getRepoQureyList(word,RepoSortType.GENERAL,tag,year,language,true);
        int count = ((Long)query.uniqueResult()).intValue();

        return count/Constant.PAGE_SIZE + 1;
    }

    public Query getRepoQureyList(String word, RepoSortType sortType, String tag, int year, String language, boolean isCount) {
        boolean hasTag = (tag!=null);
        boolean hasYear = (year!=0);
        boolean hasLanguage = (language!=null);

        String hql = "";
        if(isCount) {
            hql += "select count(*) ";
        }

        hql += "from Repo repo where fullName like :word ";

        if(hasTag) {
            hql += "and fullName in (select repoFullName from RepoTag where tagName = :tag) ";
        }
        if(hasLanguage) {
            hql += "and  exists (select repoFullName from Language lang where lang.repoFullName = repo.fullName AND language = :language) ";
        }
        if(hasYear) {
            hql += "and createdAt between :begin and :end ";
        }

        if(sortType!=RepoSortType.GENERAL) {
            hql += "order by "+sortType.getOrderColumn()+" desc ";
        }

        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        if(hasTag) {
            query.setString("tag", tag);
        }
        if(hasLanguage) {
            query.setString("language", language);
        }
        if(hasYear) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, Calendar.JANUARY, 1);
            long begin = calendar.getTimeInMillis();
            calendar.set(year+1, Calendar.JANUARY, 1);
            long end = calendar.getTimeInMillis();

            query.setLong("begin", begin);
            query.setLong("end", end);
        }

        query.setString("word", "%"+word+"%");

        return query;
    }

    @Override
    public List<Repo> getRepoListByLanguage(String language, int ranking, int num) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Repo WHERE language = :language ORDER BY score DESC")
                .setString("language", language);
        if(ranking != 0) {
            query.setFirstResult(ranking-1);
            query.setMaxResults(num);
        }

        return query.list();
    }
}
