package dao.impl;

import dao.LanguageDAO;
import dao.RepoDAO;
import dao.UserDAO;
import entity.LangCommon;
import entity.Language;
import entity.Repo;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Transformer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenm on 2016/5/12.
 */
@Repository
public class LanguageDAOImpl extends BaseDAOImpl<Language> implements LanguageDAO {
    @Autowired
    UserDAO userDAO;

    @Autowired
    RepoDAO repoDAO;

    @Override
    public List<Language> getLanguage(String repoName) {
        return getListByColumn("repoFullName", repoName);
    }

    @Override
    public List<Language> getLanguage(List<String> repoNames) {
        return getListByColumns("repo_full_name", repoNames);
    }

    @Override
    public List<Language> getLanguageByUser(String userLogin) {
        List<String> repos = userDAO.getRepoNamesOfUser(userLogin);

        return getListByColumns("repo_full_name", repos);

    }

    @Override
    public List<Repo> getRepoByLanguage(String language) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Repo WHERE fullName in (SELECT repoFullName FROM Language WHERE lower(language) = :language)");
        query.setString("language", language.toLowerCase());

        return query.list();
    }

    @Override
    public List<Repo> getRepoByTwoLanguage(String lang1, String lang2) {
        String sql = "SELECT * FROM repo WHERE full_name in (SELECT lang1.repo_full_name FROM `language` lang1 left join `language` lang2 on lang1.repo_full_name = lang2.repo_full_name where LOWER(lang1.language) = :lang1 and LOWER(lang2.language) = :lang2)";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql)
                .addEntity(Repo.class)
                .setString("lang1", lang1.toLowerCase())
                .setString("lang2", lang2.toLowerCase());

        return query.list();
    }

    @Override
    public List<LangCommon> getCommonRepoCount(String lang) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LangCommon.class);
        criteria.add(Restrictions.eq("lang1", lang));

        return criteria.list();
    }

    @Override
    public LangCommon getCommonRepoCount(String lang1, String lang2) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LangCommon.class);
        criteria.add(Restrictions.eq("lang1", lang1));

        criteria.add(Restrictions.eq("lang2", lang2));

        if (criteria.list().size()==0) return null;
        return (LangCommon)criteria.list().get(0);
    }

    @Override
    public List<LangCommon> getAllCommonRepoCount() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LangCommon.class);

        return criteria.list();
    }

    @Override
    public List<User> getUserByLanguage(String language) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE login IN (SELECT userLogin FROM Joinrepo WHERE repoFullName IN (SELECT repoFullName FROM Language WHERE lower(language) = :language))");
        query.setString("language", language.toLowerCase());

        return query.list();
    }

    @Override
    public List<Language> getLanguageByYear(int year) {
        return getListByColumn("year", year);
    }

    @Override
    public List<Map> getPropertySum(String language, String... columns) {
        String sql = "SELECT `year` as year";
        for (String column : columns) {
            sql += ", COALESCE(SUM(repo.`" + column + "`),0) as " + column;
        }

        sql += " FROM `language` AS lang LEFT JOIN repo ON lang.repo_full_name = repo.full_name WHERE LOWER(lang.`language`)=:lang GROUP BY `year` ORDER BY `year`";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setString("lang", language)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.list();
    }

    @Override
    public List<String> getAllLanguage() {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT language FROM Language ");

        return query.list();
    }

    @Override
    public List<Map> getSize() {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT language as language ,SUM(size) as size FROM Language GROUP BY language ORDER BY SUM(size) desc")
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.list();
    }

    @Override
    public List<Map> getLanguageSumPerYear(String language) {
        String hql = "SELECT year as year,COUNT(*) as sum FROM Language WHERE language = :lang GROUP BY year";

        Query query = sessionFactory.getCurrentSession().createQuery(hql)
                .setString("lang", language)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.list();
    }
}
