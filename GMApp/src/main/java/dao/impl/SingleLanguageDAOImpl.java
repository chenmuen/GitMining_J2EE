package dao.impl;

import dao.SingleLanguageDAO;
import entity.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.python.bouncycastle.asn1.isismtt.x509.Restriction;
import org.springframework.stereotype.Repository;
import util.Constant;
import util.enums.LangSortType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenm on 2016/6/3.
 */
@Repository
public class SingleLanguageDAOImpl extends BaseDAOImpl<SingleLanguage> implements SingleLanguageDAO {
    @Override
    public SingleLanguage getLanguage(String language) {
        return getByColumn("language", language);
    }

    @Override
    public List<String> getTagList(String language, boolean isApplication) {
        if (language == null || language.length() == 0) {
            return new ArrayList<>();
        }

        String hql = "SELECT tagName FROM LanguageTag WHERE language = :language AND isApp = :isApp";

        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setString("language", language);
        query.setInteger("isApp", isApplication?1:0);

        return query.list();
    }

    @Override
    public List<String> getAllTag(boolean isApplication) {
        String hql = "SELECT DISTINCT tagName FROM LanguageTag WHERE isApp = :isApp";

        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setInteger("isApp", isApplication?1:0);

        return query.list();
    }

    @Override
    public List<String> getTagListByTwoLanguage(String lang1, String lang2) {
        lang1 = lang1.replace("'","''");
        lang2 = lang2.replace("'","''");
        String sql = "SELECT lang1.tag_name FROM languagetag lang1 left join languagetag lang2 on lang1.tag_name = lang2.tag_name where  lang1.language = '"+lang1+"' and lang2.language = '"+lang2+"'";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);

        return query.list();
    }

    @Override
    public List<SingleLanguage> getLanguageList(String keyword, String langCategory, String langApplication, LangSortType langSortType, int page) {
        Query query = getQuery(keyword, langCategory, langApplication, langSortType, false);
        query.setFirstResult((page - 1) * Constant.PAGE_SIZE);
        query.setMaxResults(Constant.PAGE_SIZE);

        return query.list();
    }

    @Override
    public List<SingleLanguage> getLanguageListByTag(String tagName) {
        String hql = "FROM SingleLanguage WHERE language in (SELECT language FROM LanguageTag WHERE tagName = :tagName)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("tagName", tagName);

        return query.list();
    }

    @Override
    public List<LanguageYearStat> getLanguageStatByYear(int year) {
        String hql = "FROM LanguageYearStat WHERE year = :year ORDER BY repoNum DESC";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger("year", year);

        return query.list();
    }

    @Override
    public Quote getQuote() {
        String sql = "SELECT * FROM quote WHERE id >= ((SELECT MAX(id) FROM quote) - (SELECT MIN(id) FROM quote)) * RAND() + (SELECT MIN(id) FROM quote) LIMIT 1";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Quote.class);

        List list = query.list();
        if (list == null) {
            return null;
        } else if(list.size() == 0){
            return null;
        } else {
            return (Quote)list.get(0);
        }
    }

    @Override
    public Quote getQuote(String language) {
        String sql = "SELECT * FROM quote WHERE `language` = '' AND id >= ((SELECT MAX(id) FROM quote) - (SELECT MIN(id) FROM quote)) * RAND() + (SELECT MIN(id) FROM quote) LIMIT 1";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Quote.class);

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Quote.class);

        List list;
        if(language == null || language.length()==0) {
            list = query.list();
            if (list == null) {
                return null;
            } else if(list.size() == 0){
                return null;
            } else {
                return (Quote)list.get(0);
            }
        } else {
            criteria.add(Restrictions.eq("language", language));
            list = criteria.list();
            if (list == null) {
                list = query.list();
                if (list.size()==0) return null;
                return (Quote)list.get(0);
            } else {
                if (list.size()==0) return null;
                return (Quote)list.get(0);
            }
        }
    }

    @Override
    public int getPages(String keyword, String langCatatory, String langApplication) {
        Query query = getQuery(keyword, langCatatory, langApplication, LangSortType.GENERAL, true);
        int count = ((Long) query.uniqueResult()).intValue();

        return count / Constant.PAGE_SIZE + 1;
    }

    Query getQuery(String keyword, String langCategory, String langApplication, LangSortType langSortType, boolean isCount) {
        String hql = "";

        if (isCount) {
            hql += "select count(*) ";
        }

        hql += "FROM SingleLanguage WHERE ";

        hql += "lower(language) LIKE :keyword ";

        if (langApplication.length() != 0) {
            hql += "AND language IN (SELECT language FROM LanguageTag WHERE tagName = :langApplication AND isApp = 1) ";
        }

        if (langCategory.length() != 0) {
            hql += "AND language IN (SELECT language FROM LanguageTag WHERE tagName = :langCategory AND isApp = 0) ";
        }

        if (langSortType != LangSortType.GENERAL) {
            hql += "ORDER BY " + langSortType.getOrderColumn() + " DESC ";
        }

        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setString("keyword", "%" + keyword.toLowerCase() + "%");

        if (langApplication.length() != 0) {
            query.setString("langApplication", langApplication);
        }

        if (langCategory.length() != 0) {
            query.setString("langCategory", langCategory);
        }

        return query;
    }

    @Override
    public RecommendCourse getRecommendCourse(String language) {
        String hql = "FROM RecommendCourse WHERE language = :lang ORDER BY rand()";
        Query query = sessionFactory.getCurrentSession().createQuery(hql)
                .setMaxResults(1)
                .setString("lang", language);
        List list = query.list();
        if(list.size()==0) {
            hql = "FROM RecommendCourse WHERE language = '' OR language is NULL ORDER BY rand()";
            query = sessionFactory.getCurrentSession().createQuery(hql)
                    .setMaxResults(1);
            list = query.list();
        }

        return (RecommendCourse)list.get(0);
    }

    @Override
    public List<RecommendSite> getRecommendSite(String language) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RecommendSite.class)
                .add(Restrictions.eq("language", language).ignoreCase());

        return criteria.list();
    }
}
