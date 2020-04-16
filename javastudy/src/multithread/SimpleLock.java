package multithread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class SimpleLock extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int unused) {
        //使用compareAndSetState控制AQS中的同步变量
        if (compareAndSetState(0, 1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int unused) {
        setExclusiveOwnerThread(null);
        //使用setState控制AQS中的同步变量
        setState(0);
        return true;
    }

    public void lock(){
        acquire(1);
    }

    public boolean tryLock(){
        return tryAcquire(1);
    }

    public void unlock(){
        release(1);
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleLock simpleLock = new SimpleLock();
        simpleLock.lock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                simpleLock.lock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取了锁!");
                simpleLock.unlock();
            }).start();
            Thread.sleep(10);
        }
        System.out.println("主线程释放了锁!");
        simpleLock.unlock();
    }
}
