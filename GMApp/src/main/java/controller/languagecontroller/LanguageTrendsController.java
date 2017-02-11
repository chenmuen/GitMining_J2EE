package controller.languagecontroller;

import controller.BaseController;
import controller.uitities.CommonUtil;
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
import util.LanguageTrends;
import util.enums.LangTrendsTypeByYear;
import util.enums.ResultMessage;
import vo.LanguageVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by raychen on 16/5/31.
 */
@Controller
@RequestMapping("/")
public class LanguageTrendsController extends BaseController {

    @Autowired
    private LanguageService languageService;

    @Override
    @RequestMapping("lang-trends")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        String lang_name = request.getParameter("langName");

        if (lang_name!=null){
            LanguageTrends trends = languageService.getTrends(lang_name);
            JSONObject ranking = CommonUtil.getTrendsSectors(trends);
            model.addAttribute("ranking", ranking);
        }
        model.addAttribute("language", lang_name);

        return "lang-trends";
    }

    @RequestMapping("lang-trends-repo")
    @ResponseBody
    public JSONObject ajaxRepo(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String lang_name = request.getParameter("langName");
        if (lang_name == null) return null;
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;

        JSONObject repos = new JSONObject();

        JSONArray langNames = new JSONArray();
        langNames.add(lang_name);
        JSONArray yearArray = new JSONArray();
        JSONArray reposData = new JSONArray();
        Map<Integer, Long> yearsdata = languageService.getStatByTime(lang_name, LangTrendsTypeByYear.REPOS);
        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            int year = Integer.parseInt(years[i]);
            if (yearsdata.get(year)==null) continue;
            yearArray.add(year);
            reposData.add(yearsdata.get(year));
        }
        repos.put("langName", langNames);
        repos.put("actRepoYears", yearArray);
        repos.put("actRepoData", reposData);
        ret.put("repos", repos);
        return ret;
    }

    @RequestMapping("lang-trends-star")
    @ResponseBody
    public JSONObject ajaxStar(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String lang_name = request.getParameter("langName");
        if (lang_name == null) return null;
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;

        JSONObject stars = new JSONObject();

        JSONArray langNames = new JSONArray();
        langNames.add(lang_name);
        JSONArray yearArray = new JSONArray();
//        JSONArray reposData = new JSONArray();
        JSONArray starsData = new JSONArray();

        Map<Integer, Long> yearsdata = languageService.getStatByTime(lang_name, LangTrendsTypeByYear.STARS);

        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            int year = Integer.parseInt(years[i]);
            if (yearsdata.get(year)==null) continue;
            yearArray.add(year);
            starsData.add(yearsdata.get(year));
        }
        stars.put("langName", langNames);
        stars.put("totalStarsYears", yearArray);
        stars.put("totalStarsData", starsData);
        ret.put("stars", stars);
        return ret;
    }

    @RequestMapping("lang-trends-fork")
    @ResponseBody
    public JSONObject ajaxFork(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String lang_name = request.getParameter("langName");
        if (lang_name == null) return null;
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;

        JSONObject forks = new JSONObject();

        JSONArray langNames = new JSONArray();
        langNames.add(lang_name);
        JSONArray yearArray = new JSONArray();
//        JSONArray reposData = new JSONArray();
//        JSONArray starsData = new JSONArray();
        JSONArray forksData = new JSONArray();

        Map<Integer, Long> yearsdata = languageService.getStatByTime(lang_name, LangTrendsTypeByYear.FORKS);

        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            int year = Integer.parseInt(years[i]);
            if (yearsdata.get(year)==null) continue;
            yearArray.add(year);
            forksData.add(yearsdata.get(year));
        }
        forks.put("langName", langNames);
        forks.put("newForkPRYears", yearArray);
        forks.put("newForkPRData", forksData);
        ret.put("forks", forks);
        return ret;
    }

    @RequestMapping("lang-trends-watcher")
    @ResponseBody
    public JSONObject ajaxWatcher(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String lang_name = request.getParameter("langName");
        if (lang_name == null) return null;
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;

        JSONObject watches = new JSONObject();

        JSONArray langNames = new JSONArray();
        langNames.add(lang_name);
        JSONArray yearArray = new JSONArray();
        JSONArray watchesData = new JSONArray();

        Map<Integer, Long> yearsdata = languageService.getStatByTime(lang_name, LangTrendsTypeByYear.WATCHES);

        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            int year = Integer.parseInt(years[i]);
            if (yearsdata.get(year)==null) continue;
            yearArray.add(year);
            watchesData.add(yearsdata.get(year));
        }
        watches.put("langName", langNames);
        watches.put("newWatchersPRYears", yearArray);
        watches.put("newWatchersPRData", watchesData);
        ret.put("watches", watches);
        return ret;
    }

    @RequestMapping("lang-trends-issue")
    @ResponseBody
    public JSONObject ajaxIssue(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String lang_name = request.getParameter("langName");
        if (lang_name == null) return null;
        LanguageVO languageVO = languageService.getLanguage(lang_name);
        if (languageVO == null) return null;

        JSONObject issues = new JSONObject();

        JSONArray langNames = new JSONArray();
        langNames.add(lang_name);
        JSONArray yearArray = new JSONArray();
        JSONArray issuesData = new JSONArray();

        Map<Integer, Long> yearsdata = languageService.getStatByTime(lang_name, LangTrendsTypeByYear.ISSUES);

        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            int year = Integer.parseInt(years[i]);
            if (yearsdata.get(year)==null) continue;
            yearArray.add(year);
            issuesData.add(yearsdata.get(year));
        }
        issues.put("langName", langNames);
        issues.put("newOpenIssuePRYears", yearArray);
        issues.put("newOpenIssuePRData", issuesData);
        ret.put("issues", issues);
        return ret;
    }

    @Override
    @RequestMapping("lang-trends-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
