package entity;

import util.enums.UserType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenm on 2016/5/10.
 */
@Entity
@Table(name="user")
public class User implements Serializable {
    private String login;
    private String name;
    private UserType type;
    private String avatarUrl;
    private String htmlUrl;
    private int followersCount;
    private int followingsCount;
    private int starredCount;
    private int subscriptionCount;
    private int publicGists;
    private int publicRepo;
    private String email;
    private String location;
    private String blog;
    private String company;
//    private List<String> repos;
    private long createAt;
    private int score;
    private int repoScore;
    private int repoStarCount;

    @Id
    @Column(name = "login", nullable = false, length = 255)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = false, columnDefinition = "enum")
    @Enumerated(EnumType.STRING)
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "avatar_url", nullable = true, length = 255)
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Basic
    @Column(name = "html_url", nullable = true, length = 255)
    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @Basic
    @Column(name = "followers_count", nullable = false)
    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    @Basic
    @Column(name = "followings_count", nullable = false)
    public int getFollowingsCount() {
        return followingsCount;
    }

    public void setFollowingsCount(int followingsCount) {
        this.followingsCount = followingsCount;
    }

    @Basic
    @Column(name = "starred_count", nullable = false)
    public int getStarredCount() {
        return starredCount;
    }

    public void setStarredCount(int starredCount) {
        this.starredCount = starredCount;
    }

    @Basic
    @Column(name = "subscription_count", nullable = false)
    public int getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(int subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }

    @Basic
    @Column(name = "public_gists", nullable = false)
    public int getPublicGists() {
        return publicGists;
    }

    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
    }

    @Basic
    @Column(name = "public_repo", nullable = false)
    public int getPublicRepo() {
        return publicRepo;
    }

    public void setPublicRepo(int publicRepo) {
        this.publicRepo = publicRepo;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 255)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "blog", nullable = true, length = 255)
    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name="joinrepo", joinColumns=@JoinColumn(name="user_login"))
//    @Column(name="repo_full_name")
//    public List<String> getRepos() {
//        return repos;
//    }
//
//    public void setRepos(List<String> repos) {
//        this.repos = repos;
//    }

    @Basic
    @Column(name = "company", nullable = true, length = 255)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (followersCount != user.followersCount) return false;
        if (followingsCount != user.followingsCount) return false;
        if (starredCount != user.starredCount) return false;
        if (subscriptionCount != user.subscriptionCount) return false;
        if (publicGists != user.publicGists) return false;
        if (publicRepo != user.publicRepo) return false;
        if (createAt != user.createAt) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (type != user.type) return false;
        if (avatarUrl != null ? !avatarUrl.equals(user.avatarUrl) : user.avatarUrl != null) return false;
        if (htmlUrl != null ? !htmlUrl.equals(user.htmlUrl) : user.htmlUrl != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (location != null ? !location.equals(user.location) : user.location != null) return false;
        if (blog != null ? !blog.equals(user.blog) : user.blog != null) return false;
        if (company != null ? !company.equals(user.company) : user.company != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (htmlUrl != null ? htmlUrl.hashCode() : 0);
        result = 31 * result + followersCount;
        result = 31 * result + followingsCount;
        result = 31 * result + starredCount;
        result = 31 * result + subscriptionCount;
        result = 31 * result + publicGists;
        result = 31 * result + publicRepo;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (blog != null ? blog.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (int) (createAt ^ (createAt >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", followersCount=" + followersCount +
                ", followingsCount=" + followingsCount +
                ", starredCount=" + starredCount +
                ", subscriptionCount=" + subscriptionCount +
                ", publicGists=" + publicGists +
                ", publicRepo=" + publicRepo +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", blog='" + blog + '\'' +
                ", company='" + company + '\'' +
                ", createdAt=" + createAt +
                ", createAt=" + createAt +
                ", score=" + score +
                ", repoScore=" + repoScore +
                ", repoStarCount=" + repoStarCount +
                '}';
    }

    @Basic
    @Column(name = "create_at", nullable = false)
    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "repo_score", nullable = false)
    public int getRepoScore() {
        return repoScore;
    }

    public void setRepoScore(int repoScore) {
        this.repoScore = repoScore;
    }

    @Basic
    @Column(name = "repo_star_count", nullable = false)
    public int getRepoStarCount() {
        return repoStarCount;
    }

    public void setRepoStarCount(int repoStarCount) {
        this.repoStarCount = repoStarCount;
    }
}
