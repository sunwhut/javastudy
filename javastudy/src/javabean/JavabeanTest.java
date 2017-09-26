package javabean;

import com.mchange.v2.beans.BeansUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.xml.bind.DatatypeConverter;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by sun on 2017/2/27.
 */
public class JavabeanTest {
    public static void main(String[] args) {
        //1.使用传统的方式去访问JavaBean
        User user = new User();
        user.setName("孙");
        user.setAge(24);
        System.out.println(user);

        //2.使用内省的方式访问JavaBean
        JavabeanTest javabeanTest = new JavabeanTest();
        try {
            javabeanTest.printAllProperties(Class.forName("javabean.User"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        javabeanTest.setProperty(user, "name", "汪");
        javabeanTest.setProperty(user, "age", 24);
        System.out.println(user);

        //3.使用BeanUtils访问JavaBean
        javabeanTest.BeanUtilsTest(user, "name", "杨");
        javabeanTest.BeanUtilsTest(user, "age", 24);
        javabeanTest.BeanUtilsTest(user, "birthday", "1993-06-05");
        try {
            System.out.println(user + ", birthday: " +
                    BeanUtils.getProperty(user, "birthday"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用内省的方式获取JavaBean的所有属性名
     * @param clazz
     */
    public void printAllProperties(Class clazz){
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        for (PropertyDescriptor p : beanInfo.getPropertyDescriptors()) {
            System.out.println(p.getName());
        }
    }

    /**
     * 使用内省的方式操作JavaBean的单个属性
     * @param bean  JavaBean对象
     * @param property  属性名
     * @param value  属性值
     */
    public void setProperty(Object bean, String property, Object value){
        PropertyDescriptor propertyDescriptor = null;
        try {
            //获取属性描述符
            propertyDescriptor = new PropertyDescriptor(property, bean.getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        //获取写方法
        Method setMethod = propertyDescriptor.getWriteMethod();
        try {
            //调用写方法
            setMethod.invoke(bean, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用BeanUtils工具包访问JavaBean
     * @param bean  JavaBean对象
     * @param field  属性名
     * @param value  属性值
     */
    public void BeanUtilsTest(Object bean, String field, Object value){
        try {
            if ("birthday".equals(field)){
                //注册一个转化器，将字符串类型转换为Date类型
                DateConverter dateConverter = new DateConverter();
                dateConverter.setPattern("yyyy-MM-dd");
                ConvertUtils.register(dateConverter, Date.class);
                BeanUtils.setProperty(bean, field, value);
            }else {
                BeanUtils.setProperty(bean, field, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
