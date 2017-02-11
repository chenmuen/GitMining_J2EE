package entity;

import javax.persistence.*;

/**
 * Created by chenm on 2016/5/18.
 */
@Entity
@Table(name = "repotag")
public class RepoTag {
    private int id;
    private String repoFullName;
    private String tagName;

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
    @Column(name = "tag_name", nullable = false, length = 255)
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepoTag repoTag = (RepoTag) o;

        if (id != repoTag.id) return false;
        if (repoFullName != null ? !repoFullName.equals(repoTag.repoFullName) : repoTag.repoFullName != null)
            return false;
        if (tagName != null ? !tagName.equals(repoTag.tagName) : repoTag.tagName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (repoFullName != null ? repoFullName.hashCode() : 0);
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RepoTag{" +
                "id=" + id +
                ", repoFullName='" + repoFullName + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
