package controller.usercontroller;

import controller.BaseController;
import controller.uitities.ChartUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserStatisticService;
import util.enums.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lenovo on 2016/5/12.
 */
@Controller
@RequestMapping("/")
public class UserStatisticController extends BaseController {

    @Autowired
    private UserStatisticService userStatisticService;

    @Override
    @RequestMapping("users-statistic")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "/users-stats";
    }

    @RequestMapping("users-com")
    @ResponseBody
    public JSONObject ajaxCom(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        //company比例饼图数据
        JSONArray comName = new JSONArray();
        JSONArray comData = new JSONArray();
        ChartUtil.getRatePieData(comName, comData, userStatisticService.getCompanyRates());
        ret.put("comName", comName);
        ret.put("comData", comData);
        return ret;
    }

    @RequestMapping("users-type")
    @ResponseBody
    public JSONObject ajaxType(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        // type比例饼图数据
        JSONArray typeName = new JSONArray();
        JSONArray typeData = new JSONArray();
        ChartUtil.getRatePieData(typeName, typeData, userStatisticService.getTypeRates());
        ret.put("typeName", typeName);
        ret.put("typeData", typeData);
        return ret;
    }

    @RequestMapping("users-repo")
    @ResponseBody
    public JSONObject ajaxRepo(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        // repo柱状图
        JSONArray repoNum = new JSONArray();
        JSONArray repoData = new JSONArray();
        ChartUtil.getHistogramData(repoNum, repoData, userStatisticService.getRepoSector());
        ret.put("repoNum", repoNum);
        ret.put("repoData", repoData);
        return ret;
    }

    @RequestMapping("users-gist")
    @ResponseBody
    public JSONObject ajaxGist(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        // gist柱状图
        JSONArray gistNum = new JSONArray();
        JSONArray gistData = new JSONArray();
        ChartUtil.getHistogramData(gistNum, gistData, userStatisticService.getGistSector());
        ret.put("gistNum", gistNum);
        ret.put("gistData", gistData);
        return ret;
    }

    @RequestMapping("users-follower")
    @ResponseBody
    public JSONObject ajaxFollower(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        // follower柱状图
        JSONArray followerNum = new JSONArray();
        JSONArray followerData = new JSONArray();
        ChartUtil.getHistogramData(followerNum, followerData, userStatisticService.getFollowerSector());
        ret.put("followerNum", followerNum);
        ret.put("followerData", followerData);
        return ret;
    }

    @RequestMapping("users-following")
    @ResponseBody
    public JSONObject ajaxFollowing(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        //following柱状图
        JSONArray followingNum = new JSONArray();
        JSONArray followingData = new JSONArray();
        ChartUtil.getHistogramData(followingNum, followingData, userStatisticService.getFollowingSector());
        ret.put("followingNum", followingNum);
        ret.put("followingData", followingData);
        return ret;
    }

    @Override
    @RequestMapping("users-stat-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
       return null;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
