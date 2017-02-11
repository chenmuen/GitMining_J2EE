package util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raychen on 16/6/6.
 */
public class LanguageTrends {
    public String langName;
    public Map<Integer, Integer> rates;

    public LanguageTrends(String langName) {
        rates = new HashMap<>();
        this.langName = langName;
    }
}
