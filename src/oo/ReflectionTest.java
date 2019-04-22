package oo;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.*;

/**
 * Created by sun on 2016/12/22.
 */
public class ReflectionTest {
    public static void main(String[] args){
        try {
            Class cl = Class.forName("oo.Employee");

            //获取类名
            String str = cl.getName();
            System.out.println(str);
            System.out.println(str.substring(str.lastIndexOf(".") + 1, str.length()));

            //字符串首字母大写
            String field = "name";
            System.out.println(field.substring(0,1).toUpperCase().concat(field.substring(1)));

            ReflectionTest rt = new ReflectionTest();
            rt.printFileds(cl);
            rt.printConstructors(cl);
            rt.printMethods(cl);

            rt.testField(cl, "name", "孙");
            rt.testMethod(cl, "name", "汪");

            for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
                System.out.println(gc.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printFileds(Class cl){
        Field[] fields = cl.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        for (Field field : fields) {
            sb.append("    ");
            sb.append(Modifier.toString(field.getModifiers()) + " ");
            sb.append(field.getType().getName() + " ");
            sb.append(field.getName() + ";\n");
        }
        System.out.println(sb.toString());
    }

    public void printConstructors(Class cl){
        Constructor[] constructors = cl.getConstructors();
        StringBuffer sb = new StringBuffer();
        for (Constructor constructor : constructors) {
            sb.append("    ");
            sb.append(Modifier.toString(constructor.getModifiers()) + " ");
            sb.append(constructor.getName() + "(");

            //打印参数类型
            Class[] paramType = constructor.getParameterTypes();
            for (int j = 0; j < paramType.length; j++){
                if (j > 0) sb.append(", ");
                sb.append(paramType[j].getName());
            }
            sb.append(");\n");
        }
        System.out.println(sb.toString());
    }

    public void printMethods(Class cl){
        Method[] methods = cl.getDeclaredMethods();
        StringBuffer sb = new StringBuffer();
        for (Method method : methods) {
            sb.append("    ");
            sb.append(Modifier.toString(method.getModifiers()) + " ");
            sb.append(method.getReturnType().getName() + " ");
            sb.append(method.getName() + "(");

            //打印参数类型
            Class[] paramType = method.getParameterTypes();
            for (int j = 0; j < paramType.length; j++){
                if (j > 0) sb.append(", ");
                sb.append(paramType[j].getName());
            }
            sb.append(");\n");
        }
        System.out.println(sb.toString());
    }

    /**
     * 测试通过反射机制调用类的方法
     * @param cl  Class对象
     * @param name  属性名
     * @param value  属性值
     */
    public void testMethod(Class cl, String name ,String value){
        try {
            Employee employee = (Employee) cl.newInstance();
            //将字符串name首字符转换为大写
            String nameStr = name.substring(0, 1).toUpperCase().concat(name.substring(1));
            //获取set方法
            Method setMothod = cl.getDeclaredMethod("set" + nameStr, String.class);
            //获取get方法
            Method getMethod = cl.getDeclaredMethod("get" + nameStr);
            //调用set方法
            setMothod.invoke(employee, value);
            //调用get方法
            System.out.println(getMethod.invoke(employee));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试通过反射机制操作类的属性
     * @param cl  Class对象
     * @param str  属性名
     * @param value  属性值
     */
    public void testField(Class cl, String str, String  value){
        try {
            //Employee必须要声明一个无参的构造函数
            Employee employee = (Employee) cl.newInstance();
            //用来获取public或非public属性
            Field field = cl.getDeclaredField(str);
            //操作之前必须要设置为可访问
            field.setAccessible(true);
            field.set(employee, value);
            System.out.println(field.get(employee));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
