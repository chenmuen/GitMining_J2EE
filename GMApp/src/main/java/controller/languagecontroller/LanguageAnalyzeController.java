package controller.languagecontroller;

import controller.BaseController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LangAnalyzeService;
import util.enums.AnalyType;
import util.enums.MyPoint;
import util.enums.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by chendanni on 2016/6/18.
 */
@Controller
@RequestMapping("/")
public class LanguageAnalyzeController extends BaseController {

    @Autowired
    private LangAnalyzeService languageAnalyzeService;

    @Override
    @RequestMapping("lang-analyze")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "lang-analysis";
    }
    //-----------------user----------------------------------
    @RequestMapping("lang-user-score-ajax")
    @ResponseBody
    public JSONObject ajaxUserScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = languageAnalyzeService.getScatterMaps(AnalyType.USERS, AnalyType.LANG_SCORE);
        JSONArray dataAll = new JSONArray();
        JSONArray start = new JSONArray();
        JSONArray end = new JSONArray();
        double xMax = 0;
        double yMax = 0;
        for (MyPoint myPoint: points) {
            JSONArray array = new JSONArray();
            JSONObject data = new JSONObject();
            array.add(myPoint.x);
            array.add(myPoint.y);
            data.put("value",array);
            data.put("symbolSize",2);
            dataAll.add(data);
            if(myPoint.x > xMax) xMax = myPoint.x;
            if(myPoint.y > yMax) yMax = myPoint.y;
        }

        ret.put("userScoreDataAll", dataAll);
        ret.put("userScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("userScoreXMax", xMax);
        ret.put("userScoreYMax", yMax);
        ret.put("userScoreStartCoord", start);
        ret.put("userScoreEndCoord", end);
        ret.put("userScoreXName", "User");
        ret.put("userScoreYName", "Score");

        return ret;
    }
    @RequestMapping("lang-user-sample-score-ajax")
    @ResponseBody
    public JSONObject ajaxUserSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = languageAnalyzeService.getScatterSampleMap(AnalyType.USERS, AnalyType.LANG_SCORE);
        JSONArray dataAll = new JSONArray();
        JSONArray start = new JSONArray();
        JSONArray end = new JSONArray();
        double xMax = 0;
        double yMax = 0;
        for (MyPoint myPoint: points) {
            JSONArray array = new JSONArray();
            JSONObject data = new JSONObject();
            array.add(myPoint.x);
            array.add(myPoint.y);
            data.put("value",array);
            data.put("symbolSize",2);
            dataAll.add(data);
            if(myPoint.x > xMax) xMax = myPoint.x;
            if(myPoint.y > yMax) yMax = myPoint.y;
        }

        ret.put("userSampleScoreDataAll", dataAll);
        ret.put("userSampleScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("userSampleScoreXMax", xMax);
        ret.put("userSampleScoreYMax", yMax);
        ret.put("userSampleScoreStartCoord", start);
        ret.put("userSampleScoreEndCoord", end);
        ret.put("userSampleScoreXName", "User");
        ret.put("userSampleScoreYName", "Score");

        return ret;
    }
    //-----------------repo----------------------------------
    @RequestMapping("lang-repo-score-ajax")
    @ResponseBody
    public JSONObject ajaxRepoScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = languageAnalyzeService.getScatterMaps(AnalyType.REPOS, AnalyType.LANG_SCORE);
        JSONArray dataAll = new JSONArray();
        JSONArray start = new JSONArray();
        JSONArray end = new JSONArray();
        double xMax = 0;
        double yMax = 0;
        for (MyPoint myPoint: points) {
            JSONArray array = new JSONArray();
            JSONObject data = new JSONObject();
            array.add(myPoint.x);
            array.add(myPoint.y);
            data.put("value",array);
            data.put("symbolSize",2);
            dataAll.add(data);
            if(myPoint.x > xMax) xMax = myPoint.x;
            if(myPoint.y > yMax) yMax = myPoint.y;
        }

        ret.put("repoScoreDataAll", dataAll);
        ret.put("followerScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("repoScoreXMax", xMax);
        ret.put("repoScoreYMax", yMax);
        ret.put("repoScoreStartCoord", start);
        ret.put("repoScoreEndCoord", end);
        ret.put("repoScoreXName", "Repo");
        ret.put("repoScoreYName", "Score");

        return ret;
    }
    @RequestMapping("lang-repo-sample-score-ajax")
    @ResponseBody
    public JSONObject ajaxRepoSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = languageAnalyzeService.getScatterSampleMap(AnalyType.REPOS, AnalyType.LANG_SCORE);
        JSONArray dataAll = new JSONArray();
        JSONArray start = new JSONArray();
        JSONArray end = new JSONArray();
        double xMax = 0;
        double yMax = 0;
        for (MyPoint myPoint: points) {
            JSONArray array = new JSONArray();
            JSONObject data = new JSONObject();
            array.add(myPoint.x);
            array.add(myPoint.y);
            data.put("value",array);
            data.put("symbolSize",2);
            dataAll.add(data);
            if(myPoint.x > xMax) xMax = myPoint.x;
            if(myPoint.y > yMax) yMax = myPoint.y;
        }

        ret.put("repoSampleScoreDataAll", dataAll);
        ret.put("repoSampleScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("repoSampleScoreXMax", xMax);
        ret.put("repoSampleScoreYMax", yMax);
        ret.put("repoSampleScoreStartCoord", start);
        ret.put("repoSampleScoreEndCoord", end);
        ret.put("repoSampleScoreXName", "Repo");
        ret.put("repoSampleScoreYName", "Score");

        return ret;
    }

    @Override
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
