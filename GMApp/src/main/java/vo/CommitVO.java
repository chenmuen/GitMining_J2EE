package vo;

import entity.Commit;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by raychen on 16/5/12.
 */
public class CommitVO {
    public String repoFullName;
    public String contributorLogin;
    public Calendar time;
    public int commits;
    public int additions;
    public int deletions;

    public CommitVO(Commit commit) {
        this.repoFullName = commit.getRepoFullName();
        this.contributorLogin = commit.getContributorLogin();
        this.time = Calendar.getInstance();
        this.time.setTime(new Date(commit.getWeek()*1000));
        this.commits = commit.getCommits();
        this.additions = commit.getAddtion();
        this.deletions = commit.getDeletion();
    }

//    public CommitVO(String repoFullName, String contributorLogin, Date time, int commits, int additions, int deletions) {
//        this.repoFullName = repoFullName;
//        this.contributorLogin = contributorLogin;
//        this.time = time;
//        this.commits = commits;
//        this.additions = additions;
//        this.deletions = deletions;
//    }

    /**
     * 单个仓库总commit
     * @param commits
     * @param repoFullName
     */
    public CommitVO(List<Commit> commits, String repoFullName, long time){
        this.repoFullName = repoFullName;
        this.contributorLogin = null;
        this.time = Calendar.getInstance();
        this.time.setTime(new Date(time*1000));
        commits.forEach(commit -> {
            this.commits += commit.getCommits();
            this.additions += commit.getAddtion();
            this.deletions += commit.getDeletion();
        });
    }
}
