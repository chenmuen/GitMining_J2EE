package service;

import entity.RepoTag;
import vo.RepoVO;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chenm on 2016/5/8.
 */
public interface RepoService {
    /**
     * 根据仓库名返回RepoVO对象
     * @param repoName-仓库名
     * @return RepoVO对象
     */
    public RepoVO showRepo(String repoName);

    /**
     * 根据搜索关键词和搜索页数返回RepoVO对象数组的迭代器，不排序
     * @param word-搜索关键词
     * @param page-对应页码
     * @return Iterator<RepoVO>
     */
    public List<RepoVO> showRepoList(String word, int page, String category, String language, int year);

    /**
     * 根据搜索关键词和搜索页数返回RepoVO对象数组的迭代器，按Star数量排序
     * @param word-搜索关键词
     * @param page-对应页码
     * @return Iterator<RepoVO>
     */
    public List<RepoVO> sortRepoListByStar(String word, int page, String category, String language, int year);

    /**
     * 根据搜索关键词和搜索页数返回RepoVO对象数组的迭代器，按Fork数量排序
     * @param word-搜索关键词
     * @param page-对应页码
     * @return Iterator<RepoVO>
    */
    public List<RepoVO> sortRepoListByFork(String word, int page, String category, String language, int year);

    /**
     * 根据搜索关键词和搜索页数返回RepoVO对象数组的迭代器，按Contributor数量排序
     * @param word-搜索关键词
     * @param page-对应页码
     * @return Iterator<RepoVO>
     */
    public List<RepoVO> sortRepoListByContributor(String word, int page, String category, String language, int year);

    /**
     * 根据搜索关键词返回搜索结果的总页数
     * @param word-搜索关键词
     * @return 页数
     */
    public int getPages(String word, String category, String language, int year);

    /**
     * 拿到所有仓库vo
     * @return
     */
    public List<RepoVO> getAll();

    /**
     * 根据标签推荐
     * @param repo
     * @return
     */
    public List<RepoVO> getRelatedByTags(String repo);

    /**
     * 根据owner推荐
     * @param login
     * @return
     */
    public List<RepoVO> getRelatedByOwner(String login, String current);

    /**
     * 根据语言拿repo
     * @param langName
     * @return
     */
    public List<RepoVO> getReposByLanguage(String langName);

    /**
     * 拿排名前num的仓库
     * @param num
     * @param lang
     * @return
     */
    public List<RepoVO> getRankedRepos(int num, String lang);
}
