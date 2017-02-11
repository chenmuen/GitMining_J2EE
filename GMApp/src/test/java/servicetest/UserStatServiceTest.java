package servicetest;

import base.BaseTest;
import dao.LanguageDAO;
import entity.Language;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserStatisticService;
import vo.LanguageStaVO;

import java.util.List;

/**
 * Created by raychen on 16/5/24.
 */
public class UserStatServiceTest extends BaseTest {

    @Autowired
    private UserStatisticService userStatisticService;
    @Autowired
    private LanguageDAO languageDAO;

    @Test
    public void user() throws Exception {
        List<LanguageStaVO> languageStaVOs = userStatisticService.getUserLanguage("ezmobius");
        List<Language> languages = languageDAO.getAll();
        System.out.println("all language:"+ languages.size());
        System.out.println(languageStaVOs.size());
    }
}
