package dao.impl;

import dao.UserDAO;
import entity.Repo;
import entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import util.Constant;
import util.enums.UserSortType;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chenm on 2016/5/9.
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO  {
    public User getUser(String userName) {
        return getByColumn("login", userName);
    }

    public List<User> getUserList(String word, UserSortType sortType, int page) {
        return getListByLikeColumn("login", word, page, Constant.PAGE_SIZE, sortType.getOrderColumn(), false);
    }

    public int getPages(String word) {
        int count = getCounts("login", word);
        return count/Constant.PAGE_SIZE + 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getRepoNamesOfUser(String userLogin) {
        return (List<String>)doSqlQuery("SELECT repo_full_name FROM joinrepo WHERE user_login = \'"+userLogin+"\'");
    }

    @Override
    public List<Repo> getRepoOfUser(String userLogin) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Repo where fullName in (select repoFullName from Joinrepo where userLogin = :userLogin)");
        query.setString("userLogin", userLogin);
        return query.list();
    }

    @Override
    public List<User> getUserList(String word, UserSortType sortType, String language, int year, int page) {
        boolean hasLanguage = (language!=null);
        boolean hasYear = (year!=0);

        if(!hasLanguage && !hasYear) {
            return getUserList(word,sortType,page);
        }

        Query query =  getUserQueryList(word,sortType,language,year,false);
        query.setFirstResult((page-1)*Constant.PAGE_SIZE);
        query.setMaxResults(Constant.PAGE_SIZE);

        return query.list();
    }

    @Override
    public int getPages(String word, String language, int year) {
        int count = ((Long)getUserQueryList(word, UserSortType.GENERAL, language, year, true).uniqueResult()).intValue();

        return count/Constant.PAGE_SIZE + 1;
    }

    public Query getUserQueryList(String word, UserSortType sortType, String language, int year, boolean isCount) {
        boolean hasLanguage = (language!=null);
        boolean hasYear = (year!=0);

        String hql = "";

        if(isCount) {
            hql += "select count(*) ";
        }

        hql += "from User where login like :word ";

        if(hasYear) {
            hql += "and createAt between :begin and :end ";
        }

        if(hasLanguage) {
            hql += "and exists (select userLogin from Joinrepo joinrepo where login=userLogin AND exists (select repoFullName from Language lang where joinrepo.repoFullName=lang.repoFullName  AND language = :language)) ";
        }

        if(sortType!=UserSortType.GENERAL) {
            hql += "order by "+sortType.getOrderColumn()+" desc ";
        }

        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        //hql参数赋值
        query.setString("word", "%"+word+"%");

        if(hasYear) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, Calendar.JANUARY, 1);
            long begin = calendar.getTimeInMillis();
            calendar.set(year+1, Calendar.JANUARY, 1);
            long end = calendar.getTimeInMillis();

            query.setLong("begin", begin);
            query.setLong("end", end);
        }

        if(hasLanguage) {
            query.setString("language", language);
        }

        return query;
    }

    @Override
    public List<User> getUserListByLanguage(String language, int ranking, int num) {
        String sql = "SELECT * FROM `user` LEFT JOIN userlanguage ON login=user_login WHERE `language`=:lang "+
                "ORDER BY score DESC";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql)
                .addEntity(User.class)
                .setString("lang", language);
        if(ranking!=0) {
            query.setFirstResult(ranking-1);
            query.setMaxResults(num);
        }

        return query.list();
    }
}
