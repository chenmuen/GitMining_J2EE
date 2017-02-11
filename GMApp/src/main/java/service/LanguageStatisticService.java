package service;

import util.LanguageRelation;
import util.LanguageTrends;
import vo.LanguageVO;

import java.util.List;
import java.util.Map;

/**
 * Created by raychen on 16/6/3.
 */
public interface LanguageStatisticService {

    /**
     * 拿固定类别下前几名的语言
     * @param tag
     * @return
     */
    public List<LanguageVO> getTopLangsByTag(String tag);

    /**
     * 获得所有语言趋势
     * @return
     */
    public List<LanguageTrends> getAllLangTrends();

    /**
     * 名人堂
     * @return
     */
    public Map<Integer, String> getHallOfFame();

    /**
     * 随机名言
     * @return
     */
    public String[] getRandomQuote();

    /**
     * 获得所有语言关系
     * @return
     */
    public LanguageRelation getAllRelations();

}
