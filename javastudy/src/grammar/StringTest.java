package grammar;

import java.lang.reflect.Field;

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
    }
}
