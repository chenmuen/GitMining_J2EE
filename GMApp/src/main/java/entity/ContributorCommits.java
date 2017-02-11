package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by chenm on 2016/5/10.
 */
@Entity
@Table(name="contributorcommits")
public class ContributorCommits implements Serializable {
    private int id;
    private String repoFullName;
    private String contributorLogin;
    private int total;
//    private List<Commit> commits;

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
    @Column(name = "total", nullable = false)
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumns({
//        @JoinColumn(name="repo_full_name", referencedColumnName = "repo_full_name"),
//        @JoinColumn(name="contributor_login", referencedColumnName = "contributor_login")
//    })
//    public List<Commit> getContributorCommits() {
//        return commits;
//    }
//
//    public void setCommits(List<Commit> commits) {
//        this.commits = commits;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContributorCommits that = (ContributorCommits) o;

        if (id != that.id) return false;
        if (total != that.total) return false;
        if (repoFullName != null ? !repoFullName.equals(that.repoFullName) : that.repoFullName != null) return false;
        if (contributorLogin != null ? !contributorLogin.equals(that.contributorLogin) : that.contributorLogin != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (repoFullName != null ? repoFullName.hashCode() : 0);
        result = 31 * result + (contributorLogin != null ? contributorLogin.hashCode() : 0);
        result = 31 * result + total;
        return result;
    }

    @Override
    public String toString() {
        return "ContributorCommits{" +
                "id=" + id +
                ", repoFullName='" + repoFullName + '\'' +
                ", contributorLogin='" + contributorLogin + '\'' +
                ", total=" + total +
//                ", commits=" + commits +
                '}';
    }
}
