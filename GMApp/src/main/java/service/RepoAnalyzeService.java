package service;

import util.enums.MyPoint;
import util.enums.AnalyType;

import java.util.List;
import java.util.Map;

/**
 * Created by raychen on 16/6/16.
 */
public interface RepoAnalyzeService {
    public List<MyPoint> getScatterMaps(AnalyType type1, AnalyType type2);
    public Map<String,Double> getParams(AnalyType type1, AnalyType type2);
    public List<MyPoint> getScatterSampleMap(AnalyType type1, AnalyType type2);
}
