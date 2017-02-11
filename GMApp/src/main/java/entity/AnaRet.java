package entity;

import javax.persistence.*;

/**
 * Created by chenm on 2016/6/19.
 */
@Entity
@Table(name = "ana_ret", schema = "gmdbnew", catalog = "")
public class AnaRet {
    private int id;
    private String vName;
    private double pValue;
    private double fValue;
    private Double alpha;
    private Double kValue;
    private Double hValue;

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
    @Column(name = "p_value", nullable = false, precision = 0)
    public double getpValue() {
        return pValue;
    }

    public void setpValue(double pValue) {
        this.pValue = pValue;
    }

    @Basic
    @Column(name = "f_value", nullable = false, precision = 0)
    public double getfValue() {
        return fValue;
    }

    public void setfValue(double fValue) {
        this.fValue = fValue;
    }

    @Basic
    @Column(name = "alpha", nullable = true, precision = 0)
    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    @Basic
    @Column(name = "k_value", nullable = true, precision = 0)
    public Double getkValue() {
        return kValue;
    }

    public void setkValue(Double kValue) {
        this.kValue = kValue;
    }

    @Basic
    @Column(name = "h_value", nullable = true, precision = 0)
    public Double gethValue() {
        return hValue;
    }

    public void sethValue(Double hValue) {
        this.hValue = hValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnaRet anaRet = (AnaRet) o;

        if (id != anaRet.id) return false;
        if (Double.compare(anaRet.pValue, pValue) != 0) return false;
        if (Double.compare(anaRet.fValue, fValue) != 0) return false;
        if (vName != null ? !vName.equals(anaRet.vName) : anaRet.vName != null) return false;
        if (alpha != null ? !alpha.equals(anaRet.alpha) : anaRet.alpha != null) return false;
        if (kValue != null ? !kValue.equals(anaRet.kValue) : anaRet.kValue != null) return false;
        if (hValue != null ? !hValue.equals(anaRet.hValue) : anaRet.hValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (vName != null ? vName.hashCode() : 0);
        temp = Double.doubleToLongBits(pValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (alpha != null ? alpha.hashCode() : 0);
        result = 31 * result + (kValue != null ? kValue.hashCode() : 0);
        result = 31 * result + (hValue != null ? hValue.hashCode() : 0);
        return result;
    }
}
