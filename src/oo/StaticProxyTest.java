package oo;

/**
 * Created by sun on 2017/2/23.
 */

/**
 *  账户接口
 */
interface Account{
    /**
     * 查看账户方法
     */
    public void queryAccount();
}

/**
 * 委托类
 * 重写了查看账户方法,处理具体的业务
 */
class DelegateAccount implements Account{
    private int money;

    public DelegateAccount(int m){
        money = m;
    }

    @Override
    public void queryAccount() {
        System.out.println("账户余额： " + money +" 元人民币");
    }
}

/**
 * 代理类
 * 重写了查看账户方法，在方法中可以预处理消息，转发消息给委托类，并进行后续处理
 */
class ProxyAccount implements Account{
    private Account delegateAccount;

    public ProxyAccount(Account delegateAccount){
        this.delegateAccount = delegateAccount;
    }

    @Override
    public void queryAccount() {
        System.out.println("查看账户之前干点啥...");
        //调用委托类的方法
        delegateAccount.queryAccount();
        System.out.println("查看账户之后可以哈哈哈...");
    }
}

public class StaticProxyTest {
    public static void main(String[] args) {
        ProxyAccount proxyAccount = new ProxyAccount(new DelegateAccount(10000));
        proxyAccount.queryAccount();
    }
}
