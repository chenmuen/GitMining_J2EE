package util;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by raychen on 16/5/19.
 */
public class UserScore extends Score{

    public UserScore() {
        parameters = new HashMap<>();
    }

    @Override
    public void calculateScore() {

        double follower = parameters.get("follower") == null ? 0 : (double) parameters.get("follower");
        double repo = parameters.get("repo") == null ? 0 : (double) parameters.get("repo");

        final_score = follower*50 + repo*50;
        BigDecimal b = new BigDecimal(final_score);
        final_score = b.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
