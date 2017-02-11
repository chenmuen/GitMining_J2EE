package service.impl.userimpl;

import dao.CommitDAO;
import dao.ContributorCommitsDAO;
import dao.LanguageDAO;
import dao.UserDAO;
import entity.Commit;
import entity.ContributorCommits;
import entity.Language;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.LanguageService;
import service.UserStatisticService;
import service.evaluate.Evaluate;
import service.impl.Utilities;
import service.utilities.CommonLists;
import util.enums.UserType;
import vo.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/12.
 */
@Service
@Transactional
public class UserStatisticImpl implements UserStatisticService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private LanguageDAO languageDAO;
    @Autowired
    private ContributorCommitsDAO contributorCommitsDAO;
    @Autowired
    private CommitDAO commitDAO;
    @Autowired
    private LanguageService languageService;

    @Override
    public List<RadarVO> getRadar(String userName) {
        User user = userDAO.getUser(userName);
//        List<User> allUsers = userDAO.getAll();
//        List<Integer> repoNums = new LinkedList<>();
//        List<Integer> stars = new LinkedList<>();
//        List<Integer> gists = new LinkedList<>();
//        List<Integer> followers = new LinkedList<>();
//        List<Integer> followings = new LinkedList<>();
//        allUsers.forEach(singleUser -> {
//            repoNums.add(singleUser.getPublicRepo());
//            stars.add(singleUser.getStarredCount());
//            gists.add(singleUser.getPublicGists());
//            followers.add(singleUser.getFollowersCount());
//            followings.add(singleUser.getFollowingsCount());
//        });
        RadarVO repoRadar = new RadarVO("repoNum",
                (int) (100-userDAO.getRanking("publicRepo", user.getPublicRepo())*100)/20+1,
                5);
        RadarVO starRadar = new RadarVO("star",
                (int) (100-userDAO.getRanking("starredCount", user.getStarredCount())*100)/20+1,
                5);
        RadarVO gistRadar = new RadarVO("gist",
                (int) (100-userDAO.getRanking("publicGists", user.getPublicGists())*100)/20+1,
                5);
        RadarVO ferRadar = new RadarVO("follower",
                (int) (100-userDAO.getRanking("followersCount", user.getFollowersCount())*100)/20+1,
                5);
        RadarVO fingRadar = new RadarVO("following",
                (int) (100-userDAO.getRanking("followingsCount", user.getFollowingsCount())*100)/20+1,
                5);
        List<RadarVO> vos = new LinkedList<>();
        vos.add(repoRadar);
        vos.add(starRadar);
        vos.add(gistRadar);
        vos.add(ferRadar);
        vos.add(fingRadar);
        return vos;
    }

    @Override
    public List<LanguageStaVO> getUserLanguage(String userName) {
        List<LanguageJoinVO> languages = languageService.toJoinList(languageDAO.getLanguageByUser(userName));
        CommonLists.filterLan(languages);//filter languages
        Long sum = languages.stream().mapToLong(e -> e.size).sum();
        Map<String, List<LanguageJoinVO>> language_map = languages.stream()
                .collect(Collectors.groupingBy(e -> e.name));
        List<LanguageStaVO> languageStaVOs = new LinkedList<>();
        language_map.forEach((s, list) -> {
            Long single = list.stream().mapToLong(e -> e.size).sum();
            languageStaVOs.add(new LanguageStaVO(s, (double) single/sum, single));
        });
        return languageStaVOs;
    }

    @Override
    public List<RateVO> getContributeRates(String userName) {
        List<RateVO> vos = new LinkedList<>();
        List<String> repoNames = userDAO.getRepoNamesOfUser(userName);
        repoNames.forEach(repoName -> {
            List<ContributorCommits> contributorCommitses = contributorCommitsDAO.getCommits(repoName);
            Integer sum = contributorCommitses.stream()
                    .mapToInt(ContributorCommits::getTotal)
                    .sum();
            Integer single = contributorCommitses.stream()
                    .filter(e -> e.getContributorLogin().equals(userName))
                    .mapToInt(ContributorCommits::getTotal)
                    .sum();
            vos.add(new RateVO(repoName, (double) single / sum, single));
        });
        return vos;
    }

    @Override
    public List<CommitVO> getCommits(String userName) {
        List<Commit> commits = commitDAO.getCommitByUser(userName);
        List<CommitVO> commitVOs = new LinkedList<>();
        Map<Long, List<Commit>> commit_map = commits.stream()
                .collect(Collectors.groupingBy(e -> e.getWeek()));
        commit_map.forEach((l, list) -> {
            commitVOs.add(new CommitVO(list, userName, l));
        });
        return commitVOs;
    }

    @Override
    public List<RateVO> getCompanyRates() {
        List<RateVO> rates = new LinkedList<>();
        List<Object> companiesOrigin = userDAO.getPropertyList("company", false);
        List<String> companies = CommonLists.filterCom(companiesOrigin); // filter companies
        Map<String, Long> rates_map = companies.stream()
                .collect(Collectors.groupingBy(o -> o, Collectors.counting()));
        rates_map.forEach((s, l) -> {
            rates.add(new RateVO(s, Integer.parseInt(l.toString()), Integer.parseInt(l.toString())));
        });
        return rates;
    }

    @Override
    public List<RateVO> getTypeRates() {
        List<RateVO> rates = new LinkedList<>();
        List<Object> types = userDAO.getPropertyList("type", false);
        Map<String, Long> rates_map = types.stream()
                .collect(Collectors.groupingBy(o -> ((UserType) o).toString(), Collectors.counting()));
        rates_map.forEach((s, l) -> {
            rates.add(new RateVO(s, Integer.parseInt(l.toString()), Integer.parseInt(l.toString())));
        });
        return rates;
    }

    @Override
    public List<SectorVO> getRepoSector() {
        List<Object> repos = userDAO.getPropertyList("publicRepo", true);
        return Utilities.getListSectorVO(repos);
    }

    @Override
    public List<SectorVO> getGistSector() {
        List<Object> gists = userDAO.getPropertyList("publicGists", true);
        return Utilities.getListSectorVO(gists);
    }

    @Override
    public List<SectorVO> getFollowerSector() {
        List<Object> followersCount = userDAO.getPropertyList("followersCount", true);
        return Utilities.getListSectorVO(followersCount);
    }

    @Override
    public List<SectorVO> getFollowingSector() {
        List<Object> followingsCount = userDAO.getPropertyList("followingsCount", true);
        return Utilities.getListSectorVO(followingsCount);
    }

    @Override
    public List<SectorVO> getCreateAtSector() {
        List<Object> createAt = userDAO.getPropertyList("createAt", true);
        List<Object> years = createAt.stream()
                .map(o -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date((long) o));
                    return cal.get(Calendar.YEAR);
                })
                .collect(Collectors.toList());
        return Utilities.getListSectorVO(years);
    }
}
