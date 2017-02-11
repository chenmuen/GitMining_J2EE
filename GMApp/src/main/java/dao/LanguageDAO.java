package dao;

import entity.LangCommon;
import entity.Language;
import entity.Repo;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by chenm on 2016/5/12.
 */
public interface LanguageDAO extends BaseDAO<Language>{

    /**
     * 根据仓库名返回该仓库的语言使用情况（统计用）
     * @param repoName
     * @return
     */
    public List<Language> getLanguage(String repoName);

    /**
     * 根据仓库名列表返回该仓库列表的语言使用情况（统计用）
     * @param repoNames
     * @return
     */
    public List<Language> getLanguage(List<String> repoNames);

    /**
     * 根据用户名拿到该用户使用过的语言（统计用）
     * @param userLogin
     * @return
     */
    public List<Language> getLanguageByUser(String userLogin);

    /**
     * 根据语言拿到使用过该语言的仓库
     * @param language
     * @return
     */
    public List<Repo> getRepoByLanguage(String language);

    /**
     * 根据两种语言得到同时拥有该两种语言的仓库列表
     * @param lang1
     * @param lang2
     * @return
     */
    public List<Repo> getRepoByTwoLanguage(String lang1, String lang2);

    /**
     * 根据语言名拿到该语言与其他语言共有的仓库数量
     * @param lang
     * @return
     */
    public List<LangCommon> getCommonRepoCount(String lang);

    /**
     * 拿到所有语言共有仓库的统计信息
     * @return
     */
    public List<LangCommon> getAllCommonRepoCount();

    /**
     * 根据语言名拿到两种语言共有的仓库数量
     * @param lang1
     * @param lang2
     * @return
     */
    public LangCommon getCommonRepoCount(String lang1, String lang2);

    /**
     * 根据语言拿到使用过该语言的用户
     * @param language
     * @return
     */
    public List<User> getUserByLanguage(String language);

    /**
     * 根据年份拿到语言列表
     * @param year
     * @return
     */
    public List<Language> getLanguageByYear(int year);

    /**
     *
     * @param language
     * @return
     */
    public List<Map> getPropertySum(String language, String... columns);

    /**
     * 返回所有语言
     * @return
     */
    public List<String> getAllLanguage();

    /**
     * 得到所有语言的Size
     * @return List<Map(language-语言，size-行数）>
     */
    public List<Map> getSize();

    /**
     * 根据语言返回其各个年份的仓库数量
     * @param language
     * @return
     */
    public List<Map> getLanguageSumPerYear(String language);
}
