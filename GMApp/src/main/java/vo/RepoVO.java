package vo;

import entity.Repo;
import util.RepoScore;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenm on 2016/5/9.
 */
public class RepoVO {

    public String fullName;
    public String description;
    public String url;
    public String cloneUrl;
    public int subscribersCount;
    public int forksCount;
    public int starsCount;
    public int contributorsCount;
    public int collaboratorsCount;
    public int issuesCount;
    public int size;
    public String updatedAt;
    public String createdAt;

    public String repoName;
    public String owner;

    public RepoScore score;
    public int cretMonths;
    public double starsPerYear;
    public int sortScore;

    public Repo repo;

    public RepoVO(Repo repo) {
        this.fullName = repo.getFullName();
        this.description = repo.getDescription();
        this.url = repo.getUrl();
        this.cloneUrl = repo.getCloneUrl();
        this.subscribersCount = repo.getSubscribersCount();
        this.forksCount = repo.getForksCount();
        this.starsCount = repo.getStarsCount();
        this.contributorsCount = repo.getContributorsCount();
        this.collaboratorsCount = repo.getCollaboratorsCount();
        this.issuesCount = repo.getIssuesCount();
        this.size = repo.getSize();
        this.updatedAt = getTime(repo.getUpdatedAt());
        this.createdAt = getTime(repo.getCreatedAt());
        this.cretMonths = getDiff(repo.getCreatedAt());
        this.starsPerYear = (double) starsCount / (cretMonths+1);

        String[] split = this.fullName.split("/");
        this.owner = split[0];
        this.repoName = split[1];

        this.repo = repo;
        score = new RepoScore();
        this.sortScore = repo.getScore();
        this.score.final_score = (double) repo.getScore()/10000;
    }

    private String getTime(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        return cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
    }

    private int getDiff(long time){
        long today = Calendar.getInstance().getTimeInMillis();
        return (int) ((today - time)/1000/60/60/24/365);
    }


}
