package oo;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sun on 2016/12/5.
 */
public class CalendarTest {
    public static void main(String[] args){
        Calendar d = Calendar.getInstance();

        int today = d.get(Calendar.DAY_OF_MONTH);
        int month = d.get(Calendar.MONTH);
        System.out.println(d.get(Calendar.YEAR) + "-" + d.get(Calendar.MONTH) + "-" + d.get(Calendar.DAY_OF_WEEK));

        d.set(Calendar.DAY_OF_MONTH, 1);
        int weekday = d.get(Calendar.DAY_OF_WEEK);

        int firstDayOfWeek = d.getFirstDayOfWeek();

        int indent = 0;
        while(weekday != firstDayOfWeek){
            indent++;
            d.add(Calendar.DAY_OF_MONTH, -1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }

        String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
        do {
            System.out.printf("%4s", weekdayNames[weekday]);
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }while (weekday != firstDayOfWeek);
        System.out.println();

        for (int i=1; i <= indent; i++){
            System.out.print("       ");
        }

        d.set(Calendar.DAY_OF_MONTH, 1);
        do {
            int day = d.get(Calendar.DAY_OF_MONTH);
            System.out.printf("%6d", day);

            if (day == today) System.out.print("*");
            else System.out.print(" ");

            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);

            if (weekday == firstDayOfWeek)
                System.out.println();
        }while (d.get(Calendar.MONTH) == month);

        if (weekday != firstDayOfWeek)
            System.out.println();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(df2.format(df.parse(" 2016-04-11 10:45:59.0")));
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

}
