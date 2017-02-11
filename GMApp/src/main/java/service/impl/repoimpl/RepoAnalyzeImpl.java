package service.impl.repoimpl;

import dao.AnalysisDAO;
import dao.RepoDAO;
import entity.AnaRet;
import entity.AnaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.RepoAnalyzeService;
import service.RepoService;
import util.enums.MyPoint;
import util.enums.AnalyType;
import vo.RepoVO;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/6/16.
 */
@Service
@Transactional
public class RepoAnalyzeImpl implements RepoAnalyzeService {

    @Autowired
    private RepoDAO repoDAO;
    @Autowired
    private RepoService repoService;
    @Autowired
    private AnalysisDAO analysisDAO;

    @Override
    public List<MyPoint> getScatterMaps(AnalyType type1, AnalyType type2) {
        List<MyPoint> points = new LinkedList<>();
        List<Map> maps = repoDAO.getPropertyMapList(type1.toString(), false, type2.toString());

        for (int i = 0; i < 5000; i++) {
            Map map = maps.get(i);
            Integer in = (int) map.get(type1.toString());
            Integer jn = (int) map.get(type2.toString());
            points.add(new MyPoint(in, jn));
        }

        return points;
    }

    @Override
    public Map<String, Double> getParams(AnalyType type1, AnalyType type2) {
        List<AnaRet> all = analysisDAO.getAllAnaRet();
        return null;
    }

    @Override
    public List<MyPoint> getScatterSampleMap(AnalyType type1, AnalyType type2) {
        return null;
    }

}
