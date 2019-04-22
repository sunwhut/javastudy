package multithread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by sun on 2017/1/17.
 */
public class ThreadSynchronizedTest {
    private static Log log = LogFactory.getLog(ThreadSynchronizedTest.class);

    public static void main(String[] args){
        //线程同步测试
        log.info("---线程同步测试---");
        BankAccount bankAccount = new BankAccount("孙", 1000);
        char ch;
        for (int i = 0; i < 5; i++){
            ch = (char) ('A' + i);
            new Thread(new MyRunnable(bankAccount, i*100), String.valueOf(ch)).start();
        }
    }
}

/**
 * 定义一个普通类
 * 包含两个成员变量，账户名和账户余额
 * 及一个成员方法，从账户余额中取钱
 */
class BankAccount{
    private String name;
    private volatile int money;  //测试volatile用法

    public BankAccount(String n, int m){
        name = n;
        money = m;
        System.out.println("账户名： " + name + " , 账户余额： " + money);
    }

    /**
     * 定义一个同步方法，从对象的money属性中取钱
     * @param m  取钱的数目
     */
    public synchronized void withdrawMoney(int m){
        try {
            money -= m;
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + "线程运行结束，取钱" + m + "，账户余额： " +
                    money);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义一个非同步方法，从对象的money属性中取钱，在方法里面定义一个同步代码块
     * @param m  取钱的数目
     */
    public void getMoney(int m){
        synchronized (this){
            money -= m;
            System.out.println(Thread.currentThread().getName() + "线程运行结束，取钱" + m + "，账户余额： " +
                    money);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 定义一个类，实现Runnable接口
 * 其中一个成员变量是BankAccount类的实例对象
 */
class MyRunnable implements Runnable{
    private BankAccount bankAccount;
    private int money;

    public MyRunnable(BankAccount bank, int m){
        bankAccount = bank;
        money = m;
    }

    @Override
    public void run() {
        //调用同步方法
//        bankAccount.withdrawMoney(money);
        //调用同步代码块
        bankAccount.getMoney(money);
    }
}