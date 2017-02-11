package servicetest;

import base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.RepoStatisticService;
import vo.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by raychen on 16/5/24.
 */
public class RepoStatServiceTest extends BaseTest {

    @Autowired
    private RepoStatisticService repoStatService;

    @Test
    public void repoRadar() throws Exception {
        List<RadarVO> radar = repoStatService.getRadar("mojombo/grit");
        System.out.println(radar.size());
        radar.forEach(e -> System.out.println(e.getValue()));
    }

    @Test
    public void languageRate() throws Exception {
        List<LanguageStaVO> rates = repoStatService.getRepoLanguage("jquery/jquery");
        rates.forEach(rate -> {
            System.out.println("language rates:"+rate.getLanguage_name()+" "+rate.getRate());
        });
    }

    @Test
    public void commits() throws Exception {
        List<ContributorCommitsVO> commits = repoStatService.getCommitsContributors("mojombo/grit");
        System.out.println("all size:"+commits.size());
        commits.forEach(commit -> {
            List<CommitVO> vos = commit.getCommits();
            System.out.println("--"+vos.size());
        });
    }

    @Test
    public void adtime() throws Exception {
        List<CommitVO> ads = repoStatService.getCommitsRepo("mojombo/grit");
//        List<Commit> addaos = commitDAO.getCommitsByRepo("mojombo/grit");
        ads.forEach(ad -> {
//            Date date = ad.time;
            Calendar cal = ad.time;
//            Date date = new Date(ad.getWeek()*1000);
            System.out.println(cal.get(Calendar.YEAR)+" "+cal.get(Calendar.DATE));
        });
    }

    @Test
    public void statisticSector() throws Exception {
        List<SectorVO> sectorVOs = repoStatService.getReposCreateAt();
        Collections.sort(sectorVOs, (o1, o2) -> o1.sector-o2.sector);
        sectorVOs.forEach(e -> {
            System.out.println(e.name+" --- "+e.nums);
        });
    }
}
