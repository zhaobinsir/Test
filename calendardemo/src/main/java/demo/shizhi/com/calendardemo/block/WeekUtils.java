package demo.shizhi.com.calendardemo.block;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

//获得周几
public class WeekUtils {
    public static String getWeek(Date date) {
        String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        System.out.println(weeks[week_index]);
        return weeks[week_index];
    }

    //获得当月的第几周
    public static String getWeekNum(String str) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date.getTime());
        calendar.setTime(date);
        int week_day_month = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        return "第"+week_day_month+"周";
    }


//    public static void main(String[] args) throws ParseException {
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date= sdf.parse("2020-02-29");
//        System.out.println(date.getTime());
//        c.setTime(date);
//
////        int year = c.get(Calendar.YEAR);
////        int month = c.get(Calendar.MONTH);
////        int day = c.get(Calendar.DAY_OF_MONTH);
////        int hour = c.get(Calendar.HOUR_OF_DAY);
////        int minute = c.get(Calendar.MINUTE);
////        int second = c.get(Calendar.SECOND);
////        int week_month = c.get(Calendar.WEEK_OF_MONTH);
////        int week_year = c.get(Calendar.WEEK_OF_YEAR);
////        int week_day = c.get(Calendar.DAY_OF_WEEK);
//        int week_day_month = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
////        System.out.println("year=" + year + "\nmonth=" + month + "\nday=" + day +
////                "\nhour=" + hour + "\nminute=" + minute +
////                "\nsecond=" + second + "\nweek_month=" + week_month +
////                "\nweek_year=" + week_year + "\nweek_day=" + week_day +
////                "\nweek_day_month=" + week_day_month);
//        System.out.println(week_day_month);
//    }

}
