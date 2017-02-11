package controller.repocontroller;

import controller.BaseController;
import controller.uitities.CommonUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RepoService;
import service.evaluate.Rank;
import service.utilities.CommonLists;
import util.enums.ResultMessage;
import vo.RepoVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by raychen on 16/5/14.
 */
@Controller
@RequestMapping("/")
public class ReposController extends BaseController {

    @Autowired
    private RepoService service;

    @Override
    @RequestMapping("repos")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "repos";
    }

    @Override
    @RequestMapping("repos-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();

        //parameters
        String keywords = "";
        //1-star,2-fork,3-contributor
        int sortCode = 0;
        int page = 1;
        if (request.getParameter("keyword")!=null) keywords = request.getParameter("keyword");
        if (request.getParameter("sort")!=null) sortCode = Integer.parseInt(request.getParameter("sort"));
        if (request.getParameter("page")!=null) page = Integer.parseInt(request.getParameter("page"));
        String category = request.getParameter("category");
        String language = request.getParameter("language");
        int year = request.getParameter("year")==null ? 0 : Integer.parseInt(request.getParameter("year"));
        //默认数据
        JSONArray list = new JSONArray();
        List<RepoVO> vos;
        switch (sortCode){
            case 1: vos = service.sortRepoListByStar(keywords, page, category, language, year); break;
            case 2: vos = service.sortRepoListByFork(keywords, page, category, language, year); break;
            case 3: vos = service.sortRepoListByContributor(keywords, page, category, language, year); break;
            default: vos = service.showRepoList(keywords, page, category, language, year); break;
        }
        vos.forEach(repoVO -> {
            JSONObject single = new JSONObject();
            single.put("fullName", repoVO.fullName);
            single.put("stars", repoVO.starsCount);
            single.put("forks", repoVO.forksCount);
            single.put("contributors", repoVO.contributorsCount);
            single.put("updateTime", repoVO.updatedAt);
            single.put("description", repoVO.description);
            list.add(single);
        });
        ret.put("list", list);
        ret.put("pages", CommonUtil.getPageBox(service.getPages(keywords, category, language, year), page));
        ret.put("currentPage", String.valueOf(page));
        ret.put("currentSort", sortCode);
        //filter
        if (category==null) category = "All";
        if (language==null) language = "All";
        ret.put("currentCategory", category);
        ret.put("currentLanguage", language);
        ret.put("currentYear", year==0 ? "All" : String.valueOf(year));
        ret.put("categories", CommonLists.categories);
        ret.put("languages", CommonLists.languages);
        ret.put("years", CommonLists.years);
        
        return ret;
    }

    @RequestMapping("repos-top5")
    @ResponseBody
    public JSONObject ajaxTop5(HttpServletResponse response, HttpServletRequest request){
        JSONObject ret = new JSONObject();

        //top5
        List<RepoVO> rankList = service.getRankedRepos(5, null);
        JSONArray top5 = new JSONArray();
        for (int i = 0; i < 5; i++) {
            JSONObject obj = new JSONObject();
            obj.put("description", rankList.get(i).description);
            obj.put("stars", rankList.get(i).starsCount);
            obj.put("forks", rankList.get(i).forksCount);
            obj.put("fullName", rankList.get(i).fullName);
            top5.add(obj);
        }
        ret.put("tops", top5);

        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
