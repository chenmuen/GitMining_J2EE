package service.impl.repoimpl;

import dao.LanguageDAO;
import dao.RepoDAO;
import dao.UserDAO;
import entity.Repo;
import entity.RepoTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.RepoService;
import service.RepoToolService;
import util.enums.RepoSortType;
import vo.RepoVO;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/10.
 */
@Service
@Transactional
public class RepoImpl implements RepoService, RepoToolService {

    private static final int maxRelated = 5;
    private static List<RepoVO> allRepos;

    @Autowired
    private RepoDAO repoDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private LanguageDAO languageDAO;

    @Override
    public List<RepoVO> toVOList(List<Repo> repos){
        if (repos == null) return null;
        List<RepoVO> vos = repos.stream()
                .map(repo -> new RepoVO(repo))
                .collect(Collectors.toList());
        return vos;
    }

    @Override
    public RepoVO showRepo(String repoName) {
        Repo repo = repoDAO.getRepo(repoName);
        return new RepoVO(repo);
    }

    @Override
    public List<RepoVO> showRepoList(String word, int page, String category, String language, int year) {
        return toVOList(repoDAO.getRepoList(word, RepoSortType.GENERAL, category, year, language, page));
    }

    @Override
    public List<RepoVO> sortRepoListByStar(String word, int page, String category, String language, int year) {
        return toVOList(repoDAO.getRepoList(word, RepoSortType.STAR, category, year, language, page));
    }

    @Override
    public List<RepoVO> sortRepoListByFork(String word, int page, String category, String language, int year) {
        return toVOList(repoDAO.getRepoList(word, RepoSortType.FORK, category, year, language, page));
    }

    @Override
    public List<RepoVO> sortRepoListByContributor(String word, int page, String category, String language, int year) {
        return toVOList(repoDAO.getRepoList(word, RepoSortType.CONTRIBUTOR, category, year, language, page));
    }

    @Override
    public int getPages(String word, String category, String language, int year) {
        return repoDAO.getPages(word, category, year, language);
    }

    @Override
    public List<RepoVO> getAll() {
        if (allRepos == null) allRepos = toVOList(repoDAO.getAll());
        return allRepos;
    }

    @Override
    public List<RepoVO> getRelatedByTags(String repo) {
        List<RepoVO> vos = new LinkedList<>();
        List<RepoTag> tags = repoDAO.getRepoTags(repo);
        try {
            int sum = 0;
            for (int i = tags.size(); i >= 1; i++) {
                List<List<String>> toCheck = getPartOfTags(tags, i);
                for (List<String> tag: toCheck) {
                    List<Repo> repos = repoDAO.getRepoListByTags(tag);
                    //not sorted
                    repos = repos.stream()
                            .filter(repo1 -> !repo1.getFullName().equals(repo))
                            .limit(maxRelated - sum)
                            .collect(Collectors.toList());
                    List<RepoVO> repovos = toVOList(repos);
                    repovos.forEach(repoVO -> vos.add(repoVO));
                    sum += repos.size();
                    if (sum >= maxRelated) break;
                }
                if (sum >= maxRelated) break;
            }
        }catch (NullPointerException e){
            return vos;
        }
        return vos;
    }

    private void find(List<RepoTag> origin, int index, int left, List<RepoTag> current, List<List<String>> tags){
        if (left == 0){
            List<String> newList = new LinkedList<>();
            current.forEach(c -> newList.add(c.getTagName()));
            tags.add(newList);
        }else if (origin.size() - index < left) return;
        else{
            current.add(origin.get(index));
            find(origin, index+1, left-1, current, tags);
            current.remove(current.size()-1);
            find(origin, index+1, left, current, tags);
        }
    }

    private List<List<String>> getPartOfTags(List<RepoTag> tags, int num){
        List<List<String>> newTags = new LinkedList<>();
        List<RepoTag> single =  new LinkedList<>();
        find(tags, 0, num, single, newTags);
        return newTags;
    }

    @Override
    public List<RepoVO> getRelatedByOwner(String login, String current) {
        List<String> repos = userDAO.getRepoNamesOfUser(login);
        repos.remove(current);
        List<RepoVO> repoVOs = new LinkedList<>();
        //version-1 return first 5
        int max = repos.size();
        if (repos.size()> maxRelated) max = maxRelated;
        for (int i = 0; i < max; i++) {
            repoVOs.add(new RepoVO(repoDAO.getRepo(repos.get(i))));
        }
        return repoVOs;
    }

    @Override
    public List<RepoVO> getReposByLanguage(String langName) {
        List<Repo> repos = languageDAO.getRepoByLanguage(langName);
        return toVOList(repos);
    }

    @Override
    public List<RepoVO> getRankedRepos(int num, String lang) {
        List<RepoVO> repoVOs = lang==null?toVOList(repoDAO.getListByRanking("score", 1, num)):toVOList(repoDAO.getRepoListByLanguage(lang, 1, num));
        return repoVOs;
    }
}
