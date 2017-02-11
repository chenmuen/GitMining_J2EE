package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenm on 2016/5/10.
 */
@Entity
@Table(name="language")
public class Language implements Serializable {
    private int id;
    private String repoFullName;
    private String language;
    private long size;
    private int year;

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
    @Column(name = "repo_full_name", nullable = false, length = 100)
    public String getRepoFullName() {
        return repoFullName;
    }

    public void setRepoFullName(String repoFullName) {
        this.repoFullName = repoFullName;
    }

    @Basic
    @Column(name = "language", nullable = false, length = 100)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "size", nullable = false)
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language1 = (Language) o;

        if (id != language1.id) return false;
        if (size != language1.size) return false;
        if (repoFullName != null ? !repoFullName.equals(language1.repoFullName) : language1.repoFullName != null)
            return false;
        if (language != null ? !language.equals(language1.language) : language1.language != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (repoFullName != null ? repoFullName.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (int)size;
        return result;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", repoFullName='" + repoFullName + '\'' +
                ", language='" + language + '\'' +
                ", size=" + size +
                ", year=" + year +
                '}';
    }

    @Basic
    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
