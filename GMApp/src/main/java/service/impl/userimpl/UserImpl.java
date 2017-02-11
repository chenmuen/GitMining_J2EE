package service.impl.userimpl;

import dao.LanguageDAO;
import dao.UserDAO;
import entity.Language;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import service.UserToolService;
import util.enums.UserSortType;
import vo.UserVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by raychen on 16/5/10.
 */
@Service
@Transactional
public class UserImpl implements UserService, UserToolService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    LanguageDAO languageDAO;

    @Override
    public List<UserVO> toVOList(List<User> pos){
        List<UserVO> vos = new LinkedList<>();
        pos.forEach(po -> vos.add(new UserVO(po)));
        return vos;
    }

    public UserVO showUser(String userName) {
        return new UserVO(userDAO.getUser(userName));
    }

    public List<UserVO> showUserList(String word, int page, String language, int year) {
        return toVOList(userDAO.getUserList(word, UserSortType.GENERAL, language, year, page));
    }

    public List<UserVO> sortUserListByRepo(String word, int page, String language, int year) {
        return toVOList(userDAO.getUserList(word, UserSortType.REPO, language, year, page));
    }

    public List<UserVO> sortUserListByFollower(String word, int page, String language, int year) {
        return toVOList(userDAO.getUserList(word, UserSortType.FOLLOWER, language, year, page));
    }

    public int getPages(String word, String language, int year) {
        return userDAO.getPages(word, language, year);
    }

    @Override
    public List<Language> getLanguagesByUser(String userName) {
        return languageDAO.getLanguageByUser(userName);
    }

    @Override
    public List<UserVO> getAll() {
        return toVOList(userDAO.getAll());
    }

    @Override
    public List<UserVO> getUserByLanguage(String langName) {
        List<User> users = languageDAO.getUserByLanguage(langName);
        return toVOList(users);
    }

    @Override
    public List<UserVO> getRankedUser(int num, String lang) {
        List<UserVO> userVOs = lang==null?toVOList(userDAO.getListByRanking("score", 1, num)):toVOList(userDAO.getUserListByLanguage(lang, 1, num));
        return userVOs;
    }
}
