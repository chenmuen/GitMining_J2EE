package servicetest;

import base.BaseTest;
import dao.RepoDAO;
import entity.RepoTag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.RepoAnalyzeService;
import service.RepoService;
import service.RepoStatisticService;
import service.evaluate.Rank;
import util.enums.MyPoint;
import util.enums.AnalyType;
import vo.*;

import java.util.List;

/**
 * Created by raychen on 16/6/15.
 */
public class RepoFinalTest extends BaseTest {

    @Autowired
    private RepoService repoService;
    @Autowired
    private RepoStatisticService repostat;
    @Autowired
    private RepoDAO repoDAO;
    @Autowired
    private RepoAnalyzeService analyzeService;

    @Test
    public void repo() throws Exception {
        List<RepoTag> tags = repoDAO.getRepoTags("robbyrussell/oh-my-zsh");
        System.out.println(tags.size());
//        List<RepoVO> relatedByTags = repoService.getRelatedByTags("mojombo/god");
//        System.out.println(relatedByTags.size());
    }

    @Test
    public void repoRadar() throws Exception {
        List<RadarVO> vos = repostat.getRadar("mojombo/grit");
        System.out.println(vos.size());
    }

    @Test
    public void repoRank() throws Exception {
        List<RepoVO> allVOs = repoService.getAll();//.stream().limit(1000).collect(Collectors.toList());
        Rank.rankRepo(allVOs);
        for (int i = 0; i < 100; i++) {
            System.out.println(allVOs.get(i).fullName+": "+allVOs.get(i).score.final_score);
        }
//        for (int i = 0; i < 100; i++) {
//            double j = Math.pow(100, 1.0-1.0/Math.pow(allVOs.get(i).issuesCount,0.5));
//            double k = 100.0/(double) allVOs.get(i).cretMonths+1;
//            System.out.println(j+" "+k+" "+allVOs.get(i).cretMonths+" "+allVOs.get(i).createdAt);
//        }
//        System.out.println(i);
    }

    @Test
    public void scatter() throws Exception {
        List<MyPoint> points = analyzeService.getScatterMaps(AnalyType.FORK, AnalyType.STAR);
        System.out.println(points.size());
    }

    @Test
    public void stat() throws Exception {
        List<LanguageStaVO> langstat = repostat.getAllLanguage();
        System.out.println(langstat.size());
    }

    @Test
    public void ana() throws Exception {
        List<MyPoint> points = analyzeService.getScatterMaps(AnalyType.STAR,AnalyType.REPO_SCORE);
        System.out.println(points);
    }
}
