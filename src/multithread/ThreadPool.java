package multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/9/25.
 */
public class ThreadPool {
    private volatile int poolSize;  //线程池中当前线程数
    private volatile int corePoolSize;  //核心池大小
    private volatile int runState;  //线程池的状态
    private static final int RUNNING = -1;
    private static final int SHUTDOWN = 0;
    private HashSet<Worker> workers = new HashSet<Worker>();  //线程池
    private BlockingQueue<Runnable> workQueue;  //任务队列
    private final ReentrantLock mainLock = new ReentrantLock();  //线程池的锁
    private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);

    /**
     * 创建线程池
     * @param corePoolSize
     */
    public ThreadPool(int corePoolSize){
        this.corePoolSize = corePoolSize;
        workQueue = new LinkedBlockingQueue<Runnable>(Integer.MAX_VALUE);
        runState = RUNNING;
    }

    /**
     * 提交任务
     * @param task
     */
    public void execute(Runnable task){
        if (task == null) throw new NullPointerException();
        if (runState == RUNNING && poolSize < corePoolSize){
            Thread t = addWorker(task);
            t.start();
        } else{
            addTask(task);
        }
    }

    /**
     * 创建工作线程
     * @param task
     * @return
     */
    public Thread addWorker(Runnable task){
        Thread t = null;
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try{
            Worker worker = new Worker(task);
            t = new Thread(worker);
            worker.thread = t;
            workers.add(worker);
            poolSize++;
            logger.info(t.getName() + "创建完成！");
        } finally {
            mainLock.unlock();
        }
        return t;
    }

    /**
     * 添加任务到缓存队列中
     * @param task
     */
    public void addTask(Runnable task){
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            workQueue.put(task);
            logger.info(Thread.currentThread().getName() + "任务添加成功！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mainLock.unlock();
        }
    }

    /**
     * 关闭线程池
     */
    public void shutdown(){
        final ReentrantLock mainLock = this.mainLock;
        if (mainLock.tryLock()){
            try {
                runState = SHUTDOWN;
                if(!workers.isEmpty()){
                    for (Worker w : workers) {
                        w.thread.interrupt();
                        System.out.println(w.thread.getName() + "线程中断！");
                    }
                }

            } finally {
                mainLock.unlock();
            }
        }
    }

    class Worker implements Runnable{
        Thread thread;

        public Worker(Runnable task){
            workQueue.offer(task);
        }

        public void run(){
            while (runState == RUNNING){
                Runnable task = null;
                if (mainLock.tryLock()){
                    try{
                        task = workQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mainLock.unlock();
                    }

                }
            }
        }

    }

    class Task implements Runnable{
        private int index;

        public Task(int index){
            this.index = index;
        }

        @Override
        public void run() {
            logger.info(Thread.currentThread().getName() + "开始执行: index= " + index);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(Thread.currentThread().getName() + "执行结束！");
        }
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.execute(pool.new Task(i));
        }
    }

}
