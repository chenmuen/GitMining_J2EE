package controller.usercontroller;

import controller.BaseController;
import controller.uitities.CommonUtil;
import entity.User;
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
 * Created by raychen on 16/5/14.
 */
@Controller
public class UsersController extends BaseController {

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping("users")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "users";
    }

    @Override
    @RequestMapping("users-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        
        //parameters
        String keywords = "";
        //2-repo,1-follower
        int sortCode = 0;
        int page = 1;
        if (request.getParameter("keyword")!=null) keywords = request.getParameter("keyword");
        if (request.getParameter("sort")!=null) sortCode = Integer.parseInt(request.getParameter("sort"));
        if (request.getParameter("page")!=null) page = Integer.parseInt(request.getParameter("page"));
        String language = request.getParameter("language");
        int year = request.getParameter("year")==null ? 0 : Integer.parseInt(request.getParameter("year"));
        //默认数据
        List<UserVO> vos;
        switch (sortCode){
            case 2: vos = userService.sortUserListByRepo(keywords, page, language, year);break;
            case 1: vos = userService.sortUserListByFollower(keywords, page, language, year);break;
            default: vos = userService.showUserList(keywords, page, language, year);
        }
        JSONArray list = new JSONArray();
        vos.forEach(userVO -> {
            JSONObject obj = new JSONObject();
            obj.put("name", userVO.login);
            obj.put("followers", userVO.followersCount);
            obj.put("repos", userVO.publicRepo);
            obj.put("location", userVO.location);
            obj.put("email", userVO.email);
            obj.put("joinTime", userVO.createAt);
            obj.put("avatar", userVO.avatarUrl);
            list.add(obj);
        });
        ret.put("currentSort", sortCode);
        ret.put("pages", CommonUtil.getPageBox(userService.getPages(keywords, language, year), page));
        ret.put("currentPage", String.valueOf(page));
        ret.put("list", list);
        //filter
        if (language == null) language = "All";
        ret.put("languages", CommonLists.languages);
        ret.put("years", CommonLists.years);
        ret.put("currentLanguage", language);
        ret.put("currentYear", year==0 ? "All" : String.valueOf(year));

        return ret;
    }

    @RequestMapping("users-top5")
    @ResponseBody
    public JSONObject ajaxTop5(HttpServletResponse response, HttpServletRequest request){
        JSONObject ret = new JSONObject();

        //top5
        List<UserVO> allUser = userService.getRankedUser(5, null);
        JSONArray tops = new JSONArray();
        for (int i = 0; i < 5; i++) {
            JSONObject obj = new JSONObject();
            obj.put("avatar", allUser.get(i).avatarUrl);
            obj.put("name", allUser.get(i).login);
            obj.put("followers", allUser.get(i).score.final_score);
            obj.put("repos", allUser.get(i).publicRepo);
            tops.add(obj);
        }
        ret.put("tops", tops);

        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
