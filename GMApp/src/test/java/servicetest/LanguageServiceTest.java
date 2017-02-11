package servicetest;

import base.BaseTest;
import entity.Language;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.LanguageService;
import vo.LanguageVO;
import vo.RepoVO;

import java.util.List;

/**
 * Created by raychen on 16/5/26.
 */
public class LanguageServiceTest extends BaseTest {

    @Autowired
    private LanguageService languageService;

    @Test
    public void rank() throws Exception {
        List<LanguageVO> vos = languageService.getAll();
        vos.forEach(languageVO -> {
            System.out.println(languageVO.languageName+" -- "+languageVO.score.final_score);
        });
    }

    @Test
    public void relatedRepo() throws Exception {
        List<RepoVO> vos = languageService.getMostRelatedRepos("C");
        vos.forEach(repoVO -> {
            System.out.println(repoVO.fullName);
        });
    }
}
