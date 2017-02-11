package entity;

import javax.persistence.*;

/**
 * Created by chenm on 2016/6/19.
 */
@Entity
@Table(name = "ana_values", schema = "gmdbnew", catalog = "")
public class AnaValues {
    private int id;
    private String vName;
    private int groupId;
    private long xValue;
    private long vValue;
    private String xName;
    private String yName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "v_name", nullable = false, length = 255)
    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    @Basic
    @Column(name = "groupId", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "x_value", nullable = false)
    public long getxValue() {
        return xValue;
    }

    public void setxValue(long xValue) {
        this.xValue = xValue;
    }

    @Basic
    @Column(name = "v_value", nullable = false)
    public long getvValue() {
        return vValue;
    }

    public void setvValue(long vValue) {
        this.vValue = vValue;
    }

    @Basic
    @Column(name = "x_name", nullable = false, length = 255)
    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName;
    }

    @Basic
    @Column(name = "y_name", nullable = false, length = 255)
    public String getyName() {
        return yName;
    }

    public void setyName(String yName) {
        this.yName = yName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnaValues anaValues = (AnaValues) o;

        if (id != anaValues.id) return false;
        if (groupId != anaValues.groupId) return false;
        if (xValue != anaValues.xValue) return false;
        if (vValue != anaValues.vValue) return false;
        if (vName != null ? !vName.equals(anaValues.vName) : anaValues.vName != null) return false;
        if (xName != null ? !xName.equals(anaValues.xName) : anaValues.xName != null) return false;
        if (yName != null ? !yName.equals(anaValues.yName) : anaValues.yName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vName != null ? vName.hashCode() : 0);
        result = 31 * result + groupId;
        result = 31 * result + (int) (xValue ^ (xValue >>> 32));
        result = 31 * result + (int) (vValue ^ (vValue >>> 32));
        result = 31 * result + (xName != null ? xName.hashCode() : 0);
        result = 31 * result + (yName != null ? yName.hashCode() : 0);
        return result;
    }
}
