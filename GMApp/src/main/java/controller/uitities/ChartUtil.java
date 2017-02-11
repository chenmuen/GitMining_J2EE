package controller.uitities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vo.*;

import java.util.Calendar;
import java.util.List;

/**
 * Created by raychen on 16/5/13.
 */
public class ChartUtil {

    /**
     * 雷达图
     * @param value
     * @param scoreIndicator
     * @param scores
     */
    public static void getRadarData(JSONArray value, JSONArray scoreIndicator, List<RadarVO> scores){
        for (RadarVO vo : scores){
            value.add(vo.getValue());
            JSONObject obj = new JSONObject();
            obj.put("name",vo.getName());
            obj.put("max",vo.getMax());
            scoreIndicator.add(obj);
        }
    }

    /**
     * 饼图-语言
     * @param language_names
     * @param languageArray
     * @param language
     */
    public static void getLauguageData(JSONArray language_names, JSONArray languageArray, List<LanguageStaVO> language){
        for (LanguageStaVO vo : language){
            language_names.add(vo.getLanguage_name());
            JSONObject obj = new JSONObject();
            obj.put("value", vo.value);
            obj.put("name", vo.getLanguage_name());
            languageArray.add(obj);
        }
    }

    /**
     * rate-饼图
     * @param comName
     * @param comData
     * @param rateVOs
     */
    public static void getRatePieData(JSONArray comName, JSONArray comData, List<RateVO> rateVOs){
        for (RateVO vo : rateVOs){
            comName.add(vo.name);
            JSONObject obj = new JSONObject();
            obj.put("value", vo.value);
            obj.put("name", vo.name);
            comData.add(obj);
        }
    }

    /**
     * 柱状图
     * @param num
     * @param data
     * @param sectorVOs
     */
    public static void getHistogramData(JSONArray num, JSONArray data, List<SectorVO> sectorVOs){
        sectorVOs.forEach(sectorVO -> {
            num.add(sectorVO.name);
            data.add(sectorVO.nums);
        });
    }

    /**
     * commit增减折线图
     * @param time
     * @param addtion
     * @param deletion
     * @param ad
     */
    public static void getAddtionDeletionData(JSONArray time, JSONArray addtion, JSONArray deletion, List<CommitVO> ad){
        for (CommitVO vo : ad){
            String dateTemp = null;
            dateTemp = Integer.toString(vo.time.get(Calendar.YEAR)) + '.'
                    + Integer.toString(vo.time.get(Calendar.MONTH)) + '.'
                    + Integer.toString(vo.time.get(Calendar.DATE));
            time.add(dateTemp);
            addtion.add(vo.additions);
            deletion.add(-vo.deletions);
        }
    }

    /**
     * punch card
     * @param punchVOs
     * @return
     */
    public static JSONArray getPunchData(List<PunchVO> punchVOs){
        JSONArray punchData = new JSONArray();

        for (PunchVO vo : punchVOs){
            JSONArray punch = new JSONArray();
            punch.add(vo.day);
            punch.add(vo.hour);
            punch.add(vo.commits);
            punchData.add(punch);
        }

        return punchData;
    }

    /**
     * contributor commits(repo)
     * @param memName
     * @param memTime
     * @param memNum
     * @param commitsVOs
     */
    public static void getContributorCommits(JSONArray memName, JSONArray memTime, JSONArray memNum, List<ContributorCommitsVO> commitsVOs){
        commitsVOs.forEach(contributorCommitsVO -> {
            memName.add(contributorCommitsVO.getLoginName());
            List<CommitVO> commits = contributorCommitsVO.getCommits();
            JSONArray times = new JSONArray();
            JSONArray nums = new JSONArray();
            commits.forEach(commit -> {
                String dateTemp = commit.time.get(Calendar.YEAR) + "."
                        + commit.time.get(Calendar.MONTH) + "."
                        + commit.time.get(Calendar.DATE);
                times.add(dateTemp);
                nums.add(commit.commits);
            });
            memTime.add(times);
            memNum.add(nums);
        });
    }
}
