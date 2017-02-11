package vo;

/**
 * Created by raychen on 16/5/12.
 */
public class RadarVO {
//    public String name;
//    public int value;
//    public int max;
    private String name;
    private int value;
    private int max;


    public RadarVO(String name, int value, int max) {
        this.name = name;
        this.value = value;
        this.max = max;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }

}
