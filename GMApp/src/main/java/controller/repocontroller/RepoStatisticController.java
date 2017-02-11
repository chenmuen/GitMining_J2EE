package controller.repocontroller;

import controller.BaseController;
import controller.uitities.ChartUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RepoStatisticService;
import util.enums.ResultMessage;
import vo.SectorVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Lenovo on 2016/5/12.
 */
@Controller
@RequestMapping("/")
public class RepoStatisticController extends BaseController {

    @Autowired
    private RepoStatisticService repoStatisticService;

    @Override
    @RequestMapping("repos-statistic")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "repos-stats";
    }

    @Override
    @RequestMapping("repos-stat-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        
        //语言-饼图
        JSONArray lanName = new JSONArray();
        JSONArray lanData = new JSONArray();
        ChartUtil.getLauguageData(lanName, lanData, repoStatisticService.getAllLanguage());
        ret.put("lanName", lanName);
        ret.put("lanData", lanData);
        
        return ret;
    }

    @RequestMapping("repos-created")
    @ResponseBody
    public JSONObject ajaxCreated(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        //柱状图-create time
        JSONArray createTime = new JSONArray();
        JSONArray createData = new JSONArray();
        ChartUtil.getHistogramData(createTime, createData, repoStatisticService.getReposCreateAt());
        ret.put("createTime", createTime);
        ret.put("createData", createData);

        return ret;
    }

    @RequestMapping("repos-star")
    @ResponseBody
    public JSONObject ajaxStar(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
//        柱状图-star
        JSONArray starNum = new JSONArray();
        JSONArray starData = new JSONArray();
        ChartUtil.getHistogramData(starNum, starData, repoStatisticService.getReposStar());
        ret.put("starNum", starNum);
        ret.put("starData", starData);
        return ret;
    }

    @RequestMapping("repos-fork")
    @ResponseBody
    public JSONObject ajaxFork(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        //柱状图-fork
        JSONArray forkNum = new JSONArray();
        JSONArray forkData = new JSONArray();
        ChartUtil.getHistogramData(forkNum, forkData, repoStatisticService.getReposFork());
        ret.put("forkNum", forkNum);
        ret.put("forkData", forkData);
        return ret;
    }

    @RequestMapping("repos-contr")
    @ResponseBody
    public JSONObject ajaxContr(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        //柱状图-contr
        JSONArray contrNum = new JSONArray();
        JSONArray contrData = new JSONArray();
        ChartUtil.getHistogramData(contrNum, contrData, repoStatisticService.getRepoContritutor());
        ret.put("contrNum", contrNum);
        ret.put("contrData", contrData);
        return ret;
    }

    @RequestMapping("repos-colla")
    @ResponseBody
    public JSONObject ajaxColla(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        //柱状图-colla
        JSONArray collaNum = new JSONArray();
        JSONArray collaData = new JSONArray();
        ChartUtil.getHistogramData(collaNum, collaData, repoStatisticService.getRepoCollaborator());
        ret.put("collaNum", collaNum);
        ret.put("collaData", collaData);
        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
