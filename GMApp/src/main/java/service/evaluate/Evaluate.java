package service.evaluate;

import entity.LanguageYearStat;
import util.Statistic;
import vo.LanguageVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/12.
 */
public class Evaluate {

    //雷达图cut max/min
    private static double cutRate = 0.1;

    public static double getRemarksInArray(Integer value, List<Integer> arrayOrigin) {
        List<Integer> array = arrayOrigin.stream()
                .distinct()
                .sorted((e1, e2) -> (e1 - e2))
                .collect(Collectors.toList());

        int size = array.size();
        int cut = (int) (size * cutRate);
        int index = array.indexOf(value) + 1 - cut;
        double rate = (double) index / (size - 2 * cut) * 100;
        if (rate > 100) rate = 100;
        if (rate < 10) rate = 10; //雷达图最小值
        return rate;
    }

    public static Statistic getStatistic(List<Integer> list) {
        Statistic st = new Statistic();

        Collections.sort(list, (o1, o2) -> o1 - o2);
        double sum = list.stream().mapToInt(l -> l).sum();
        System.out.println(sum);
        //算数平均数
        st.arithmetic_mean = sum / list.size();
        //几何平均数/调和
        double product = 0;
        double sum2 = 0;
        int num = 0;
        for (Integer i : list)
            if (i > 0) { // 去掉0
                product += Math.log(i);
                sum2 += (double) 1 / i;
                num++;
            }
        product /= num;
        st.geometrical_mean = Math.pow(Math.E, product);
        st.harmonic_mean = (double) num / sum2;
        //中位数
        st.median = list.get((list.size() + 1) / 2);
        //众数


        return st;
    }

    public static void calculateLangSizeOfYear(List<LanguageVO> allLangs, List<LanguageYearStat> stats, int year) {
        allLangs.forEach(languageVO -> languageVO.scoreOfYear.put(year, 0));
        for (LanguageYearStat lan : stats) {
            LanguageVO lanvo = null;
            for (LanguageVO lanvo2 : allLangs) {
                if (lanvo2.languageName.equals(lan.getLanguage())) {
                    lanvo = lanvo2;
                    break;
                }
            }
            if (lanvo == null) continue;
            long i = lanvo.reposOfYear.get(year);
            long j = lanvo.usersOfYear.get(year);
            lanvo.reposOfYear.replace(year, i + lan.getRepoNum());
            lanvo.usersOfYear.replace(year, j + lan.getUserNum());
//            lanvo.allRepos += lan.getRepoNum();
        }
    }

}
