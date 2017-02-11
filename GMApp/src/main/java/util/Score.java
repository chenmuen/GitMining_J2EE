package util;

import java.util.Map;

/**
 * Created by raychen on 16/5/18.
 */
public abstract class Score {
    public double final_score;
    protected Map<String, Object> parameters;

    public void putParameter(String key, Object obj){
        if (!parameters.containsKey(key)) parameters.put(key, obj);
        else parameters.replace(key, obj);
    }

    public abstract void calculateScore();
}
