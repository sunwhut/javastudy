package grammar;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/25.
 */
public class StringTest {
    public static void main(String[] args) throws Exception {
        String s = "Hello World";
        System.out.println("s = " + s);

        //获取String类中的value属性
        Field valueField = String.class.getDeclaredField("value");

        //改变value属性的访问权限
        valueField.setAccessible(true);

        //获取s对象上的value属性的值
        char[] value = (char[]) valueField.get(s);

        //改变value所引用的数组中的第6个字符
        value[5] = '_';
        System.out.println("s = " + s);

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        String[] aa = list.toArray(new String[0]);
        System.out.println("----------" + list.toArray(new String[0])) ;

//        Pattern pattern = Pattern.compile("\\d{17}");
        Pattern pattern = Pattern.compile("^P[a-zA-Z0-9]{17,}");
        Matcher matcher = pattern.matcher("P54191573255917535");
        System.out.println(matcher.matches());

        String[] arr = "V4.0.0.3".split("\\.");
        for (String st : arr) {
            System.out.println(st);
        }

        List<String> expressCompanyCodeList = new ArrayList<>();
        expressCompanyCodeList.addAll(Arrays.asList(new String[] {"ETK", "etk", "ems", "el", "zhongsukuaidi"}));
//        System.out.println(expressCompanyCodeList);

        String str = "2019-08-20T11:32:34.622000+08:00";
        String[] strArr = str.split("\\+");
        LocalDateTime date = LocalDateTime.parse(strArr[0], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String dateStr = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(sdf.parse(dateStr)));

//        System.out.println(Integer.valueOf("-1"));

        StringBuffer buffer = new StringBuffer();
        Class clazz = s.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Object object = field.get(s);
            buffer.append(field.getName()).append("=").append(object).append("&");
        }
        System.out.println(buffer.substring(0, buffer.length() - 1));

        if (s instanceof String){
            System.out.println("true");
        }
     }
}
