package entity;

import javax.persistence.*;

/**
 * Created by chenm on 2016/6/9.
 */
@Entity
@Table(name = "langcommon")
public class LangCommon {
    private int id;
    private String lang1;
    private String lang2;
    private int commonRepoNum;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lang1", nullable = false, length = 100)
    public String getLang1() {
        return lang1;
    }

    public void setLang1(String lang1) {
        this.lang1 = lang1;
    }

    @Basic
    @Column(name = "lang2", nullable = false, length = 100)
    public String getLang2() {
        return lang2;
    }

    public void setLang2(String lang2) {
        this.lang2 = lang2;
    }

    @Basic
    @Column(name = "common_repo_num", nullable = false)
    public int getCommonRepoNum() {
        return commonRepoNum;
    }

    public void setCommonRepoNum(int commonRepoNum) {
        this.commonRepoNum = commonRepoNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LangCommon that = (LangCommon) o;

        if (id != that.id) return false;
        if (commonRepoNum != that.commonRepoNum) return false;
        if (lang1 != null ? !lang1.equals(that.lang1) : that.lang1 != null) return false;
        if (lang2 != null ? !lang2.equals(that.lang2) : that.lang2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lang1 != null ? lang1.hashCode() : 0);
        result = 31 * result + (lang2 != null ? lang2.hashCode() : 0);
        result = 31 * result + commonRepoNum;
        return result;
    }

    @Override
    public String toString() {
        return "LangCommon{" +
                "id=" + id +
                ", lang1='" + lang1 + '\'' +
                ", lang2='" + lang2 + '\'' +
                ", commonRepoNum=" + commonRepoNum +
                '}';
    }
}
