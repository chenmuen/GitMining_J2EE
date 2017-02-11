package service.impl.repoimpl;

import dao.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.LanguageService;
import service.RepoStatisticService;
import service.evaluate.Evaluate;
import service.impl.Utilities;
import service.utilities.CommonLists;
import vo.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/12.
 */
@Service
@Transactional
public class RepoStatisticImpl implements RepoStatisticService{

    @Autowired
    private RepoDAO repoDAO;
    @Autowired
    private LanguageDAO languageDAO;
    @Autowired
    private CommitDAO commitDAO;
    @Autowired
    private ContributorCommitsDAO contributorCommitsDAO;
    @Autowired
    private PunchDAO punchDAO;
    @Autowired
    private LanguageService languageService;

    @Override
    public List<RadarVO> getRadar(String repoName) {
        Repo repo = repoDAO.getRepo(repoName);

//        List<Repo> allRepos = repoDAO.getAll();

//        List<Integer> contributors = new LinkedList<>();
//        List<Integer> sizes = new LinkedList<>();
//        List<Integer> stars = new LinkedList<>();
//        List<Integer> issues = new LinkedList<>();
//        List<Integer> pullRequests = new LinkedList<>();
//        List<Integer> forks = new LinkedList<>();
//        allRepos.forEach(e -> {
//            contributors.add(e.getContributorsCount());
//            sizes.add(e.getSize());
//            stars.add(e.getStarsCount());
//            issues.add(e.getIssuesCount());
//            pullRequests.add(e.getPullRequestsCount());
//            forks.add(e.getForksCount());
//        });

        RadarVO conRadar = new RadarVO("contributor",
                (int) (100-repoDAO.getRanking("contributorsCount", repo.getContributorsCount())*100)/20+1,
                5
        );
        RadarVO sizeRadar = new RadarVO("size",
                (int) (100-repoDAO.getRanking("size", repo.getSize())*100)/20+1,
                5
        );
        RadarVO starRadar = new RadarVO("star",
                (int) (100-repoDAO.getRanking("starsCount", repo.getStarsCount())*100)/20+1,
                5
        );
        RadarVO issueRadar = new RadarVO("issue",
                (int) (100-repoDAO.getRanking("issuesCount", repo.getIssuesCount())*100)/20+1,
                5
        );
        RadarVO pullRadar = new RadarVO("pull request",
                (int) (100-repoDAO.getRanking("pullRequestsCount", repo.getPullRequestsCount())*100)/20+1,
                5
        );
        RadarVO forkRadar = new RadarVO("fork",
                (int) (100-repoDAO.getRanking("forksCount", repo.getForksCount())*100)/20+1,
                5
        );

        List<RadarVO> rets = new LinkedList<>();
        rets.add(conRadar);
        rets.add(sizeRadar);
        rets.add(starRadar);
        rets.add(issueRadar);
        rets.add(pullRadar);
        rets.add(forkRadar);
        return rets;
    }

    @Override
    public List<LanguageStaVO> getRepoLanguage(String repoName) {
        List<LanguageJoinVO> languages = languageService.toJoinList(languageDAO.getLanguage(repoName));
        CommonLists.filterLan(languages);//filter languages
        Long sum = languages.stream()
                .mapToLong(value -> value.size)
                .reduce(0, (x,y) -> (x+y));
        List<LanguageStaVO> vos = languages.stream()
                .map(e -> new LanguageStaVO(e.name, (double) e.size / sum, e.size))
                .collect(Collectors.toList());
        return vos;
    }

    @Override
    public List<CommitVO> getCommitsRepo(String repoName) {
        List<Commit> commits = commitDAO.getCommitsByRepo(repoName);
        Collections.sort(commits, (o1, o2) -> Utilities.get_long_comparator().compare(o1.getWeek(), o2.getWeek()));
        Map<Long, List<Commit>> commits_map = commits.stream()
                .collect(Collectors.groupingBy(e -> e.getWeek()));
        List<CommitVO> commitVOs = new LinkedList<>();
        commits_map.forEach((l, list) -> {
            commitVOs.add(new CommitVO(list, repoName, l));
        });
        return commitVOs;
    }

    @Override
    public List<ContributorCommitsVO> getCommitsContributors(String repoName) {
        List<ContributorCommits> commitUsers = contributorCommitsDAO.getCommits(repoName);
        List<ContributorCommitsVO> rets = new LinkedList<>();
        commitUsers.forEach(contributorCommits -> {
            List<Commit> commits = commitDAO.getCommitsOfContributor(contributorCommits.getRepoFullName(), contributorCommits.getContributorLogin());
            Collections.sort(commits, (e1,e2) -> Utilities.get_long_comparator().compare(e1.getWeek(),e2.getWeek()));
            List<CommitVO> vos = commits.stream()
                    .map(e -> new CommitVO(e))
                    .collect(Collectors.toList());
            if (vos.size() >= 10) rets.add(new ContributorCommitsVO(vos, contributorCommits.getContributorLogin()));
        });
        Collections.sort(rets, (o1, o2) -> o2.getCommits().size() - o1.getCommits().size());
        return rets;
    }

    @Override
    public List<PunchVO> getPunches(String repoName) {
        List<Punch> punches = punchDAO.getPunchCard(repoName);
        List<PunchVO> ret = punches.stream()
                .map(e -> new PunchVO(e))
                .collect(Collectors.toList());
        return ret;
    }

    @Override
    public List<LanguageStaVO> getAllLanguage() {
        List<LanguageStaVO> vos = new LinkedList<>();
        List<Map> maps = languageDAO.getSize();
        long sum = maps.stream().mapToLong(map -> (long) map.get("size")).sum();
        for (int i = 0; i < 15; i++) {
            Map map = maps.get(i);
            long mysize = (long) map.get("size");
            vos.add(new LanguageStaVO((String) map.get("language"), (double) mysize/sum, mysize));
        }
        long others = 0;
        for (int i = 15; i < maps.size(); i++) {
            Map map = maps.get(i);
            long mysize = (long) map.get("size");
            others += mysize;
        }
        vos.add(new LanguageStaVO("Others", (double)others/sum , others));
        return vos;
    }

    @Override
    public List<SectorVO> getReposCreateAt() {
        List<Object> propertyList = repoDAO.getPropertyList("createdAt", true);
        List<Object> times = propertyList.stream()
                .map(o -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date((long) o));
                    return cal.get(Calendar.YEAR);
                })
                .collect(Collectors.toList());
        return Utilities.getListSectorVO(times);
    }

    @Override
    public List<SectorVO> getReposStar() {
        List<Object> propertyList = repoDAO.getPropertyList("starsCount", true);
        return Utilities.getListSectorVO(propertyList);
    }

    @Override
    public List<SectorVO> getReposFork() {
        List<Object> propertyList = repoDAO.getPropertyList("forksCount", true);
        return Utilities.getListSectorVO(propertyList);
    }

    @Override
    public List<SectorVO> getRepoContritutor() {
        List<Object> propertyList = repoDAO.getPropertyList("contributorsCount", true);
        return Utilities.getListSectorVO(propertyList);
    }

    @Override
    public List<SectorVO> getRepoCollaborator() {
        List<Object> propertyList = repoDAO.getPropertyList("collaboratorsCount", true);
        return Utilities.getListSectorVO(propertyList);
    }
}
