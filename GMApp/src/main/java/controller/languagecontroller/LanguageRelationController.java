package controller.languagecontroller;

import controller.BaseController;
import controller.uitities.CommonUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LanguageService;
import service.LanguageStatisticService;
import util.LanguageRelation;
import util.enums.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by raychen on 16/5/31.
 */
@Controller
@RequestMapping("/")
public class LanguageRelationController extends BaseController {

    @Autowired
    private LanguageStatisticService languageStatisticService;

    @Override
    @RequestMapping("lang-relation")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
//        LanguageRelation relation = languageStatisticService.getAllRelations();
//        model.addAttribute("data", CommonUtil.getTotalRelationFromRelation(relation));
        return "lang-relation";
    }
    @RequestMapping("lang-relation-ajax")
    @ResponseBody
    public JSONObject ajaxRelation(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        LanguageRelation relation = languageStatisticService.getAllRelations();
        ret.put("data", CommonUtil.getTotalRelationFromRelation(relation));
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
