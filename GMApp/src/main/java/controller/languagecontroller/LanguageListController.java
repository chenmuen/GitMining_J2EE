package controller.languagecontroller;

import controller.BaseController;
import controller.uitities.CommonUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LanguageService;
import service.utilities.CommonLists;
import util.enums.LangSortType;
import util.enums.ResultMessage;
import vo.LanguageVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by raychen on 16/5/31.
 */
@Controller
@RequestMapping("/")
public class LanguageListController extends BaseController {

    @Autowired
    private LanguageService languageService;

    @Override
    @RequestMapping("lang-list")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {

        return "lang-list";
    }

    @Override
    @RequestMapping("lang-list-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();

        //parameters
        String keywords = "";
        //1-repos,2-developers
        int sortCode = 0;
        int page = 1;
        if (request.getParameter("keyword")!=null) keywords = request.getParameter("keyword");
        if (request.getParameter("sort")!=null) sortCode = Integer.parseInt(request.getParameter("sort"));
        if (request.getParameter("page")!=null) page = Integer.parseInt(request.getParameter("page"));
        String category = request.getParameter("category");
        String application = request.getParameter("application");
        //数据
        JSONArray list = new JSONArray();
        List<LanguageVO> vos;
        switch (sortCode){
            case 1: vos = languageService.showLangs(keywords, page, LangSortType.REPOS, category, application); break;
            case 2: vos = languageService.showLangs(keywords, page, LangSortType.DEVS, category, application); break;
            default: vos = languageService.showLangs(keywords, page, LangSortType.GENERAL, category, application); break;
        }
        vos.forEach(languageVO -> {
            JSONObject single = new JSONObject();
            single.put("name", languageVO.languageName);
            single.put("repoCount", languageVO.repos);
            single.put("devCount", languageVO.users);
            list.add(single);
        });
        ret.put("list", list);
        ret.put("pages", CommonUtil.getPageBox(languageService.getPage(keywords, category, application), page));
        ret.put("currentPage", String.valueOf(page));
        ret.put("currentSort", sortCode);
        //filter
        if (category==null) category = "All";
        if (application==null) application = "All";
        ret.put("currentCategory", category);
        ret.put("currentApplication", application);
        ret.put("categories", languageService.getAllTag(false));
        ret.put("applications", languageService.getAllTag(true));

        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
