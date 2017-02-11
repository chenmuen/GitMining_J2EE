package servicetest;

import base.BaseTest;
import dao.RepoDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.RepoStatisticService;
import service.evaluate.Evaluate;
import util.Statistic;
import vo.SectorVO;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/24.
 */
public class StatisticTest extends BaseTest {

    @Autowired
    private RepoStatisticService repostat;
    @Autowired
    private RepoDAO repoDAO;

    @Test
    public void repoStatistic() throws Exception {
        List<SectorVO> sectors = repostat.getRepoCollaborator();
        sectors.forEach(s -> {
            DecimalFormat format = new DecimalFormat("#.00");
            System.out.println(s.sector+" -- "+s.name+" -- "+s.nums);
        });
    }

    @Test
    public void statistic() throws Exception {
        List<Object> objs = repoDAO.getPropertyList("starsCount", true);
        List<Integer> list = objs.stream().map(o -> (Integer)o).collect(Collectors.toList());
        Statistic st = Evaluate.getStatistic(list);
        System.out.println(st.arithmetic_mean+" "+st.geometrical_mean+" "+st.harmonic_mean);
    }
}
