package util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by raychen on 16/6/4.
 */
public class TimeUtil {

    public static Calendar getTimeFromLong(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        return calendar;
    }
}
