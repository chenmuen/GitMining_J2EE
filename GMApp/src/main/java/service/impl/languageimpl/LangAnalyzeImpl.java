package service.impl.languageimpl;

import dao.SingleLanguageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.LangAnalyzeService;
import service.LanguageService;
import util.enums.AnalyType;
import util.enums.MyPoint;
import vo.LanguageVO;
import vo.UserVO;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/6/18.
 */
@Service
@Transactional
public class LangAnalyzeImpl implements LangAnalyzeService {

    @Autowired
    private LanguageService languageService;
    @Autowired
    private SingleLanguageDAO singleLanguageDAO;

    @Override
    public List<MyPoint> getScatterMaps(AnalyType type1, AnalyType type2) {
        List<MyPoint> points = new LinkedList<>();
        List<Map> maps = singleLanguageDAO.getPropertyMapList(type1.toString(), false, type2.toString());

        for (int i = 0; i < maps.size(); i++) {
            Map map = maps.get(i);
            Integer in = (int) map.get(type1.toString());
            Integer jn = (int) map.get(type2.toString());
            points.add(new MyPoint(in, jn));
        }

        return points;
    }

    @Override
    public Map<String, Double> getParams(AnalyType type1, AnalyType type2) {
        return null;
    }

    @Override
    public List<MyPoint> getScatterSampleMap(AnalyType type1, AnalyType type2) {
        return null;
    }

}
