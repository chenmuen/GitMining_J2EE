package service.impl.userimpl;

import dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserAnalyzeService;
import service.UserService;
import util.enums.AnalyType;
import util.enums.MyPoint;
import vo.UserVO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/6/18.
 */
@Service
@Transactional
public class UserAnalyzeImpl implements UserAnalyzeService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<MyPoint> getScatterMaps(AnalyType type1, AnalyType type2) {
        List<MyPoint> points = new LinkedList<>();
        List<Map> maps = userDAO.getPropertyMapList(type1.toString(), false, type2.toString());

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
        Map<String, Double> map = new HashMap<>();
        return map;
    }

    @Override
    public List<MyPoint> getScatterSampleMap(AnalyType type1, AnalyType type2) {
        return null;
    }

}
