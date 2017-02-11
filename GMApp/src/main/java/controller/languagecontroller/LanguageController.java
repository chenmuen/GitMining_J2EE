package controller.languagecontroller;

import controller.BaseController;
import controller.uitities.CommonUtil;
import dao.SingleLanguageDAO;
import entity.RecommendCourse;
import entity.RecommendSite;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.python.antlr.op.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LanguageService;
import service.utilities.CommonLists;
import util.LanguageRelation;
import util.LanguageTrends;
import util.enums.ResultMessage;
import vo.LanguageVO;
import vo.RepoVO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by raychen on 16/5/22.
 * 语言详情界面
 */
@Controller
@RequestMapping("/")
public class LanguageController extends BaseController {

    @Autowired
    private LanguageService languageService;
    @Autowired
    private SingleLanguageDAO singleLanguageDAO;

    @Override
    @RequestMapping("lang")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        String lang_name = request.getParameter("lang");
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;
        int cover = (int)(9*Math.random() + 1);
        int courseCover = (int)(9*Math.random() + 1);

        RecommendCourse recommendCourse = singleLanguageDAO.getRecommendCourse(lang_name);
        List<RecommendSite> sites = singleLanguageDAO.getRecommendSite(lang_name);
        JSONArray websites = new JSONArray();

        for (RecommendSite site: sites) {
            JSONObject web = new JSONObject();
            web.put("id", site.getId());
            web.put("link", site.getSiteUrl());
            web.put("img", site.getSiteImg());
            websites.add(web);
        }

//        System.out.println(websites.size());
        model.addAttribute("websites", websites);
        model.addAttribute("courseName", recommendCourse.getCourseName());
        model.addAttribute("courseCover", courseCover);
        model.addAttribute("courseUrl", recommendCourse.getCourseUrl());
        model.addAttribute("courseUni", recommendCourse.getSchool());
        model.addAttribute("language", lang_name);
        model.addAttribute("cover",cover);

        String hello = languageVO.helloworld;
//        hello.replace("<", "&lt;").replace(">", "&gt;").replace("\n", "<br/>").replace(" ", "&nbsp;");

        model.addAttribute("applications", languageVO.tags_apps);
        model.addAttribute("repoCount", languageVO.repos);
        model.addAttribute("devCount", languageVO.users);
        model.addAttribute("wiki", languageVO.wiki);
        model.addAttribute("helloWorld", hello);
        model.addAttribute("quoteContent", languageVO.quoteContent);
        model.addAttribute("quoteFrom", languageVO.quoteFrom);
        return "lang";
    }

    /**
     * 基本数据
     * @param response
     * @param request
     * @return
     */
    @Override
    @RequestMapping("lang-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String lang_name = request.getParameter("langName");
        if (lang_name == null) return null;

        ret.put("language", lang_name);
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;
        ret.put("applications", languageVO.tags_apps);
        ret.put("repoCount", languageVO.repos);
        ret.put("devCount", languageVO.users);
        ret.put("wiki", languageVO.wiki);
        ret.put("helloWorld", languageVO.helloworld);
        ret.put("quoteContent", languageVO.quoteContent);
        ret.put("quoteFrom", languageVO.quoteFrom);

        return ret;
    }

    /**
     * 单个语言相关仓库和用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("lang-ajax-related")
    @ResponseBody
    public JSONObject ajaxRelated(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();
        String lang_name = request.getParameter("langName");
        if (lang_name == null) return null;
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;
        //related repo
        List<RepoVO> repos = languageService.getMostRelatedRepos(lang_name);
        JSONArray listRepo = new JSONArray();
        repos.forEach(repoVO -> {
            JSONObject obj = new JSONObject();
            obj.put("stars", repoVO.starsCount);
            obj.put("forks", repoVO.forksCount);
            obj.put("description", repoVO.description);
            obj.put("fullName", repoVO.fullName);
            listRepo.add(obj);
        });
        ret.put("listRepo", listRepo);
        //related user
        List<UserVO> users = languageService.getMostRelatedUsers(lang_name);
        JSONArray listTopDev = new JSONArray();
        for (int i = 0; i < users.size(); i++) {
            UserVO userVO = users.get(i);
            JSONObject obj = new JSONObject();
            obj.put("rank", i+1);
            obj.put("name", userVO.login);
            obj.put("stars", userVO.score.final_score);
            obj.put("avatar", userVO.avatarUrl);
            listTopDev.add(obj);
        }
        ret.put("listTopDev", listTopDev);

        return ret;
    }

    /**
     * 单个语言趋势
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("lang-ajax-trend")
    @ResponseBody
    public JSONObject ajaxTrend(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();
        String lang_name = request.getParameter("langName");
        if (lang_name == null) return null;
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;

        LanguageTrends trends = languageService.getTrends(lang_name);

        ret = CommonUtil.getTrendsSectors(trends);
        return ret;
    }

    @RequestMapping("lang-ajax-relation")
    @ResponseBody
    public JSONObject ajaxRelation(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();
        String lang_name = request.getParameter("langName");
        if (lang_name == null) return null;
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;

        LanguageRelation relation = languageService.relatedNodes(lang_name);
        return CommonUtil.getSingleRelationFromRelation(relation);
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
