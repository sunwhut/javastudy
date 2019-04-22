package oo;

/**
 * Created by sun on 2017/2/23.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  书籍接口
 */
interface Book{
    /**
     * 看书方法
     */
    public void readBook();
}

/**
 * 委托类
 * 重写了看书方法
 */
class DelegateClass implements Book{
    private String bookName;

    public DelegateClass(String bookName){
        this.bookName = bookName;
    }

    /**
     *  处理具体的业务
     */
    @Override
    public void readBook() {
        System.out.println("开始阅读《" + bookName + "》啦！");
    }
}

/**
 *  调用处理器实现类
 *  每次生成动态代理类对象时都需要指定一个调用处理器实现类的对象
 */
class InvocationHandlerImpl implements InvocationHandler{
    //要代理的真实对象
    Object realObject;

    public InvocationHandlerImpl(Object object){
        realObject = object;
    }

    /**
     * 该方法负责集中处理动态代理类上的所有方法调用
     * @param proxy  代理类实例
     * @param method  被调用的方法对象
     * @param args  被调用的方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("做事之前，先干点啥呢...");
        System.out.println("method: " + method);
        Object result = null;
        if ("readBook".equals(method.getName())){
            System.out.println("今天休息，不看书啦...");
        }else {
            //调用真实对象的方法
            result = method.invoke(realObject, args);
        }
        System.out.println("做完事了，哈哈哈...");
        return result;
    }
}


public class DynamicProxyTest {
    public static void main(String[] args) {
        //要代理的真实对象
        Book delegateClass = new DelegateClass("人类简史");
        //创建调用处理器实现类的对象
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl(delegateClass);
        //生成动态代理类的实例
        Book proxyClass = (Book) Proxy.newProxyInstance(delegateClass.getClass().getClassLoader(),
                delegateClass.getClass().getInterfaces(), invocationHandler);  //要绑定接口
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        proxyClass.readBook();
        System.out.println("动态代理对象的类型: " + proxyClass.getClass().getName());
        System.out.println("-----");

        //真实对象
        Account delegateAccount = new DelegateAccount(100000);
        //代理对象
        Account proxyAccount = (Account) Proxy.newProxyInstance(delegateAccount.getClass().getClassLoader(),
                delegateAccount.getClass().getInterfaces(), new InvocationHandlerImpl(delegateAccount));
        proxyAccount.queryAccount();
        System.out.println("动态代理对象的类型" + proxyAccount.getClass().getName());
    }
}
