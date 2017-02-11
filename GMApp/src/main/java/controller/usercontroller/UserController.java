package controller.usercontroller;

import controller.BaseController;
import controller.uitities.ChartUtil;
import entity.Language;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import service.UserStatisticService;
import util.enums.ResultMessage;
import vo.CommitVO;
import vo.RateVO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Lenovo on 2016/5/12.
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserStatisticService userStatisticService;

    @Override
    @RequestMapping("user")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "user";
    }

    @Override
    @RequestMapping("user-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        
        String username = request.getParameter("login");
        if (username == null) return null; // 404
        UserVO user = userService.showUser(username);
        if (user == null) return null; // 404
        System.out.println("username: "+username);

        //默认数据
        List<String> languageList = userService.getLanguagesByUser(username)
                .stream()
                .map(e -> e.getLanguage())
                .distinct()
                .collect(Collectors.toList());
        ret.put("name", user.login);
        ret.put("location", user.location);
        ret.put("email", user.email);
        ret.put("joinTime", user.createAt);
        ret.put("stars", user.starredCount);
        ret.put("repos", user.publicRepo);
        ret.put("followers", user.followersCount);
        ret.put("following", user.followingsCount);
        ret.put("gists", user.publicGists);
        ret.put("languages", languageList);
        ret.put("avatar", user.avatarUrl);

        return ret;
    }

    @RequestMapping("user-radar")
    @ResponseBody
    public JSONObject ajaxRadar(HttpServletResponse response, HttpServletRequest request){
        JSONObject ret = new JSONObject();

        String username = request.getParameter("login");
        if (username == null) return null; // 404
        UserVO user = userService.showUser(username);

        //雷达图属性
        JSONArray value = new JSONArray();
        JSONArray scoreIndicator = new JSONArray();
        ChartUtil.getRadarData(value, scoreIndicator, userStatisticService.getRadar(username));
        ret.put("values",value);
        ret.put("scoreIndicator",scoreIndicator);

        return ret;
    }

    @RequestMapping("user-lang")
    @ResponseBody
    public JSONObject ajaxLang(HttpServletResponse response, HttpServletRequest request){

        JSONObject ret = new JSONObject();

        String username = request.getParameter("login");
        if (username == null) return null; // 404
        UserVO user = userService.showUser(username);

        //语言饼图属性
        JSONArray lanName = new JSONArray();
        JSONArray languageArray = new JSONArray();
        ChartUtil.getLauguageData(lanName, languageArray, userStatisticService.getUserLanguage(username));
        ret.put("lanName",lanName);
        ret.put("lanData",languageArray);

        return ret;
    }

    @RequestMapping("user-rate")
    @ResponseBody
    public JSONObject ajaxRate(HttpServletResponse response, HttpServletRequest request){

        JSONObject ret = new JSONObject();

        String username = request.getParameter("login");
        if (username == null) return null; // 404
        UserVO user = userService.showUser(username);

        //柱状图-贡献率
        JSONArray repoName = new JSONArray();
        JSONArray contrData = new JSONArray();
        List<RateVO> rates = userStatisticService.getContributeRates(username);
        rates.forEach(rateVO -> {
            repoName.add(rateVO.name);
            contrData.add(rateVO.rate);
        });
        ret.put("repoName", repoName);
        ret.put("contrData", contrData);

        return ret;
    }

    @RequestMapping("user-commit")
    @ResponseBody
    public JSONObject ajaxCommit(HttpServletResponse response, HttpServletRequest request){

        JSONObject ret = new JSONObject();

        String username = request.getParameter("login");
        if (username == null) return null; // 404
        UserVO user = userService.showUser(username);

        //commit-single user by month
        JSONArray commitData = new JSONArray();
        List<CommitVO> commitVOs = userStatisticService.getCommits(username);
        Map<Integer, List<CommitVO>> commits_map = commitVOs.stream()
                .collect(Collectors.groupingBy(e -> e.time.get(Calendar.MONTH)));
        for (int i = 1; i <= 12 ; i++) {
            List<CommitVO> l = commits_map.get(i);
            Integer j = 0;
            if (l != null) j = l.size();
            commitData.add(j);
        }
        ret.put("commitData", commitData);

        return ret;
    }


    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
