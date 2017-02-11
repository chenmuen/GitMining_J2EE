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
import util.enums.ResultMessage;
import vo.RepoVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by raychen on 16/5/28.
 */
@Controller
@RequestMapping("/")
public class ReposTestController extends BaseController {

    @Autowired
    private RepoService repoService;

    @Override
    @RequestMapping("repos-test")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {


        return "repos-test";
    }

    @Override
    @RequestMapping("repos-default") //默认数据加载
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
        //default
        JSONArray list = new JSONArray();
        List<RepoVO> vos;
//        switch (sortCode){
//            case 1: vos = repoService.sortRepoListByStar(keywords, page, category, language, year); break;
//            case 2: vos = repoService.sortRepoListByFork(keywords, page, category, language, year); break;
//            case 3: vos = repoService.sortRepoListByContributor(keywords, page, category, language, year); break;
//            default: vos = repoService.showRepoList(keywords, page, category, language, year); break;
//        }
//        vos.forEach(repoVO -> {
//            JSONObject single = new JSONObject();
//            single.put("fullName", repoVO.fullName);
//            single.put("stars", repoVO.starsCount);
//            single.put("forks", repoVO.forksCount);
//            single.put("contributors", repoVO.contributorsCount);
//            single.put("updateTime", repoVO.updatedAt);
//            single.put("description", repoVO.description);
//            list.add(single);
//        });
        ret.put("list", list);
        ret.put("pages", CommonUtil.getPageBox(repoService.getPages(keywords, category, language, year), page));
        ret.put("currentPage", String.valueOf(page));
        ret.put("currentSort", sortCode);
        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
