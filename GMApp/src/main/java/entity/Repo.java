package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by chenm on 2016/5/10.
 */
@Entity
@Table(name="repo")
public class Repo implements Serializable {
    private String fullName;
    private String description;
    private String url;
    private String cloneUrl;
    private int subscribersCount;
    private int forksCount;
    private int starsCount;
    private int contributorsCount;
    private int collaboratorsCount;
    private int pullRequestsCount;
    private int issuesCount;
    private int size;
    private long updatedAt;
    private long createdAt;
    private String language;
    private int score;

    @Id
    @Column(name = "full_name", nullable = false, length = 255)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1, columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "clone_url", nullable = false, length = 255)
    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    @Basic
    @Column(name = "subscribers_count", nullable = false)
    public int getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(int subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    @Basic
    @Column(name = "forks_count", nullable = false)
    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    @Basic
    @Column(name = "stars_count", nullable = false)
    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    @Basic
    @Column(name = "contributors_count", nullable = false)
    public int getContributorsCount() {
        return contributorsCount;
    }

    public void setContributorsCount(int contributorsCount) {
        this.contributorsCount = contributorsCount;
    }

    @Basic
    @Column(name = "collaborators_count", nullable = false)
    public int getCollaboratorsCount() {
        return collaboratorsCount;
    }

    public void setCollaboratorsCount(int collaboratorsCount) {
        this.collaboratorsCount = collaboratorsCount;
    }

    @Basic
    @Column(name = "pullrequests_count", nullable = false)
    public int getPullRequestsCount() {
        return pullRequestsCount;
    }

    public void setPullRequestsCount(int pullrequestsCount) {
        this.pullRequestsCount = pullrequestsCount;
    }

    @Basic
    @Column(name = "issues_count", nullable = false)
    public int getIssuesCount() {
        return issuesCount;
    }

    public void setIssuesCount(int issuesCount) {
        this.issuesCount = issuesCount;
    }

    @Basic
    @Column(name = "size", nullable = false)
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Basic
    @Column(name = "updated_at", nullable = false)
    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "created_at", nullable = false)
    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "language", nullable = false, length = 255)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Repo repo = (Repo) o;

        if (subscribersCount != repo.subscribersCount) return false;
        if (forksCount != repo.forksCount) return false;
        if (starsCount != repo.starsCount) return false;
        if (contributorsCount != repo.contributorsCount) return false;
        if (collaboratorsCount != repo.collaboratorsCount) return false;
        if (issuesCount != repo.issuesCount) return false;
        if (size != repo.size) return false;
        if (updatedAt != repo.updatedAt) return false;
        if (createdAt != repo.createdAt) return false;
        if (fullName != null ? !fullName.equals(repo.fullName) : repo.fullName != null) return false;
        if (description != null ? !description.equals(repo.description) : repo.description != null) return false;
        if (url != null ? !url.equals(repo.url) : repo.url != null) return false;
        if (cloneUrl != null ? !cloneUrl.equals(repo.cloneUrl) : repo.cloneUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fullName != null ? fullName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (cloneUrl != null ? cloneUrl.hashCode() : 0);
        result = 31 * result + subscribersCount;
        result = 31 * result + forksCount;
        result = 31 * result + starsCount;
        result = 31 * result + contributorsCount;
        result = 31 * result + collaboratorsCount;
        result = 31 * result + issuesCount;
        result = 31 * result + size;
        result = 31 * result + (int) (updatedAt ^ (updatedAt >>> 32));
        result = 31 * result + (int) (createdAt ^ (createdAt >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", cloneUrl='" + cloneUrl + '\'' +
                ", subscribersCount=" + subscribersCount +
                ", forksCount=" + forksCount +
                ", starsCount=" + starsCount +
                ", contributorsCount=" + contributorsCount +
                ", collaboratorsCount=" + collaboratorsCount +
                ", pullRequestsCount=" + pullRequestsCount +
                ", issuesCount=" + issuesCount +
                ", size=" + size +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", language='" + language + '\'' +
                ", score=" + score +
                '}';
    }

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
