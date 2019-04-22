package multithread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by sun on 2017/1/20.
 */
public class DeadLockTest {
    private static Log log = LogFactory.getLog(DeadLockTest.class);
    public static Object obj1 = new Object();
    public static Object obj2 = new Object();

    public static void main(String[] args){
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }
}

class Thread1 implements Runnable{
    @Override
    public void run() {
        synchronized (DeadLockTest.obj1){
            System.out.println("线程1进入obj1的同步代码块");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DeadLockTest.obj2){
                System.out.println("线程1进入obj2的同步代码块");
            }
        }
    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        synchronized (DeadLockTest.obj2){
            System.out.println("线程2进入obj2的同步代码块");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DeadLockTest.obj1){
                System.out.println("线程2进入obj1的同步代码块");
            }
        }
    }
}
