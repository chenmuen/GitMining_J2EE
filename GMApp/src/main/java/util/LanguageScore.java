package util;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by raychen on 16/5/26.
 */
public class LanguageScore extends Score {

    public LanguageScore() {
        parameters = new HashMap<>();
    }

    @Override
    public void calculateScore() {

        double repo = parameters.get("repo") == null ? 0 : (double) parameters.get("repo");
        double user = parameters.get("user") == null ? 0 : (double) parameters.get("user");
        double score = parameters.get("repo_score") == null ? 0 : (double) parameters.get("user");

        final_score = (repo*250 + user*100) / 350 * 100;
        BigDecimal b = new BigDecimal(final_score);
        final_score = b.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
