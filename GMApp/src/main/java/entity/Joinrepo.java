package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenm on 2016/5/10.
 */
@Entity
@Table(name="joinrepo")
public class Joinrepo implements Serializable {
    private int id;
    private String userLogin;
    private String repoFullName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_login", nullable = false, length = 100)
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Basic
    @Column(name = "repo_full_name", nullable = false, length = 100)
    public String getRepoFullName() {
        return repoFullName;
    }

    public void setRepoFullName(String repoFullName) {
        this.repoFullName = repoFullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Joinrepo joinrepo = (Joinrepo) o;

        if (id != joinrepo.id) return false;
        if (userLogin != null ? !userLogin.equals(joinrepo.userLogin) : joinrepo.userLogin != null) return false;
        if (repoFullName != null ? !repoFullName.equals(joinrepo.repoFullName) : joinrepo.repoFullName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (repoFullName != null ? repoFullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Joinrepo{" +
                "id=" + id +
                ", userLogin='" + userLogin + '\'' +
                ", repoFullName='" + repoFullName + '\'' +
                '}';
    }
}
