package entity;

import javax.persistence.*;

/**
 * Created by chenm on 2016/6/18.
 */
@Entity
@Table(name="recommendcourse")
public class RecommendCourse {
    private int id;
    private String language;
    private String courseName;
    private String courseUrl;
    private int courseId;
    private String school;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "language", nullable = true, length = 100)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "course_name", nullable = false, length = 100)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "course_url", nullable = false, length = 255)
    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    @Basic
    @Column(name = "course_id", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "school", nullable = false, length = 100)
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendCourse that = (RecommendCourse) o;

        if (id != that.id) return false;
        if (courseId != that.courseId) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) return false;
        if (courseUrl != null ? !courseUrl.equals(that.courseUrl) : that.courseUrl != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (courseUrl != null ? courseUrl.hashCode() : 0);
        result = 31 * result + courseId;
        result = 31 * result + (school != null ? school.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RecommendCourse{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseUrl='" + courseUrl + '\'' +
                ", courseId=" + courseId +
                ", school='" + school + '\'' +
                '}';
    }
}
