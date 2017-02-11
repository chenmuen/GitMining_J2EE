package dao;

import entity.Repo;
import entity.User;
import util.enums.UserSortType;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chenm on 2016/5/8.
 */
public interface UserDAO extends BaseDAO<User>{
    /**
     * 根据用户名返回一个User对象
     * @param userName-用户名
     * @return
     */
    public User getUser(String userName);

    /**
     * 根据搜索关键字，排序类型，页码返回一个User对象数组迭代器
     * @param word-搜索关键字
     * @param sortType-排序类型
     * @param page-页码
     * @return
     */
    public List<User> getUserList(String word, UserSortType sortType, int page);

    /**
     * 根据关键字返回搜索结果的总页数
     * @param word-搜索关键字
     * @return
     */
    public int getPages(String word);

    /**
     * 根据用户名返回其参与的仓库名
     * @param userLogin
     * @return
     */
    public List<String> getRepoNamesOfUser(String userLogin);

    public List<Repo> getRepoOfUser(String userLogin);

    public List<User> getUserList(String word, UserSortType sortType, String language, int year, int page);

    public int getPages(String word, String language, int year);

    public List<User> getUserListByLanguage(String language, int ranking, int num);
}
