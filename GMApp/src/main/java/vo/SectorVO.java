package vo;

/**
 * Created by raychen on 16/5/12.
 */
public class SectorVO {
    public int sector;
    public double nums;
    public String name;

    public SectorVO(int sector, double nums) {
        this.sector = sector;
        this.nums = nums;
    }

    public SectorVO(double nums, String name) {
        this.nums = nums;
        this.name = name;
    }
}
