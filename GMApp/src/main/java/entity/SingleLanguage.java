package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by chenm on 2016/6/17.
 */
@Entity
public class SingleLanguage {
    private String language;
    private int repoNum;
    private int userNum;
    private int score;
    private String wiki;
    private String wikiUrl;
    private String hw;

    @Id
    @Column(name = "language", nullable = false, length = 255)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "wiki", nullable = false, length = -1)
    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    @Basic
    @Column(name = "wiki_url", nullable = false, length = 255)
    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    @Basic
    @Column(name = "hw", nullable = false, length = -1)
    public String getHw() {
        return hw;
    }

    public void setHw(String hw) {
        this.hw = hw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleLanguage that = (SingleLanguage) o;

        if (repoNum != that.repoNum) return false;
        if (userNum != that.userNum) return false;
        if (score != that.score) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (wiki != null ? !wiki.equals(that.wiki) : that.wiki != null) return false;
        if (wikiUrl != null ? !wikiUrl.equals(that.wikiUrl) : that.wikiUrl != null) return false;
        if (hw != null ? !hw.equals(that.hw) : that.hw != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = language != null ? language.hashCode() : 0;
        result = 31 * result + repoNum;
        result = 31 * result + userNum;
        result = 31 * result + score;
        result = 31 * result + (wiki != null ? wiki.hashCode() : 0);
        result = 31 * result + (wikiUrl != null ? wikiUrl.hashCode() : 0);
        result = 31 * result + (hw != null ? hw.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SingleLanguage{" +
                "language='" + language + '\'' +
                ", repoNum=" + repoNum +
                ", userNum=" + userNum +
                ", score=" + score +
                ", wiki='" + wiki + '\'' +
                ", wikiUrl='" + wikiUrl + '\'' +
                ", hw='" + hw + '\'' +
                '}';
    }
}
