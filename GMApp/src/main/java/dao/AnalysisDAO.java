package dao;

import entity.AnaRet;
import entity.AnaValues;

import java.util.List;

/**
 * Created by chenm on 2016/6/18.
 */
public interface AnalysisDAO extends BaseDAO<AnaValues> {
    public List<AnaValues> getAnaValues(int groupId);

    public List<AnaRet> getAllAnaRet();
}
