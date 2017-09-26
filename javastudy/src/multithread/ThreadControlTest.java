package multithread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;
import static java.lang.Thread.yield;

/**
 * Created by sun on 2017/1/16.
 */
public class ThreadControlTest {
    private static Log log = LogFactory.getLog(ThreadCreateTest.class);

    public static void main(String[] args){
        //测试sleep()方法
        log.info("---测试sleep()方法---");
        /*char ch;
        for (int i = 0; i < 3; i++){
            ch = (char) ('A' + i);
            new Thread(new MyThread(), String.valueOf(ch)).start();
        }*/
        //测试yield()方法
        log.info("---测试yield()方法---");
        /*char ch;
        for (int i = 0; i < 3; i++){
            ch = (char) ('A' + i);
            new Thread(new ThreadYield(), String.valueOf(ch)).start();
        }*/
        //测试join方法
        log.info("---测试join方法---");
        /*int num = 6;
        ThreadJoin threadJoin = new ThreadJoin(num);
        Thread thread = new Thread(threadJoin);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num + "! = " + threadJoin.getResult());*/
        //测试setPriority方法
        log.info("---测试setPriority方法---");
        Thread threadA = new Thread(new ThreadYield(), "A");
        Thread threadB = new Thread(new ThreadYield(), "B");
        threadA.setPriority(MAX_PRIORITY);
        threadB.setPriority(MIN_PRIORITY);
        threadA.start();
        threadB.start();
    }
}

/**
 * 实现Runnable接口
 */
class MyThread implements Runnable{
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("当前线程： " + name);
        try {
            for (int i = 5; i > 0; i--){
                System.out.println("线程" + name + " : " + i);
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
class ThreadYield implements Runnable{
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 5; i > 0; i--){
            System.out.println("线程" + name + " : " + i);
            yield();
        }
    }
}

/**
 * 定义一个类实现Runnable接口
 */
class ThreadJoin implements Runnable{
    private int result = 1;
    private int n;

    public ThreadJoin(int num){
        n = num;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= n; i++){
                result *= i;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getResult() {
        return result;
    }
}
