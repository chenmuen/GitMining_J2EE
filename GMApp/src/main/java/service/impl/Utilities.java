package service.impl;

import vo.SectorVO;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/13.
 */
public class Utilities {
    private static int MAX_VALUE = 1000000;
    private static int bins = 25;
    private static double cutMax = 0.05;

    /**
     * 划分区间
     * @param list
     * @return
     */
    public static List<SectorVO> getListSectorVO(List<Object> list){
        List<Integer> list_2 = list.stream()
                .map(o -> (Integer) o)
                .collect(Collectors.toList());
        Map<Integer, Long> map = list_2.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        List<SectorVO> vos = new LinkedList<>();
        map.forEach((i, l) -> {
            vos.add(new SectorVO(i, Integer.parseInt(l.toString())));
        });
        Collections.sort(vos, (o1, o2) -> o1.sector - o2.sector);
        List<SectorVO> returns = new LinkedList<>();
        int sectors = bins;//桶的个数
        int maxValue = vos.get(vos.size() - 1 - (int)(vos.size() * cutMax)).sector;
        if (maxValue - vos.get(0).sector < bins){
            vos.forEach(vo -> returns.add(new SectorVO(vo.nums, String.valueOf(vo.sector))));
            return returns;
        }
        int interval = maxValue / sectors;
//        int interval1 = interval / sectors;
//        addSectors(sectors, 0, interval, interval1, vos, returns);
        addSectors(sectors, 0, MAX_VALUE, interval, vos, returns);
        returns.forEach(re -> {
            if (re.nums > 0) re.nums = Math.log(re.nums); // 取ln,指数分布
            re.nums = Double.parseDouble(new DecimalFormat("#.00").format(re.nums));
        });
        return returns;
    }

    //具体划分区间
    private static void addSectors(int sectors, int minIndex, int maxValue, int interval, List<SectorVO> vos, List<SectorVO> toAdd){
        for (int i = minIndex; i < sectors; i++) {
            SectorVO realvo = new SectorVO(0, "["+i*interval+","+(i+1)*interval+")");
            for (SectorVO vo: vos) {
                if (vo.sector >= i*interval && vo.sector < (i+1)*interval)
                    realvo.nums += vo.nums;
            }
            toAdd.add(realvo);
        }
        SectorVO last = new SectorVO(0, "["+sectors*interval+","+maxValue+")"); //最大部分
        if (maxValue == MAX_VALUE) last.name = "["+sectors*interval+",++)";
        if (maxValue == sectors*interval) return;
        for (SectorVO vo: vos) {
            if (vo.sector >= sectors*interval && vo.sector < maxValue)
                last.nums += vo.nums;
        }
        toAdd.add(last);
    }

    /**
     * comparator for long
     * @return
     */
    public static Comparator<Long> get_long_comparator(){
        return new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                if (o1 > o2) return 1;
                return 0;
            }
        };
    }
}
