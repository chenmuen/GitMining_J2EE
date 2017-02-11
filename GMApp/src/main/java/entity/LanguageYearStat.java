package entity;

import javax.persistence.*;

/**
 * Created by chenm on 2016/6/8.
 */
@Entity
@Table(name = "languageyearstat")
@IdClass(LanguageYearStatPK.class)
public class LanguageYearStat {
    private String language;
    private int year;
    private int repoNum;
    private int userNum;

    @Id
    @Column(name = "language", nullable = false, length = 255)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Id
    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "repo_num", nullable = false)
    public int getRepoNum() {
        return repoNum;
    }

    public void setRepoNum(int repoNum) {
        this.repoNum = repoNum;
    }

    @Basic
    @Column(name = "user_num", nullable = false)
    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguageYearStat that = (LanguageYearStat) o;

        if (year != that.year) return false;
        if (repoNum != that.repoNum) return false;
        if (userNum != that.userNum) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = language != null ? language.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + repoNum;
        result = 31 * result + userNum;
        return result;
    }

    @Override
    public String toString() {
        return "LanguageYearStat{" +
                "language='" + language + '\'' +
                ", year=" + year +
                ", repoNum=" + repoNum +
                ", userNum=" + userNum +
                '}';
    }
}
