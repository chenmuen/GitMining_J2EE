import base.BaseTest;
import dao.LanguageDAO;
import dao.RepoDAO;
import dao.UserDAO;
import entity.Repo;
import entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.LanguageService;
import service.RepoService;
import service.UserService;
import service.evaluate.Evaluate;
import service.evaluate.Rank;
import service.utilities.CommonLists;
import util.enums.RepoSortType;
import vo.LanguageVO;
import vo.RepoVO;
import vo.UserVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/24.
 */
public class UtilTest extends BaseTest {

    @Autowired
    private RepoDAO repoDAO;
    @Autowired
    private RepoService repoService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private LanguageService languageService;

    @Test
    public void filterYear() throws Exception {
        List<Repo> repos = repoDAO.getRepoList("", RepoSortType.GENERAL, null, 2014, null, 1);
        System.out.println(repos.size());
    }

    @Test
    public void others_1() throws Exception {
        Integer[] intArray = {7, 2, 8, 1, 3, 5, 4, 6, 9, 1, 2, 2 };
        List<Integer> listOfIntegers =
                new ArrayList<>(Arrays.asList(intArray));
        List<Integer> after = listOfIntegers.stream()
                .sorted((e1,e2) -> (e2-e1))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(after.size());
    }

    @Test
    public void others_2() throws Exception {
        Map<Integer, Long> map = new HashMap<>();
        map.put(1,(long) 26);
        map.put(2,(long) 25);
        map.put(3,(long) 24);
        map.put(4,(long) 23);
        long sum = map.values().stream().mapToLong(Long::longValue).sum();
        System.out.println(sum);
    }

    @Test
    public void mapkey() throws Exception {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,1);
        map.put(1,3);
        map.forEach((k,v)-> System.out.println(k+" "+v));
    }

    @Test
    public void setTest() throws Exception {
        List<String> s1 = new LinkedList<>();
        s1.add("s1");s1.add("s2");s1.add("s3");s1.add("s4");
        List<String> s2 = new LinkedList<>();
        s2.add("s1");s2.add("s2");s2.add("s5");s2.add("s6");
        List<String> common = CommonLists.getCommonStrings(s1, s2);
        System.out.println(common.size());
    }

    @Test
    public void listSet() throws Exception {
        List<Integer> l = new LinkedList<>();
        l.add(1);l.add(2);
        l.set(l.indexOf(1), 3);
        l.forEach(i -> System.out.println(i));
    }

    @Test
    public void str() throws Exception {
        String s1 = "cap'n";
        s1 = s1.replace("'","''");
        System.out.println(s1);
    }

    @Test
    public void setScore() throws Exception {
        List<RepoVO> allRepo = repoService.getAll();
        Rank.rankRepo(allRepo);
        int count = 0;
        for (RepoVO repoVO: allRepo) {
            System.out.println(count++);
            repoVO.repo.setScore((int)(repoVO.score.final_score*10000));
        }
        List<UserVO> allUser = userService.getAll();
        Rank.rankUser(allUser);
        count = 0;
        for (UserVO userVO: allUser){
            System.out.println(count++);
            userVO.userPO.setScore((int)(userVO.score.final_score*10000));
        }
        List<LanguageVO> allvo = languageService.getAll();
        allvo.forEach(languageVO -> languageVO.single.setScore((int)(languageVO.score.final_score*10000)));
    }
}
