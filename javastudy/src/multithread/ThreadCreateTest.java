package multithread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 * Created by sun on 2017/1/15.
 */
public class ThreadCreateTest {
    private static Log log = LogFactory.getLog(ThreadCreateTest.class);

    public static void main(String[] args){
        //开始测试通过继承Thread类创建线程
        log.info("---开始测试通过继承Thread类创建线程---");
       /* char ch;
        for (int i =0; i < 3; i++){
            ch = (char) ( 'A' + i);
            new ExtendThread(String.valueOf(ch)).start();
        }*/
        //开始测试通过实现Runnable接口创建线程
        log.info("---开始测试通过实现Runnable接口创建线程---");
        /*char ch;
        for (int i = 0; i < 3; i++){
            ch = (char) ('A' + i);
            new Thread(new ImplementsRunnable(String.valueOf(ch))).start();
        }*/
        Thread thread = new Thread(new ImplementsRunnable(String.valueOf('D')));
        thread.start();
//        thread.run();
    }
}

/**
 * 继承Thread类
 */
class ExtendThread extends Thread{
    String name;

    public ExtendThread(String n){
        super(n);
        System.out.println("创建线程 : " + getName());
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--){
                System.out.println("线程" + getName() + " : " + i);
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 *实现Runnable接口
 */
class ImplementsRunnable implements Runnable{
    String name;

    public ImplementsRunnable(String n){
       name = n;
       System.out.println("创建线程 : " + name);
    }

    @Override
    public void run() {
        try {
            System.out.println("线程名: " + Thread.currentThread().getName());
            for (int i = 5; i > 0 ; i--){
                System.out.println("线程" + name + " : " + i);
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
