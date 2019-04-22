package oo;

/**
 * Created by sun on 2017/2/23.
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 没有实现任何接口的普通业务类
 */
class MyBook{
    private String bookName;

    //加入默认的构造函数
    public MyBook(){

    }

    public MyBook(String bookName){
        this.bookName = bookName;
    }

    public void readBook(){
        System.out.println("开始阅读《" + bookName + "》啦...");
    }
}

/**
 * 使用CGlib动态代理
 */
class CGlibProxy implements MethodInterceptor{
    private Object target;

    /**
     * 创建代理对象
     * @param t  要代理的真实对象
     * @return  动态代理对象
     */
    public Object getInstance(Object t){
        target = t;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 拦截方法
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        Object result = null;
        System.out.println("做事之前，干点啥呢...");
        //调用真实对象的方法
        result = methodProxy.invoke(target, objects);
        System.out.println("做完事了，哈哈哈...");
        return result;
    }
}

public class CGlibTest {
    public static void main(String[] args) {
        CGlibProxy cGlibProxy = new CGlibProxy();
        //获取代理对象
        MyBook myBook = (MyBook) cGlibProxy.getInstance(new MyBook("人类简史"));
        myBook.readBook();
        System.out.println("-----");

        //真实对象
        MyBook realBook = new MyBook("Java核心技术");
        //使用Enhancer的静态方法获取代理对象
        MyBook book = (MyBook) Enhancer.create(MyBook.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects,
                                    MethodProxy methodProxy) throws Throwable {
                System.out.println("代理之前，干点哈呢...");
                Object result = methodProxy.invoke(realBook, objects);
                System.out.println("代理之后，哈哈哈...");
                return result;
            }
        });
        book.readBook();
    }
}
