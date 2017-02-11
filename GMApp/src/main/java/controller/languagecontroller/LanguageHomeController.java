package controller.languagecontroller;

import com.sun.org.apache.regexp.internal.RE;
import controller.BaseController;
import controller.uitities.CommonUtil;
import entity.Language;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LanguageService;
import service.LanguageStatisticService;
import util.enums.ResultMessage;
import vo.LanguageVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by raychen on 16/5/31.
 */
@Controller
@RequestMapping("/")
public class LanguageHomeController extends BaseController {

    @Autowired
    private LanguageStatisticService languageStatisticService;
    @Autowired
    private LanguageService languageService;

    @RequestMapping("lang-home")
    @Override
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        String[] quotes = languageStatisticService.getRandomQuote();
        model.addAttribute("quoteContent", quotes[0]);
        model.addAttribute("quoteFrom", quotes[1]);

        return "lang-home";
    }

    @Override
    @RequestMapping("lang_home-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();

        List<LanguageVO> webs = languageStatisticService.getTopLangsByTag("Website");
        ret.put("listWeb", listToArray(webs));
        List<LanguageVO> apps = languageStatisticService.getTopLangsByTag("Mobile Application");
        apps.add(languageService.getLanguage("JavaScript"));
        ret.put("listApp", listToArray(apps));

        return ret;
    }

    @RequestMapping("lang_home-ajax-fame")
    @ResponseBody
    public JSONObject ajaxFame(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();
        Map<Integer, String> hallOfFame = languageStatisticService.getHallOfFame();
        JSONArray listHallOfFame1 = new JSONArray();
        JSONArray listHallOfFame2 = new JSONArray();
        for (int i = 2016; i >= 2007; i--) {
            JSONObject obj = new JSONObject();
            String name = hallOfFame.get(i);
            obj.put("name", name);
            obj.put("year", i);
            if (i<2012) listHallOfFame2.add(obj);
            else listHallOfFame1.add(obj);
        }
        ret.put("listHallOfFame1", listHallOfFame1);
        ret.put("listHallOfFame2", listHallOfFame2);
        return ret;
    }

    @RequestMapping("lang_home-ajax-top")
    @ResponseBody
    public JSONObject ajaxTop5(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();

        ret.put("listObjectiveOriented", listToArray2(languageStatisticService.getTopLangsByTag("Object-oriented")));
        ret.put("listFunctional", listToArray2(languageStatisticService.getTopLangsByTag("Functional")));
        ret.put("listImperative", listToArray2(languageStatisticService.getTopLangsByTag("Imperative")));
        ret.put("listStructured", listToArray2(languageStatisticService.getTopLangsByTag("Server")));

        return ret;
    }

    @RequestMapping("lang_home-ajax-trends")
    @ResponseBody
    public JSONObject ajaxTrends(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = CommonUtil.getTrendsAll(languageStatisticService.getAllLangTrends());
        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }

    private JSONArray listToArray(List<LanguageVO> langs){
        JSONArray array = new JSONArray();
        langs.forEach(languageVO -> {
            JSONObject obj = new JSONObject();
            obj.put("name", languageVO.languageName);
            obj.put("repoCount", languageVO.repos);
            obj.put("devCount", languageVO.users);
            array.add(obj);
        });
        return array;
    }

    private JSONArray listToArray2(List<LanguageVO> langs){
        JSONArray array = new JSONArray();
        for (int i = 0; i < langs.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("rank", i+1);
            LanguageVO vo = langs.get(i);
            obj.put("name", vo.languageName);
            obj.put("rating", vo.score.final_score);
            array.add(obj);
        }
        return array;
    }
}
