package service.evaluate;

import org.springframework.beans.factory.annotation.Autowired;
import service.LanguageService;
import service.impl.languageimpl.LanguageImpl;
import util.enums.LangTrendsTypeByYear;
import vo.LanguageVO;
import vo.RepoVO;
import vo.UserVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/18.
 */

public class Rank {

    private static LanguageService languageService = new LanguageImpl();

    /**
     * set score in repoVO
     *
     * @param repoVOs
     */
    public static void rankRepo(List<RepoVO> repoVOs) {
        List<Integer> starList = new LinkedList<>();
        List<Integer> forkList = new LinkedList<>();
        List<Integer> cretList = new LinkedList<>();
        List<Integer> conList = new LinkedList<>();
        repoVOs.forEach(repoVO -> {
            starList.add(repoVO.starsCount);
            forkList.add(repoVO.forksCount);
            cretList.add((int) repoVO.starsPerYear);
            conList.add(repoVO.contributorsCount);
        });
        List<Integer> distinctStar = starList.stream().distinct().sorted().collect(Collectors.toList());
        List<Integer> distinctFork = forkList.stream().distinct().sorted().collect(Collectors.toList());
        List<Integer> distinctCon = conList.stream().distinct().sorted().collect(Collectors.toList());
        List<Integer> distinctCret = cretList.stream().distinct().sorted().collect(Collectors.toList());
        repoVOs.forEach(repoVO -> {
            repoVO.score.putParameter("star", (double) distinctStar.indexOf(repoVO.starsCount) / distinctStar.size());
            repoVO.score.putParameter("fork", (double) distinctFork.indexOf(repoVO.forksCount) / distinctFork.size());
            repoVO.score.putParameter("contributiors", (double) distinctCon.indexOf(repoVO.contributorsCount) / distinctCon.size());
            repoVO.score.putParameter("cret", (double) distinctCret.indexOf(repoVO.starsPerYear) / distinctCret.size());
            repoVO.score.putParameter("issue", (double) repoVO.issuesCount);
            repoVO.score.calculateScore();
        });
        Collections.sort(repoVOs, new Comparator<RepoVO>() {
            @Override
            public int compare(RepoVO o1, RepoVO o2) {
                if (o1.score.final_score < o2.score.final_score) return 1;
                if (o1.score.final_score == o2.score.final_score) return 0;
                return -1;
            }
        });
    }

    /**
     * set score in uservo
     *
     * @param userVOs
     */
    public static void rankUser(List<UserVO> userVOs) {
        List<Integer> followerList = new LinkedList<>();
        List<Integer> repoList = new LinkedList<>();
        userVOs.forEach(userVO -> {
            followerList.add(userVO.followersCount);
            repoList.add(userVO.repo_score);
        });
        List<Integer> disfollower = followerList.stream().distinct().sorted().collect(Collectors.toList());
        List<Integer> disrepo = repoList.stream().distinct().sorted().collect(Collectors.toList());
        userVOs.forEach(userVO -> {
            userVO.score.putParameter("follower", (double) disfollower.indexOf(userVO.followersCount) / disfollower.size());
            userVO.score.putParameter("repo", (double) disrepo.indexOf(userVO.repo_score) / disrepo.size());
            userVO.score.calculateScore();
        });
        Collections.sort(userVOs, new Comparator<UserVO>() {
            @Override
            public int compare(UserVO o1, UserVO o2) {
                if (o1.score.final_score < o2.score.final_score) return 1;
                return -1;
            }
        });
    }

    /**
     * set score in languagevo
     * <p>
     * before: evaluate.calculate
     *
     * @param languageVOs
     * @param year:       -1 means all
     */
    public static void rankLanguage(List<LanguageVO> languageVOs, int year) {

//        long sum = languageVOs.stream().mapToLong(value -> value.allRepos).sum();
//        if (year > 0) sum = languageVOs.stream().mapToLong(value -> value.reposOfYear.get(year)).sum();
//        if (sum == 0) return ;
        List<Long> disRepos = languageVOs.stream()
                .map(lang -> year > 0 ? lang.reposOfYear.get(year) : lang.allRepos)
                .distinct().sorted().collect(Collectors.toList());
        List<Long> disUsers = languageVOs.stream()
                .map(lang -> year > 0 ? lang.usersOfYear.get(year) : lang.allUsers)
                .distinct().sorted().collect(Collectors.toList());
        for (LanguageVO lanVO : languageVOs) {
//            Map map = languageService.getStatByTime(lanVO.languageName, LangTrendsTypeByYear.SCORE);
//            lanVO.score.putParameter("repo_score", (long) map.get(year));
            if (year > 0) {
                lanVO.score.putParameter("repo", (double) disRepos.indexOf(lanVO.reposOfYear.get(year)) / disRepos.size());
                lanVO.score.putParameter("user", (double) disUsers.indexOf(lanVO.usersOfYear.get(year)) / disUsers.size());
            } else {
                lanVO.score.putParameter("repo", (double) disRepos.indexOf(lanVO.allRepos) / disRepos.size());
                lanVO.score.putParameter("user", (double) disUsers.indexOf(lanVO.allUsers) / disUsers.size());
            }

            lanVO.score.calculateScore();
        }

        Collections.sort(languageVOs, new Comparator<LanguageVO>() {
            @Override
            public int compare(LanguageVO o1, LanguageVO o2) {
                if (o1.score.final_score < o2.score.final_score) return 1;
                if (o1.score.final_score == o2.score.final_score) return 0;
                return -1;
            }
        });
        if (year > 0)
            languageVOs.forEach(languageVO -> {
                if (languageVO.scoreOfYear.containsKey(year))
                    languageVO.scoreOfYear.replace(year, languageVOs.indexOf(languageVO) + 1);
                else languageVO.scoreOfYear.put(year, languageVOs.indexOf(languageVO) + 1);
            });
    }
}
