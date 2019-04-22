package annotation;

import java.lang.annotation.*;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * Created by sun on 2017/2/27.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@interface MyAnnotation{
    String name() default "孙";
    int age() default 24;
}

class Demo{
    public void m1(){

    }

    @MyAnnotation
    public void m2(){

    }

    @MyAnnotation(name = "汪", age = 23)
    public void m3(){

    }
}

public class MyAnnotationTest {
    public static void main(String[] args) {
        MyAnnotationTest myAnnotationTest = new MyAnnotationTest();
        myAnnotationTest.process("annotation.Demo");
    }

    public void process(String classStr){
        Class clazz = null;
        try {
            clazz = Class.forName(classStr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyAnnotation.class)){
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
                System.out.println("被MyAnnotation注解修饰的方法: " + method.getName() + ", 内容: "
                        + myAnnotation.name() + ", " + myAnnotation.age());
            }else {
                System.out.println("未被MyAnnotation注解的方法: " + method.getName());
            }
        }
    }
}
