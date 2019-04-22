package multithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/9/18.
 */
public class LockTest {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.test();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.test();
            }
        }).start();
    }

    public void test(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取了锁");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }
}
