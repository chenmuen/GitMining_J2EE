package controller.repocontroller;

//import com.sun.deploy.net.HttpRequest;
//import com.sun.deploy.net.HttpResponse;
import controller.BaseController;
import controller.uitities.ChartUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RepoService;
import service.RepoStatisticService;
import util.enums.ResultMessage;
import vo.RepoVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Lenovo on 2016/5/12.
 */
@Controller
@RequestMapping("/")
public class RepoController extends BaseController {

    @Autowired
    private RepoStatisticService repoStatisticService;
    @Autowired
    private RepoService repoService;

    @Override
    @RequestMapping("repo")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "repo";
    }

    @Override
    @RequestMapping("repo-ajax")
    @ResponseBody
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        
        String repoName = request.getParameter("repo");
        if (repoName == null) return null; // 404
        RepoVO repo = repoService.showRepo(repoName);
        if (repo == null) return null; // 404

        //默认数据
        ret.put("owner", repo.owner);
        ret.put("name", repo.repoName);
        ret.put("description", repo.description);
        ret.put("updateTime", repo.updatedAt);
        ret.put("cloneUrl", repo.cloneUrl);
        ret.put("stars", repo.starsCount);
        ret.put("forks", repo.forksCount);
        ret.put("contributors", repo.contributorsCount);
        ret.put("subscribers", repo.subscribersCount);
        ret.put("collaborators", repo.collaboratorsCount);

        return ret;
    }

    @RequestMapping("repo-related")
    @ResponseBody
    public JSONObject ajaxRelated(HttpServletResponse response, HttpServletRequest request){
        JSONObject ret = new JSONObject();

        String repoName = request.getParameter("repo");
        if (repoName == null) return null; // 404
        RepoVO repo = repoService.showRepo(repoName);
        if (repo == null) return null; // 404

        //related repos
        List<RepoVO> relatedOwner = repoService.getRelatedByOwner(repo.owner, repo.fullName);
        List<RepoVO> relatedTag = repoService.getRelatedByTags(repo.fullName);
        JSONArray relOwner = new JSONArray();
        JSONArray relTag = new JSONArray();
        for (int i = 0; i < relatedOwner.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("stars", relatedOwner.get(i).starsCount);
            obj.put("forks", relatedOwner.get(i).forksCount);
            obj.put("fullName", relatedOwner.get(i).fullName);
            relOwner.add(obj);
        }
        for (int i = 0; i < relatedTag.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("stars", relatedTag.get(i).starsCount);
            obj.put("forks", relatedTag.get(i).forksCount);
            obj.put("fullName", relatedTag.get(i).fullName);
            relTag.add(obj);
        }
        ret.put("relatedsByOwner", relOwner);
        ret.put("relatedsByTags", relTag);

        return ret;
    }

    @RequestMapping("repo-radar")
    @ResponseBody
    public JSONObject ajaxRadar(HttpServletResponse response, HttpServletRequest request){
        JSONObject ret = new JSONObject();

        String repoName = request.getParameter("repo");
        if (repoName == null) return null; // 404
//        RepoVO repo = repoService.showRepo(repoName);
//        if (repo == null) return null; // 404

        //雷达图属性
        JSONArray value = new JSONArray();
        JSONArray scoreIndicator = new JSONArray();
        ChartUtil.getRadarData(value, scoreIndicator, repoStatisticService.getRadar(repoName));
        ret.put("values",value);
        ret.put("scoreIndicator",scoreIndicator);

        return ret;
    }

    @RequestMapping("repo-lang")
    @ResponseBody
    public JSONObject ajaxLang(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();

        String repoName = request.getParameter("repo");
        if (repoName == null) return null; // 404
//        RepoVO repo = repoService.showRepo(repoName);
//        if (repo == null) return null; // 404

        //语言饼图属性
        JSONArray lanName = new JSONArray();
        JSONArray languageArray = new JSONArray();
        ChartUtil.getLauguageData(lanName, languageArray, repoStatisticService.getRepoLanguage(repoName));
        ret.put("lanName",lanName);
        ret.put("lanData",languageArray);

        return ret;
    }

    @RequestMapping("repo-ad")
    @ResponseBody
    public JSONObject ajaxAD(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();

        String repoName = request.getParameter("repo");
        if (repoName == null) return null; // 404
//        RepoVO repo = repoService.showRepo(repoName);
//        if (repo == null) return null; // 404

        //项目代码行数增减折线图数据
        JSONArray adDate = new JSONArray();
        JSONArray aData = new JSONArray();
        JSONArray dData = new JSONArray();
        ChartUtil.getAddtionDeletionData(adDate, aData, dData, repoStatisticService.getCommitsRepo(repoName));
        ret.put("adDate",adDate);
        ret.put("aData",aData);
        ret.put("dData",dData);

        return ret;
    }

    @RequestMapping("repo-punch")
    @ResponseBody
    public JSONObject ajaxPunch(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();

        String repoName = request.getParameter("repo");
        if (repoName == null) return null; // 404
//        RepoVO repo = repoService.showRepo(repoName);
//        if (repo == null) return null; // 404

        //punch card数据
        ret.put("punchData", ChartUtil.getPunchData(repoStatisticService.getPunches(repoName)));

        return ret;
    }

    @RequestMapping("repo-commit")
    @ResponseBody
    public JSONObject ajaxCommit(HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();

        String repoName = request.getParameter("repo");
        if (repoName == null) return null; // 404
//        RepoVO repo = repoService.showRepo(repoName);
//        if (repo == null) return null; // 404

        //member-commit 数据
        JSONArray memName = new JSONArray();
        JSONArray memCDate = new JSONArray();
        JSONArray memData = new JSONArray();
        ChartUtil.getContributorCommits(memName, memCDate, memData, repoStatisticService.getCommitsContributors(repoName));
        ret.put("memName", memName);
        ret.put("memCDate", memCDate);
        ret.put("memData", memData);

        return ret;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
