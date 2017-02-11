package controller.repocontroller;

import controller.BaseController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RepoAnalyzeService;
import util.enums.MyPoint;
import util.enums.AnalyType;
import util.enums.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by raychen on 16/6/16.
 */
@Controller
@RequestMapping("/")
public class RepoAnalyzeController extends BaseController {

    @Autowired
    private RepoAnalyzeService repoAnalyzeService;

    @Override
    @RequestMapping("repos-analyze")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
//        List<MyPoint> points = repoAnalyzeService.getScatterMaps(AnalyType.FORK, AnalyType.STAR);
//        JSONArray xxDataAll = new JSONArray();
//        JSONArray start = new JSONArray();
//        JSONArray end = new JSONArray();
//        double xMax = 0;
//        double yMax = 0;
//        for (MyPoint myPoint: points) {
//            JSONArray array = new JSONArray();
//            JSONObject data = new JSONObject();
//            array.add(myPoint.x);
//            array.add(myPoint.y);
//            data.put("value",array);
//            data.put("symbolSize",2);
//            xxDataAll.add(data);
//            if(myPoint.x > xMax) xMax = myPoint.x;
//            if(myPoint.y > yMax) yMax = myPoint.y;
//        }
//
//        model.addAttribute("starForkDataAll", xxDataAll);
//        model.addAttribute("starForkFormatter", "y=3.056*x+4.31");
//        start.add(0);start.add(4.31);
////        double endint = points.get(points.size()-1).x;
//        end.add(xMax);end.add(3.056*xMax+4.31);
//        model.addAttribute("starForkXMax", xMax);
//        model.addAttribute("starForkYMax", yMax);
//        model.addAttribute("starForkStartCoord", start);
//        model.addAttribute("starForkEndCoord", end);
//        model.addAttribute("starForkXName", "fork");
//        model.addAttribute("starForkYName", "star");
//
        return "repo-analysis";
    }
    //-----------------star----------------------------------
    @RequestMapping("repo-star-score-ajax")
    @ResponseBody
    public JSONObject ajaxStarScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterMaps(AnalyType.STAR, AnalyType.REPO_SCORE);
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

        ret.put("starScoreDataAll", dataAll);
        ret.put("starScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("starScoreXMax", xMax);
        ret.put("starScoreYMax", yMax);
        ret.put("starScoreStartCoord", start);
        ret.put("starScoreEndCoord", end);
        ret.put("starScoreXName", "star");
        ret.put("starScoreYName", "score");

        return ret;
    }

    @RequestMapping("repo-star-sample-score-ajax")
    @ResponseBody
    public JSONObject ajaxStarSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterSampleMap(AnalyType.STAR,AnalyType.REPO_SCORE);
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

        ret.put("starSampleScoreDataAll", dataAll);
        ret.put("starSampleScoreFormatter", "y=3.056*x+4.31");
        start.add(0);start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("starSampleScoreXMax", xMax);
        ret.put("starSampleScoreYMax", yMax);
        ret.put("starSampleScoreStartCoord", start);
        ret.put("starSampleScoreEndCoord", end);
        ret.put("starSampleScoreXName", "star");
        ret.put("starSampleScoreYName", "score");

        return ret;
    }
    //-----------------fork----------------------------------
    @RequestMapping("repo-fork-score-ajax")
    @ResponseBody
    public JSONObject ajaxForkScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterMaps(AnalyType.FORK, AnalyType.REPO_SCORE);
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

        ret.put("forkScoreDataAll", dataAll);
        ret.put("forkScoreFormatter", "y=3.056*x+4.31");
        start.add(0); start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("forkScoreXMax", xMax);
        ret.put("forkScoreYMax", yMax);
        ret.put("forkScoreStartCoord", start);
        ret.put("forkScoreEndCoord", end);
        ret.put("forkScoreXName", "fork");
        ret.put("forkScoreYName", "score");

        return ret;
    }

    @RequestMapping("repo-fork-sample-score-ajax")
    @ResponseBody
    public JSONObject ajaxForkSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterSampleMap(AnalyType.FORK, AnalyType.REPO_SCORE);
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

        ret.put("forkSampleScoreDataAll", dataAll);
        ret.put("forkSampleScoreFormatter", "y=3.056*x+4.31");
        start.add(0); start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("forkSampleScoreXMax", xMax);
        ret.put("forkSampleScoreYMax", yMax);
        ret.put("forkSampleScoreStartCoord", start);
        ret.put("forkSampleScoreEndCoord", end);
        ret.put("forkSampleScoreXName", "fork");
        ret.put("forkSampleScoreYName", "score");

        return ret;
    }

    //-----------------contributor----------------------------------
    @RequestMapping("repo-contributor-score-ajax")
    @ResponseBody
    public JSONObject ajaxContributorScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterMaps(AnalyType.CONTRIBUTOR, AnalyType.REPO_SCORE);
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

        ret.put("contributorScoreDataAll", dataAll);
        ret.put("contributorScoreFormatter", "y=3.056*x+4.31");
        start.add(0); start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("contributorScoreXMax", xMax);
        ret.put("contributorScoreYMax", yMax);
        ret.put("contributorScoreStartCoord", start);
        ret.put("contributorScoreEndCoord", end);
        ret.put("contributorScoreXName", "contributor");
        ret.put("contributorScoreYName", "score");

        return ret;
    }

    @RequestMapping("repo-contributor-sample-score-ajax")
    @ResponseBody
    public JSONObject ajaxContributorSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterSampleMap(AnalyType.CONTRIBUTOR, AnalyType.REPO_SCORE);
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

        ret.put("contributorSampleScoreDataAll", dataAll);
        ret.put("contributorSampleScoreFormatter", "y=3.056*x+4.31");
        start.add(0); start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("contributorSampleScoreXMax", xMax);
        ret.put("contributorSampleScoreYMax", yMax);
        ret.put("contributorSampleScoreStartCoord", start);
        ret.put("contributorSampleScoreEndCoord", end);
        ret.put("contributorSampleScoreXName", "contributor");
        ret.put("contributorSampleScoreYName", "score");

        return ret;
    }
    //-----------------issue----------------------------------
    @RequestMapping("repo-issue-score-ajax")
    @ResponseBody
    public JSONObject ajaxIssueScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterMaps(AnalyType.ISSUE, AnalyType.REPO_SCORE);
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

        ret.put("issueScoreDataAll", dataAll);
        ret.put("issueScoreFormatter", "y=3.056*x+4.31");
        start.add(0); start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("issueScoreXMax", xMax);
        ret.put("issueScoreYMax", yMax);
        ret.put("issueScoreStartCoord", start);
        ret.put("issueScoreEndCoord", end);
        ret.put("issueScoreXName", "issue");
        ret.put("issueScoreYName", "score");

        return ret;
    }
    @RequestMapping("repo-issue-sample-score-ajax")
    @ResponseBody
    public JSONObject ajaxIssueSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterSampleMap(AnalyType.ISSUE, AnalyType.REPO_SCORE);
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

        ret.put("issueSampleScoreDataAll", dataAll);
        ret.put("issueSampleScoreFormatter", "y=3.056*x+4.31");
        start.add(0); start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("issueSampleScoreXMax", xMax);
        ret.put("issueSampleScoreYMax", yMax);
        ret.put("issueSampleScoreStartCoord", start);
        ret.put("issueSampleScoreEndCoord", end);
        ret.put("issueSampleScoreXName", "Issue");
        ret.put("issueSampleScoreYName", "Score");

        return ret;
    }
    //-----------------size----------------------------------
    @RequestMapping("repo-size-score-ajax")
    @ResponseBody
    public JSONObject ajaxSizeScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterMaps(AnalyType.SIZE, AnalyType.REPO_SCORE);
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

        ret.put("sizeScoreDataAll", dataAll);
        ret.put("sizeScoreFormatter", "y=3.056*x+4.31");
        start.add(0); start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("sizeScoreXMax", xMax);
        ret.put("sizeScoreYMax", yMax);
        ret.put("sizeScoreStartCoord", start);
        ret.put("sizeScoreEndCoord", end);
        ret.put("sizeScoreXName", "size");
        ret.put("sizeScoreYName", "score");

        return ret;
    }
    @RequestMapping("repo-size-sample--score-ajax")
    @ResponseBody
    public JSONObject ajaxSizeSampleScore(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        List<MyPoint> points = repoAnalyzeService.getScatterSampleMap(AnalyType.SIZE, AnalyType.REPO_SCORE);
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

        ret.put("sizeSampleScoreDataAll", dataAll);
        ret.put("sizeSampleScoreFormatter", "y=3.056*x+4.31");
        start.add(0); start.add(4.31);
//        double endint = points.get(points.size()-1).x;
        end.add(xMax);end.add(3.056*xMax+4.31);
        ret.put("sizeSampleScoreXMax", xMax);
        ret.put("sizeSampleScoreYMax", yMax);
        ret.put("sizeSampleScoreStartCoord", start);
        ret.put("sizeSampleScoreEndCoord", end);
        ret.put("sizeSampleScoreXName", "Size");
        ret.put("sizeSampleScoreYName", "score");

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
