package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenm on 2016/5/10.
 */
@Entity
@Table(name="commit")
public class Commit implements Serializable {
    private int id;
    private String repoFullName;
    private String contributorLogin;
    private long week;
    private int addtion;
    private int deletion;
    private int commits;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "repo_full_name", nullable = false, length = 255)
    public String getRepoFullName() {
        return repoFullName;
    }

    public void setRepoFullName(String repoFullName) {
        this.repoFullName = repoFullName;
    }

    @Basic
    @Column(name = "contributor_login", nullable = false, length = 255)
    public String getContributorLogin() {
        return contributorLogin;
    }

    public void setContributorLogin(String contributorLogin) {
        this.contributorLogin = contributorLogin;
    }

    @Basic
    @Column(name = "week", nullable = false)
    public long getWeek() {
        return week;
    }

    public void setWeek(long week) {
        this.week = week;
    }

    @Basic
    @Column(name = "addtion", nullable = false)
    public int getAddtion() {
        return addtion;
    }

    public void setAddtion(int addtion) {
        this.addtion = addtion;
    }

    @Basic
    @Column(name = "deletion", nullable = false)
    public int getDeletion() {
        return deletion;
    }

    public void setDeletion(int deletion) {
        this.deletion = deletion;
    }

    @Basic
    @Column(name = "commits", nullable = false)
    public int getCommits() {
        return commits;
    }

    public void setCommits(int commits) {
        this.commits = commits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commit commit = (Commit) o;

        if (id != commit.id) return false;
        if (week != commit.week) return false;
        if (addtion != commit.addtion) return false;
        if (deletion != commit.deletion) return false;
        if (commits != commit.commits) return false;
        if (repoFullName != null ? !repoFullName.equals(commit.repoFullName) : commit.repoFullName != null)
            return false;
        if (contributorLogin != null ? !contributorLogin.equals(commit.contributorLogin) : commit.contributorLogin != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (repoFullName != null ? repoFullName.hashCode() : 0);
        result = 31 * result + (contributorLogin != null ? contributorLogin.hashCode() : 0);
        result = 31 * result + (int) (week ^ (week >>> 32));
        result = 31 * result + addtion;
        result = 31 * result + deletion;
        result = 31 * result + commits;
        return result;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "id=" + id +
                ", repoFullName='" + repoFullName + '\'' +
                ", contributorLogin='" + contributorLogin + '\'' +
                ", week=" + week +
                ", addtion=" + addtion +
                ", deletion=" + deletion +
                ", commits=" + commits +
                '}';
    }
}
