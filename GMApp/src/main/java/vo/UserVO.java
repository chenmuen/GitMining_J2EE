package vo;

import entity.User;
import util.UserScore;
import util.enums.UserType;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenm on 2016/5/9.
 */
public class UserVO {

    public String login;
    public String name;
    public UserType type;
    public String avatarUrl;
    public String htmlUrl;
    public int followersCount;
    public int followingsCount;
    public int starredCount;
    public int subscriptionCount;
    public int publicGists;
    public int publicRepo;
    public String email;
    public String location;
    public String blog;
    public String company;
    public String createAt;

    public UserScore score;
    public int repo_score;
    public int sortScore;

    public User userPO;

    public UserVO(User user) {
        this.login = user.getLogin();
        this.name = user.getName();
        this.type = user.getType();
        this.avatarUrl = user.getAvatarUrl();
        this.htmlUrl = user.getHtmlUrl();
        this.followersCount = user.getFollowersCount();
        this.followingsCount = user.getFollowingsCount();
        this.starredCount = user.getStarredCount();
        this.subscriptionCount = user.getSubscriptionCount();
        this.publicGists = user.getPublicGists();
        this.publicRepo = user.getPublicRepo();
        this.email = user.getEmail();
        this.location = user.getLocation();
        this.blog = user.getBlog();
        this.company = user.getCompany();
        this.repo_score = user.getRepoStarCount();

        this.score = new UserScore();
        this.createAt = getTime(user.getCreateAt());
        this.userPO = user;
        this.sortScore = user.getScore();
        this.score.final_score = (double) user.getScore()/10000;
    }

    private String getTime(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        return cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
    }
}
