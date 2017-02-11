package controller.languagecontroller;

import controller.BaseController;
import controller.uitities.CommonUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LanguageService;
import service.LanguageStatisticService;
import util.LanguageTrends;
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
public class LanguageRankingController extends BaseController {

    @Autowired
    private LanguageService languageService;
    @Autowired
    private LanguageStatisticService languageStatisticService;

    @Override
    @RequestMapping("lang-rank")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "lang-ranking";
    }

    /**
     * hall of fame and ranking
     * @param response
     * @param request
     * @return
     */
    @Override
    @RequestMapping("lang-rank-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();

        List<LanguageVO> allLangs = languageService.getAll();
        JSONArray listRanking1 = new JSONArray();
        JSONArray listRanking2 = new JSONArray();
        for (int i = 0; i < 20; i++) {
            LanguageVO lang = allLangs.get(i);
            JSONObject obj = new JSONObject();
            obj.put("rank", i+1);
            obj.put("name", lang.languageName);
            obj.put("rating", lang.score.final_score);
            if (i >= 10) listRanking2.add(obj);
            else listRanking1.add(obj);
        }
        ret.put("listRanking1", listRanking1);
        ret.put("listRanking2", listRanking2);

        Map<Integer, String> hallOfFame = languageStatisticService.getHallOfFame();
        JSONArray listHallOfFame = new JSONArray();
        for (int i = 2016; i >= 2007; i--) {
            JSONObject obj = new JSONObject();
            String name = hallOfFame.get(i);
            obj.put("name", name);
            obj.put("year", i);
            listHallOfFame.add(obj);
        }
        ret.put("listHallOfFame", listHallOfFame);

        return ret;
    }

    @RequestMapping("lang-rank-pic")
    @ResponseBody
    public JSONObject ajaxPic(HttpServletResponse response, HttpServletRequest request){
        JSONObject ret = CommonUtil.getTrendsAll(languageStatisticService.getAllLangTrends());
        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
