package servicetest;

import base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.RepoService;
import service.UserService;
import service.evaluate.Rank;
import service.impl.repoimpl.RepoImpl;
import vo.RepoVO;
import vo.UserVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by raychen on 16/5/24.
 */
public class RepoServiceTest extends BaseTest {

    @Autowired
    private RepoService repoService;
    @Autowired
    private UserService userService;

    @Test
    public void rankRepo() throws Exception {
        List<RepoVO> repos = repoService.getAll();
        Rank.rankRepo(repos);
        for (int i = 0; i < 50; i++) {
            RepoVO repoVO = repos.get(i);
            System.out.println(repoVO.fullName+" owner: "+repoVO.owner);
            UserVO user = userService.showUser(repoVO.owner);
        }
    }

    @Test
    public void find() throws Exception {
        List<Integer> tes = new LinkedList<>();
        RepoImpl impl = new RepoImpl();
        tes.add(4);
        tes.add(3);
        tes.add(6);
        tes.add(7);
        tes.add(12);
        tes.add(23);
//        List<List<Integer>> list = impl.getPartOfTags(tes, 3);
//        list.forEach(l -> {
//            l.forEach(li -> System.out.print(li+" "));
//            System.out.println();
//        });
    }

    @Test
    public void repoTag() throws Exception {
        List<RepoVO> repos = repoService.getRelatedByTags("reddit/reddit");
        System.out.println(repos.size());
    }
}
