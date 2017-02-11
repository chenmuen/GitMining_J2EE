package dao;

import entity.Repo;
import entity.RepoTag;
import util.enums.RepoSortType;

import java.util.List;

/**
 * Created by chenm on 2016/5/8.
 */
public interface RepoDAO extends BaseDAO<Repo> {
    /**
     * 根据仓库名返回一个仓库对象
     * @param repoName-仓库名
     * @return 仓库实体
     */
    public Repo getRepo(String repoName);

    /**
     * 根据仓库名返回其所有标签
     * @param fullName-仓库名
     * @return 标签列表
     */
    public List<RepoTag> getRepoTags(String fullName);

    /**
     * 根据一些标签返回拥有这些标签的仓库
     * @param tags-标签列表
     * @return 仓库列表
     */
    public List<Repo> getRepoListByTags(List<String> tags);

    /**
     * 根据仓库关键字，排序，页码返回一个仓库列表的迭代器
     * @param word-搜索关键字
     * @param sortType-排序方式
     * @param page-页码
     * @return 仓库列表
     */
    public List<Repo> getRepoList(String word, RepoSortType sortType, int page);

    /**
     * 根据搜索关键字返回搜索结果的总页数
     * @param word-搜索关键字
     * @return 页数
     */
    public int getPages(String word);

    /**
     * 根据仓库关键字，排序，标签，年份，语言，页码返回一个仓库列表
     * 空白数据：String: null, int: 0
     * @param word-搜索关键字
     * @param sortType-排序方式
     * @param tag-筛选标签
     * @param year-筛选年份
     * @param language-筛选语言
     * @param page-页码
     * @return 仓库列表
     */
    public List<Repo> getRepoList(String word, RepoSortType sortType, String tag, int year, String language, int page);

    /**
     * 根据搜索关键字，筛选标签，筛选年份，筛选语言返回搜索结果的总页数
     * 空白数据：String: null, int: 0
     * @param word-搜索关键字
     * @param tag-筛选标签
     * @param year-筛选年份
     * @param language-筛选语言
     * @return 页数
     */
    public int getPages(String word, String tag, int year, String language);

    /**
     * 根据主语言拿仓库
     * @param language
     * @param ranking - 拿全部仓库时ranking为0
     * @param num - 拿全部仓库时num为0
     * @return
     */
    public List<Repo> getRepoListByLanguage(String language, int ranking, int num);
}
