package multithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ThreadDemo(lock1, lock2), "线程A");
        Thread thread2 = new Thread(new ThreadDemo(lock2, lock1), "线程B");
        thread1.start();
        thread2.start();
    }
}

class ThreadDemo implements Runnable {
    private Lock firstLock;
    private Lock secondLock;

    public ThreadDemo(Lock firstLock, Lock secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!firstLock.tryLock()) {
                    Thread.sleep(100);
                    continue;
                }
                Thread.sleep(100);
                if (!secondLock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + "释放了锁");
                    firstLock.unlock();
                    Thread.sleep(100);
                } else {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            firstLock.unlock();
            secondLock.unlock();
            System.out.println(Thread.currentThread().getName() + "正常结束");
        }

    }
}

