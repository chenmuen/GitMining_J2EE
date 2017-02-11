package service;

import entity.Language;
import vo.UserVO;

import java.util.List;

/**
 * Created by chenm on 2016/5/8.
 */
public interface UserService {
    /**
     * 根据用户名返回一个UserVO对象
     * @param userName-用户名
     * @return UserVO
     */
    public UserVO showUser(String userName);

    /**
     * 根据搜索关键词和对应页码返回UserVO列表的迭代器，不排序
     * @param word-搜索关键词
     * @param page-对应页码
     * @return Iterator<UserVO>
     */
    public List<UserVO> showUserList(String word, int page, String language, int year);

    /**
     * 根据搜索关键词和对应页码返回UserVO列表的迭代器，按仓库数量排序
     * @param word-搜索关键词
     * @param page-对应页码
     * @return Iterator<UserVO>
     */
    public List<UserVO> sortUserListByRepo(String word, int page, String language, int year);

    /**
     * 根据搜索关键词和对应页码返回UserVO列表的迭代器，按Follower数量排序
     * @param word-搜索关键词
     * @param page-对应页码
     * @return Iterator<UserVO>
     */
    public List<UserVO> sortUserListByFollower(String word, int page, String language, int year);

    /**
     * 根据搜索关键词返回搜索结果的总页数
     * @param word-搜索关键词
     * @return 总页数
     */
    public int getPages(String word, String language, int year);

    /**
     * 根据用户名拿使用语言
     * @param userName
     * @return
     */
    public List<Language> getLanguagesByUser(String userName);

    /**
     * 拿到所有userVO
     * @return
     */
    public List<UserVO> getAll();

    /**
     * 根据语言拿user
     * @param langName
     * @return
     */
    public List<UserVO> getUserByLanguage(String langName);

    /**
     * 拿到排名num的user
     * @param num
     * @return
     */
    public List<UserVO> getRankedUser(int num, String lang);
}
