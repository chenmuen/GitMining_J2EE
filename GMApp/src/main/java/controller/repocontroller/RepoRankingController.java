package controller.repocontroller;

import controller.BaseController;
import entity.SingleLanguage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LanguageService;
import service.RepoService;
import service.UserService;
import service.evaluate.Rank;
import service.utilities.CommonLists;
import util.enums.ResultMessage;
import vo.LanguageVO;
import vo.RepoVO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Created by raychen on 16/6/9.
 */
@Controller
@RequestMapping("/")
public class RepoRankingController extends BaseController {

    @Autowired
    private RepoService repoService;
    @Autowired
    private UserService userService;
    @Autowired
    private LanguageService languageService;

    @Override
    @RequestMapping("repo-rank")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {

        JSONArray filters = new JSONArray();
        JSONArray list = new JSONArray();
        JSONArray list2 = new JSONArray();

        String[] allLang = CommonLists.languages;
        for (int i = 0; i < allLang.length; i++) {
            filters.add(allLang[i]);
        }
        List<RepoVO> allRepo = repoService.getRankedRepos(50, null);
        getLists(list, list2, allRepo);

        model.addAttribute("filters", filters);
        model.addAttribute("list", list);
        model.addAttribute("list2", list2);
        model.addAttribute("currentFilter", "All");

        return "repo-ranking";
    }

    @Override
    @RequestMapping("repo-rank-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String lang = request.getParameter("langName");
        List<RepoVO> repoVOs = repoService.getRankedRepos(50, lang);
        JSONArray list = new JSONArray();
        JSONArray list2 = new JSONArray();

        getLists(list, list2, repoVOs);

        ret.put("list", list);
        ret.put("list2", list2);
        ret.put("currentFilter", lang);

        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }

    private void getLists(JSONArray list, JSONArray list2, List<RepoVO> allRepo){
        for (int i = 0; i < 50; i++) {
            if (i>=allRepo.size()) break;
            RepoVO repo = allRepo.get(i);
            UserVO user = userService.showUser(repo.owner);
            if (repo == null) break;
            JSONObject obj = new JSONObject();
            obj.put("rank", i+1);
            if (repo.fullName.length()>35){
                repo.repoName = repo.repoName.substring(0,35-repo.owner.length());
                repo.repoName+="...";
            }
            obj.put("owner", repo.owner);
            obj.put("name", repo.repoName);
            obj.put("stars", repo.score.final_score);
            obj.put("fullname", repo.fullName);
            obj.put("avatar", user.avatarUrl);
            if (i<25) list.add(obj);
            else list2.add(obj);
        }
    }
}
