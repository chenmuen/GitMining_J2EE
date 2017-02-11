package dao;

import entity.*;
import util.enums.LangSortType;

import java.util.List;

/**
 * Created by chenm on 2016/6/3.
 */
public interface SingleLanguageDAO extends BaseDAO<SingleLanguage>{
    /**
     * 根据语言名拿语言实体
     * @param language
     * @return
     */
    public SingleLanguage getLanguage(String language);

    /**
     * 根据语言名拿语言标签
     * @param language
     * @param isApplication 是否为应用标签
     * @return
     */
    public List<String> getTagList(String language, boolean isApplication);

    /**
     * 拿到所有语言标签
     * @param isApplication
     * @return
     */
    public List<String> getAllTag(boolean isApplication);

    /**
     * 根据两种语言名拿到该两种语言共有的仓库列表
     * @param lang1
     * @param lang2
     * @return
     */
    public List<String> getTagListByTwoLanguage(String lang1, String lang2);

    /**
     * 根据关键字，范式标签，应用标签，排序类型，页码拿到一个语言列表
     * @param keyword
     * @param langCatatory
     * @param langApplication
     * @param langSortType
     * @param page
     * @return
     */
    public List<SingleLanguage> getLanguageList(String keyword, String langCatatory, String langApplication, LangSortType langSortType, int page);

    /**
     * 根据标签名拿到拥有该标签的语言列表
     * @param tagName
     * @return
     */
    public List<SingleLanguage> getLanguageListByTag(String tagName);

    /**
     * 根据年份拿到该年份所有语言的统计数据
     * @param year
     * @return
     */
    public List<LanguageYearStat> getLanguageStatByYear(int year);

    /**
     * 根据关键字，范式标签，应用标签拿到符合条件的语言列表的页数
     * @param keyword
     * @param langCatatory
     * @param langApplication
     * @return
     */
    public int getPages(String keyword, String langCatatory, String langApplication);

    /**
     * 随机返回一条Quote
     * @return
     */
    public Quote getQuote();

    /**
     * 根据语言返回其Quote，如果没有专属Quote，则随机返回
     * @param language
     * @return
     */
    public Quote getQuote(String language);

    /**
     * 根据语言返回一个推荐的课程，如果没有专属课程，则返回通用课程
     * @param language
     * @return
     */
    public RecommendCourse getRecommendCourse(String language);

    /**
     * 根据语言返回一个推荐站点列表，如果没有专属站点，则返回空List
     * @param language
     * @return
     */
    public List<RecommendSite> getRecommendSite(String language);

}
