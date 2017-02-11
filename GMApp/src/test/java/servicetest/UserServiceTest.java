package servicetest;

import base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;
import service.evaluate.Rank;
import vo.UserVO;

import java.util.List;

/**
 * Created by raychen on 16/5/24.
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void showUser() throws Exception {
        UserVO user = userService.showUser("sj26");
        System.out.println(user.name);
    }

    @Test
    public void rankUser() throws Exception {
        List<UserVO> users = userService.getAll();
        Rank.rankUser(users);
        users.forEach(userVO -> {
            System.out.println(userVO.login +": " +userVO.score.final_score);
        });
    }

    @Test
    public void userservice() throws Exception {
        int pages = userService.getPages("", null, 0);
        System.out.println(pages);
    }
}
