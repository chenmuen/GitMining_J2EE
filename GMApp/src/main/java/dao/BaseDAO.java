package dao;

import java.util.List;
import java.util.Map;

/**
 * Created by chenm on 2016/5/8.
 */
public interface BaseDAO<T> {
    public List<?> doSqlQuery(String sql);

    public List<?> doHqlQuery(String hql);

    public void add(T t);

    public void delete(T t);

    public void update(T t);

    @SuppressWarnings({ "rawtypes" })
    public T getById(String id);

    @SuppressWarnings({"rawtypes" })
    public T getByColumn(String column, Object value);

    @SuppressWarnings({"rawtypes" })
    public List<T> getListByColumn(String column, Object value);

    @SuppressWarnings({"rawtypes" })
    public List<T> getListByColumn(String column, Object value, int page, int size);

    @SuppressWarnings({"rawtypes" })
    public List<T> getListByColumn(String column, Object value, int page, int size, String ordercolumn, boolean asc);

    @SuppressWarnings({"rawtypes" })
    public List<T> getListByLikeColumn(String column, Object value, int page, int size, String ordercolumn, boolean asc);

    @SuppressWarnings({"rawtypes"})
    public List<Object> getPropertyList(String column, boolean asc, String ...columns);

    @SuppressWarnings({"rawtypes"})
    public List<Map> getPropertyMapList(String column, boolean asc, String ...columns);

    @SuppressWarnings({"rawtypes"})
    public List<Object> getPropertyListByColumn(String column, String paramColumn, String value, boolean asc);

    @SuppressWarnings({ "rawtypes" })
    public List<T> getAll();

    @SuppressWarnings({ "rawtypes" })
    public List<T> getAllByPage(int page,int size);

    @SuppressWarnings({ "rawtypes" })
    public List<T> getListByColumns(String column, List<String> values);

    @SuppressWarnings("rawtypes")
    public int getCounts();

    @SuppressWarnings("rawtypes")
    public int getCounts(String columnName, String keyword);

    public int getDistinctCounts(String col);

    public double getRanking(String column, int value);

    public List<T> getListByRanking(String rankColumn, int ranking, int num);

    public List<T> getListRandomly(String col, int max, int min, int limit);

}
