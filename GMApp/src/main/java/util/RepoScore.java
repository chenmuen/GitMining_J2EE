package util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raychen on 16/5/18.
 */
public class RepoScore extends Score{

    public RepoScore() {
        parameters = new HashMap<>();
    }

    @Override
    public void calculateScore() {
        //version-2
        //star,fork,contributor,createtime,issue
        double star = parameters.get("star") == null ? 0 : (double) parameters.get("star");
        double fork = parameters.get("fork") == null ? 0 : (double) parameters.get("fork");
        double con = parameters.get("contributiors") == null ? 0 : (double) parameters.get("contributiors");
        double cret = parameters.get("cret") == null ? 1 : (double) parameters.get("cret");
        double issues = parameters.get("issue") == null ? 1 : (double) parameters.get("issue");

        final_score = (star*200 + fork*80 + con*5 + 0.05*Math.pow(100, 1.0-1.0/Math.pow(issues,0.5)) + 10*cret) / 3;
        BigDecimal b = new BigDecimal(final_score);
        final_score = b.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
