package controller.usercontroller;

import controller.BaseController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserAnalyzeService;
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
public class UserAnalyzeController extends BaseController {

    @Autowired
    private UserAnalyzeService userAnalyzeService;

    @Override
    @RequestMapping("user-analyze")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "user-analysis";
    }
    //-----------------follower----------------------------------
    @RequestMapping("user-follower-score-ajax")
    @ResponseBody
    public JSONObject ajaxFollowerScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = userAnalyzeService.getScatterMaps(AnalyType.FOLLOWER, AnalyType.USER_SCORE);
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

        ret.put("followerScoreDataAll", dataAll);
        ret.put("followerScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("followerScoreXMax", xMax);
        ret.put("followerScoreYMax", yMax);
        ret.put("followerScoreStartCoord", start);
        ret.put("followerScoreEndCoord", end);
        ret.put("followerScoreXName", "follower");
        ret.put("followerScoreYName", "score");

        return ret;
    }
    @RequestMapping("user-follower-sample-score-ajax")
    @ResponseBody
    public JSONObject ajaxFollowerSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = userAnalyzeService.getScatterSampleMap(AnalyType.FOLLOWER, AnalyType.USER_SCORE);
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

        ret.put("followerSampleScoreDataAll", dataAll);
        ret.put("followerSampleScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("followerSampleScoreXMax", xMax);
        ret.put("followerSampleScoreYMax", yMax);
        ret.put("followerSampleScoreStartCoord", start);
        ret.put("followerSampleScoreEndCoord", end);
        ret.put("followerSampleScoreXName", "follower");
        ret.put("followerSampleScoreYName", "score");

        return ret;
    }
    //-----------------repo_star----------------------------------
    @RequestMapping("user-repo-star-score-ajax")
    @ResponseBody
    public JSONObject ajaxRepoStarScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = userAnalyzeService.getScatterMaps(AnalyType.REPO_STAR, AnalyType.USER_SCORE);
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

        ret.put("repoStarScoreDataAll", dataAll);
        ret.put("repoStarScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("repoStarScoreXMax", xMax);
        ret.put("repoStarScoreYMax", yMax);
        ret.put("repoStarScoreStartCoord", start);
        ret.put("repoStarScoreEndCoord", end);
        ret.put("repoStarScoreXName", "Repository Star");
        ret.put("repoStarScoreYName", "score");

        return ret;
    }
    @RequestMapping("user-repo-star-sample-score-ajax")
    @ResponseBody
    public JSONObject ajaxRepoStarSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = userAnalyzeService.getScatterSampleMap(AnalyType.REPO_STAR, AnalyType.USER_SCORE);
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

        ret.put("repoStarSampleScoreDataAll", dataAll);
        ret.put("repoStarSampleScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("repoStarSampleScoreXMax", xMax);
        ret.put("repoStarSampleScoreYMax", yMax);
        ret.put("repoStarSampleScoreStartCoord", start);
        ret.put("repoStarSampleScoreEndCoord", end);
        ret.put("repoStarSampleScoreXName", "Repository Star");
        ret.put("repoStarSampleScoreYName", "score");

        return ret;
    }
    //-----------------repo----------------------------------
    @RequestMapping("user-repo-score-ajax")
    @ResponseBody
    public JSONObject ajaxRepoScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = userAnalyzeService.getScatterMaps(AnalyType.REPOS, AnalyType.USER_SCORE);
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
        ret.put("repoScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("repoScoreXMax", xMax);
        ret.put("repoScoreYMax", yMax);
        ret.put("repoScoreStartCoord", start);
        ret.put("repoScoreEndCoord", end);
        ret.put("repoScoreXName", "Repository");
        ret.put("repoScoreYName", "score");

        return ret;
    }
    @RequestMapping("user-repo-sample-score-ajax")
    @ResponseBody
    public JSONObject ajaxRepoSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = userAnalyzeService.getScatterSampleMap(AnalyType.REPOS, AnalyType.USER_SCORE);
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
        ret.put("repoSampleScoreXName", "Repository");
        ret.put("repoSampleScoreYName", "score");

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
