package controller.usercontroller;

import controller.BaseController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import service.evaluate.Rank;
import service.utilities.CommonLists;
import util.enums.ResultMessage;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by raychen on 16/6/10.
 */
@Controller
@RequestMapping("/")
public class UserRankingController extends BaseController{

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping("user-rank")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {

        JSONArray filters = new JSONArray();
        JSONArray list = new JSONArray();
        JSONArray list2 = new JSONArray();

        String[] allLang = CommonLists.languages;
        for (int i = 0; i < allLang.length; i++) {
            filters.add(allLang[i]);
        }
        List<UserVO> allUser = userService.getRankedUser(50, null);
        getUserList(list, list2, allUser);

        model.addAttribute("filters", filters);
        model.addAttribute("list", list);
        model.addAttribute("list2", list2);
        model.addAttribute("currentFilter", "All");
        return "user-ranking";
    }

    @Override
    @RequestMapping("user-rank-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();

        String lang = request.getParameter("langName");
        List<UserVO> userVOs = userService.getRankedUser(50, lang);
        JSONArray list = new JSONArray();
        JSONArray list2 = new JSONArray();

        getUserList(list, list2, userVOs);

        ret.put("list", list);
        ret.put("list2", list2);
        ret.put("currentFilter", lang);

        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }

    private void getUserList(JSONArray list, JSONArray list2, List<UserVO> allUser){
        for (int i = 0; i < 50; i++) {
            UserVO user = allUser.get(i);
            if (user == null) break;
            JSONObject obj = new JSONObject();
            obj.put("rank", i+1);
            obj.put("name", user.login);
            obj.put("stars", user.score.final_score);
            obj.put("avatar", user.avatarUrl);
            if (i<25) list.add(obj);
            else list2.add(obj);
        }
    }
}
