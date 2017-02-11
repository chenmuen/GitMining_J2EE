package service;

import entity.Language;
import entity.LanguageYearStat;
import entity.SingleLanguage;
import org.python.antlr.ast.Str;
import util.LanguageRelation;
import util.LanguageTrends;
import util.enums.LangSortType;
import util.enums.LangTrendsTypeByYear;
import vo.LanguageJoinVO;
import vo.LanguageVO;
import vo.RepoVO;
import vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by raychen on 16/5/20.
 */
public interface LanguageService {

    /**
     * 根据语言名称拿语言vo
     * @param languageName
     * @return
     */
    public LanguageVO getLanguage(String languageName);

    /**
     * 拿与某种语言最相关的仓库
     * @param languageName
     * @return
     */
    public List<RepoVO> getMostRelatedRepos(String languageName);

    /**
     * 拿与某种语言最相关的用户
     * @param languageName
     * @return
     */
    public List<UserVO> getMostRelatedUsers(String languageName);

    /**
     * 返回所有语言
     * @return
     */
    public List<LanguageVO> getAll();

    /**
     * 转换po - vo
     * @param languages
     * @return
     */
    public List<LanguageVO> toVOList(List<SingleLanguage> languages);

    /**
     * 用于List
     * @param keywords
     * @param page
     * @param sortType
     * @param lang_category
     * @param lang_app
     * @return
     */
    public List<LanguageVO> showLangs(String keywords, int page, LangSortType sortType, String lang_category, String lang_app);

    /**
     * 用于List
     * @param keywords
     * @param lang_category
     * @param lang_app
     * @return
     */
    public int getPage(String keywords, String lang_category, String lang_app);

    /**
     * 拿所有tag
     * @param isApp
     * @return
     */
    public List<String> getAllTag(boolean isApp);

    /**
     * 趋势
     * @param langName
     * @return
     */
    public LanguageTrends getTrends(String langName);

    /**
     * 单个语言在某时间段的统计量
     * @param langName
     * @param type
     * @return
     */
    public Map<Integer, Long> getStatByTime(String langName, LangTrendsTypeByYear type);

    /**
     * 获得所有stat数据
     * @return
     */
    public Map<Integer, List<LanguageYearStat>> getAllYearStats();

    /**
     *
     * @param langName
     * @return
     */
    public List<String> getTagsByLanguage(String langName);

    /**
     * 关系图
     * @param langName
     * @return
     */
    public LanguageRelation relatedNodes(String langName);

    /**
     * 交叉表数据库
     * @param languages
     * @return
     */
    public List<LanguageJoinVO> toJoinList(List<Language> languages);

    public long getSumRepoByYear(String langName, int year);
}
