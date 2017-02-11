package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenm on 2016/5/10.
 */
@Entity
@Table(name = "punch")
public class Punch implements Serializable {
    private int id;
    private String repoFullName;
    private int day;
    private int hour;
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
    @Column(name = "day", nullable = false)
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Basic
    @Column(name = "hour", nullable = false)
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
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

        Punch punch = (Punch) o;

        if (id != punch.id) return false;
        if (day != punch.day) return false;
        if (hour != punch.hour) return false;
        if (commits != punch.commits) return false;
        if (repoFullName != null ? !repoFullName.equals(punch.repoFullName) : punch.repoFullName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (repoFullName != null ? repoFullName.hashCode() : 0);
        result = 31 * result + day;
        result = 31 * result + hour;
        result = 31 * result + commits;
        return result;
    }

    @Override
    public String toString() {
        return "Punch{" +
                "id=" + id +
                ", repoFullName='" + repoFullName + '\'' +
                ", day=" + day +
                ", hour=" + hour +
                ", commits=" + commits +
                '}';
    }
}
