package codingproblem;

import java.util.*;

/**
 * Created by sun on 2017/8/1.
 */
import java.util.regex.*;

public class Main  extends  Thread{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        while (true){
            String str = in.nextLine();
            String[] strs = str.split("\\s");
            int year = Integer.parseInt(strs[0]);
            int month = Integer.parseInt(strs[1]);
            int day = Integer.parseInt(strs[2]);
            int sum = 0;
            int[] monthDay={0,31,28,31,30,31,30,31,31,30,31,30,31};
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                monthDay[2] = 29;
            for (int i = 0; i < month; i++) {
                sum += monthDay[i];
            }
            sum += day;
            System.out.println(sum);
        }

    }

}

