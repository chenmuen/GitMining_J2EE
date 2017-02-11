//import cross.gitmining.data.repodataimpl.RepoDataLocalImpl;
//import cross.gitmining.data.userdataimpl.UserDataServiceLocalImpl;
//import cross.gitmining.po.*;
//import cross.gitmining.po.ContributorCommits;
//import cross.gitmining.util.LocalDataHelper;
//import cross.gitmining.util.TimeHelper;
import base.BaseTest;
import dao.ContributorCommitsDAO;
import dao.PunchDAO;
import dao.RepoDAO;
import dao.UserDAO;
import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import util.enums.UserType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by chenm on 2016/5/11.
 */
public class DataTest extends BaseTest {
    @Autowired
    RepoDAO repoDAO;

    @Autowired
    SessionFactory sessionFactory;
//
//    @Autowired
//    UserDAO userDAO;
//
//    @Autowired
//    ContributorCommitsDAO contributorCommitsDAO;
//
//    @Autowired
//    PunchDAO punchDAO;

//    RepoDataLocalImpl repodata = new RepoDataLocalImpl();
//    UserDataServiceLocalImpl userdata = new UserDataServiceLocalImpl();

//    @Test
//    public void transRepo() {
//        List<RepoPO> list = LocalDataHelper.getAllRepo();
//        for (RepoPO repoPO: list) {
//            Repo repo = new Repo();
//
//            repo.setFullName(repoPO.getFullName());
//            repo.setCloneUrl(repoPO.getCloneUrl());
//            repo.setUrl(repoPO.getUrl());
//            repo.setCollaboratorsCount(repoPO.getCollaborators());
//            repo.setContributorsCount(repoPO.getContributors());
//            repo.setStarsCount(repoPO.getStars());
//            repo.setForksCount(repoPO.getForks());
//            repo.setPullRequestsCount(repoPO.getPullRequests());
//            repo.setIssuesCount(repoPO.getIssues());
//            repo.setDescription(repoPO.getDescription());
//            repo.setSize(repoPO.getSize());
//            repo.setUpdatedAt(TimeHelper.toDate(repoPO.getLastUpdateTime()).getTimeInMillis());
//            repo.setCreatedAt(TimeHelper.toDate(repoPO.getCreateTime()).getTimeInMillis());
//            repo.setSubscribersCount(repoPO.getSubscribers());
//
//            List<Language> languages = new ArrayList<Language>();
//
//            Map<String, Integer> languageMap = repodata.getLanguage(repo.getFullName());
//            Iterator<String> itr = languageMap.keySet().iterator();
//            while(itr.hasNext()) {
//                String key = itr.next();
//                int value = languageMap.get(key);
//                Language language = new Language();
//                language.setRepoFullName(repo.getFullName());
//                language.setLanguage(key);
//                language.setSize(value);
//
//                languages.add(language);
//            }
//
//            repo.setLanguages(languages);
//
//            repoDAO.add(repo);
//        }
//    }
//
//    @Test
//    public void transUser() {
//        List<UserPO> userPOs = LocalDataHelper.getAllUser();
//        for (UserPO userPO : userPOs) {
//            User user = new User();
//
//            user.setLogin(userPO.getLogin());
//            user.setName(userPO.getName());
//            user.setAvatarUrl(userPO.getAvatarUrl());
//            user.setHtmlUrl(userPO.getUrl());
//            user.setEmail(userPO.getEmail());
//            user.setBlog(userPO.getBlog());
//            user.setLocation(userPO.getLocation());
//            user.setCompany(userPO.getCompany());
//            user.setFollowersCount(userPO.getFollowers());
//            user.setFollowingsCount(userPO.getFollowing());
//            user.setStarredCount(userPO.getStarred());
//            user.setSubscriptionCount(userPO.getSubscription());
//            user.setCreateAt(TimeHelper.toDate(userPO.getJoinTime()).getTimeInMillis());
//            user.setPublicGists(userPO.getGists());
//            user.setPublicRepo(userPO.getRepoNum());
//            user.setRepos(userPO.getRepoNameList());
//            switch (userPO.getUserType()) {
//                case USER:
//                    user.setType(UserType.User);
//                    break;
//                case ORGANIZATION:
//                    user.setType(UserType.Organization);
//            }
//
//            userDAO.add(user);
//        }
//    }
//
//    @Test
//    public void transCommits() {
//        List<RepoPO> repoPOs = LocalDataHelper.getAllRepo();
//        List<String> names = new ArrayList<String>();
//        for (RepoPO repoPO : repoPOs) {
//            names.add(repoPO.getFullName());
//        }
//
//        for (String name : names) {
//            List<ContributorCommits> contributorCommitses = repodata.getCommits(name);
//
//            for (ContributorCommits contributorCommit : contributorCommitses) {
//                entity.ContributorCommits contributorCommits = new entity.ContributorCommits();
//                contributorCommits.setRepoFullName(name);
//                contributorCommits.setContributorLogin(contributorCommit.getLogin());
//                contributorCommits.setTotal(contributorCommit.getTotal());
//                List<Commit> commits = new ArrayList<Commit>();
//                for (CommitPO commitPO : contributorCommit.getCommits()) {
//                    Commit commit = new Commit();
//                    commit.setRepoFullName(name);
//                    commit.setContributorLogin(contributorCommit.getLogin());
//                    commit.setAddtion(commitPO.getAddition());
//                    commit.setDeletion(commitPO.getDeletion());
//                    commit.setCommits(commitPO.getCommits());
//                    commit.setWeek(commitPO.getWeek());
//                    commits.add(commit);
//                }
//                contributorCommits.setCommits(commits);
//
//                contributorCommitsDAO.add(contributorCommits);
//            }
//
//        }
//
//    }
//
//    @Test
//    public void transPunch() {
//        List<RepoPO> repoPOs = LocalDataHelper.getAllRepo();
//        List<String> names = new ArrayList<String>();
//        for (RepoPO repoPO : repoPOs) {
//            names.add(repoPO.getFullName());
//        }
//
//        for (String name : names) {
//            List<PunchPO> pos = repodata.getPunchCard(name);
//
//            for (PunchPO po : pos) {
//                Punch punch = new Punch();
//                punch.setRepoFullName(name);
//                punch.setDay(po.getDay());
//                punch.setHour(po.getHour());
//                punch.setCommits(po.getCommits());
//
//                punchDAO.add(punch);
//            }
//        }
//    }

//    @Test
//    public void addTag() {
//        String [] tags = {"App","Framework","Json","Library","Linux","Mac","Windows","Plugin","Server","Web","Template"};
//        List<Repo> repos = repoDAO.getAll();
//        Session session = sessionFactory.getCurrentSession();
//        for (Repo repo : repos) {
//            for (String tag : tags) {
//                if(repo.getDescription().toLowerCase().contains(tag.toLowerCase())) {
//                    RepoTag repoTag = new RepoTag();
//                    repoTag.setRepoFullName(repo.getFullName());
//                    repoTag.setTagName(tag);
//                    session.saveOrUpdate(repoTag);
//                }
//            }
//            System.out.println(repo.getFullName());
//        }
//
//    }

}
