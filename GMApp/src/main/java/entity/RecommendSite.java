package entity;

import javax.persistence.*;

/**
 * Created by chenm on 2016/6/19.
 */
@Entity
@Table(name = "recommendsite")
public class RecommendSite {
    private int id;
    private int siteId;
    private String siteName;
    private String siteUrl;
    private String siteImg;
    private String language;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "site_id", nullable = false)
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "site_name", nullable = false, length = 100)
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @Basic
    @Column(name = "site_url", nullable = false, length = 255)
    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @Basic
    @Column(name = "site_img", nullable = false, length = 100)
    public String getSiteImg() {
        return siteImg;
    }

    public void setSiteImg(String siteImg) {
        this.siteImg = siteImg;
    }

    @Basic
    @Column(name = "language", nullable = true, length = 100)
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

        RecommendSite that = (RecommendSite) o;

        if (id != that.id) return false;
        if (siteId != that.siteId) return false;
        if (siteName != null ? !siteName.equals(that.siteName) : that.siteName != null) return false;
        if (siteUrl != null ? !siteUrl.equals(that.siteUrl) : that.siteUrl != null) return false;
        if (siteImg != null ? !siteImg.equals(that.siteImg) : that.siteImg != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + siteId;
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        result = 31 * result + (siteUrl != null ? siteUrl.hashCode() : 0);
        result = 31 * result + (siteImg != null ? siteImg.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RecommendSite{" +
                "id=" + id +
                ", siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", siteImg='" + siteImg + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
