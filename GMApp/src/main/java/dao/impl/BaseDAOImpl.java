package dao.impl;

import dao.BaseDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Transformer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenm on 2016/5/8.
 */
public abstract class BaseDAOImpl<T> implements BaseDAO<T> {
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> entityClass;

    public BaseDAOImpl() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    public void add(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(t);
    }

    public void delete(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(t);
    }

    public void update(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
    }

    public List<?> doSqlQuery(String sql) {
        Session session = sessionFactory.getCurrentSession();
        return session.createSQLQuery(sql).list();
    }

    public List<?> doHqlQuery(String hql) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(hql).list();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public T getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(entityClass, id);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public T getByColumn(String column, Object value) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Restrictions.eq(column, value).ignoreCase());
        List<?> list = criteria.list();
        if ((list.size()) == 0) {
            return null;
        } else {
            return (T) list.get(0);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<T> getListByColumn(String column, Object value) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Restrictions.eq(column, value));
        return criteria.list();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<T> getListByColumn(String column, Object value, int page, int size) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Restrictions.eq(column, value));
        criteria.setFirstResult((page - 1) * size);
        criteria.setMaxResults(size);
        return criteria.list();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<T> getListByColumn(String column, Object value, int page, int size, String ordercolumn, boolean asc) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Restrictions.eq(column, value));
        if (ordercolumn != null) {
            if (asc) {
                criteria.addOrder(Order.asc(ordercolumn));
            } else {
                criteria.addOrder(Order.desc(ordercolumn));
            }
        }
        criteria.setFirstResult((page - 1) * size);
        criteria.setMaxResults(size);
        return criteria.list();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<T> getListByLikeColumn(String column, Object value, int page, int size, String ordercolumn, boolean asc) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass)
                .add(Restrictions.like(column, "%" + value + "%"));
        if (ordercolumn != null) {
            if (asc) {
                criteria.addOrder(Order.asc(ordercolumn));
            } else {
                criteria.addOrder(Order.desc(ordercolumn));
            }
        }
        criteria.setFirstResult((page - 1) * size);
        criteria.setMaxResults(size);
        return criteria.list();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<T> list = session.createQuery("From " + entityClass.getName()).list();
        return list;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> getAllByPage(int page, int size) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size);

        return criteria.list();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Object> getPropertyList(String column, boolean asc, String... columns) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;

        if (columns.length == 0) {
            query = session.createQuery("select " + column + " from " + entityClass.getName() + " order by " + column + (asc ? " asc" : " desc"));
        } else {
            String hql = "select " + column;
            for (String col : columns) {
                hql += (" ," + col);
            }
            hql += " from " + entityClass.getSimpleName() + " order by " + column + (asc ? " asc" : " desc");
            System.out.println(hql);
            query = session.createQuery(hql);
        }

        return query.list();
    }

    @Override
    public List<Map> getPropertyMapList(String column, boolean asc, String... columns) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;

        if (columns.length == 0) {
            query = session.createQuery("select " + column + " as " + column + " from " + entityClass.getName() + " order by " + column + (asc ? " asc" : " desc"));
        } else {
            String hql = "select " + column + " as " + column;
            for (String col : columns) {
                hql += (" ," + col + " as " + col);
            }
            hql += " from " + entityClass.getSimpleName();
            System.out.println(hql);
            query = session.createQuery(hql);
        }

        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.list();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Object> getPropertyListByColumn(String column, String paramColumn, String value, boolean asc) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("select " + column + " from " + entityClass.getName() + " where " + paramColumn + " = :val order by " + column + (asc ? " asc" : " desc"))
                .setString("val", value);

        return query.list();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> getListByColumns(String column, List<String> values) {
        if (values.size() == 0) {
            return new ArrayList<T>();
        }

        List<T> resultList = new ArrayList<>();

        int size = 2000;

        for (int i = 0; i < (values.size() - 1) / size + 1; i++) {
            String sql = "";
            if (i != (values.size() - 1) / size) {
                for (int j = i * size; j < (i + 1) * size; j++) {
                    String value = values.get(j);
                    if (j % size == 0) {
                        sql += "(SELECT * FROM " + entityClass.getSimpleName().toLowerCase() + " WHERE " + column + "='" + value + "') \n";
                    } else {
                        sql += "UNION (SELECT * FROM " + entityClass.getSimpleName().toLowerCase() + " WHERE " + column + "='" + value + "') \n";
                    }
                }
            } else {
                for (int j = i * size; j < values.size(); j++) {
                    String value = values.get(j);
                    if (j % size == 0) {
                        sql += "(SELECT * FROM " + entityClass.getSimpleName().toLowerCase() + " WHERE " + column + "='" + value + "') \n";
                    } else {
                        sql += "UNION (SELECT * FROM " + entityClass.getSimpleName().toLowerCase() + " WHERE " + column + "='" + value + "') \n";
                    }
                }
            }

            Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(entityClass);

            resultList.addAll(query.list());

        }

        return resultList;
    }

    @SuppressWarnings("rawtypes")
    public int getCounts() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        int count = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
        return count;
    }

    public int getCounts(String columnName, String keyword) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass)
                .add(Restrictions.like(columnName, "%" + keyword + "%"));
        int count = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
        return count;
    }

    @Override
    public int getDistinctCounts(String col) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT (DISTINCT "+col+") FROM " + entityClass.getSimpleName());
        int count = ((Long)query.uniqueResult()).intValue();
        return count;
    }

    @Override
    public double getRanking(String column, int value) {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(DISTINCT "+column+")+1 FROM " + entityClass.getSimpleName() + " WHERE " + column + " > :val");
        query.setParameter("val", value);

        int result = ((Long) query.uniqueResult()).intValue();

        int sum = getDistinctCounts(column);

        return (double)result/(double)sum;
    }

    @Override
    public List<T> getListByRanking(String rankColumn, int ranking, int num) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM " + entityClass.getSimpleName() + " ORDER BY " + rankColumn + " DESC ")
                .setFirstResult(ranking-1)
                .setMaxResults(num);

        return query.list();
    }

    @Override
    public List<T> getListRandomly(String col, int max, int min, int limit) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM "+entityClass.getSimpleName()+" WHERE " + col + ">:minNum AND " + col + "<:maxNum ORDER BY RAND()")
                .setInteger("minNum", min)
                .setInteger("maxNum",max)
                .setMaxResults(limit);

        return query.list();
    }
}
