package multithread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolExecutor {
    private int corePoolSize;  //核心池大小
    private int maxPoolSize;  //最大池大小
    private LinkedBlockingQueue<Runnable> workQueue = null; //任务仓库
    private AtomicInteger currentPoolSize = new AtomicInteger(0);  //当前线程池的大小

    public MyThreadPoolExecutor(int corePoolSize, int maxPoolSize, int workQueueSize) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        workQueue = new LinkedBlockingQueue<>(workQueueSize);
    }

    //线程池线程干什么
    public class Worker extends Thread {
        Runnable firstTask;

        public Worker(Runnable firstTask){
            this.firstTask = firstTask;
        }

        @Override
        public void run() {
            try {
                //执行用户提交的Runnable
                Runnable task = firstTask;

                while (true) {
                    if (task != null || (task = workQueue.take()) != null) {  //从任务队列 取Runnable执行
                        task.run();
                        task = null;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程池中的一个线程出现异常");
            }
        }
    }

    //提交任务
    public void execute(Runnable task) throws Exception {
        //1. 创建线程的步骤
        //判断currentPoolSize,如果小于核心池大小,则创建一个worker去执行当前任务(本质就是一个线程)
        if (currentPoolSize.get() < corePoolSize) {  //做一次数量判断
            if (currentPoolSize.incrementAndGet() <= corePoolSize) { //线程池的大小+1，再进行判断
                new Worker(task).start();
                return;
            } else {  //如果发现 超过了核心池大小
                currentPoolSize.decrementAndGet();  //减一
            }
        }

        //2.提交到任务仓库
        //任务队列是否已满? 没满,则将新提交的任务存储在工作队列里
        if (workQueue.offer(task)){
            return;
        }

        //3.是否达到线程池最大数量?没达到,则创建一个新的工作线程来执行任务
        if (currentPoolSize.get() < maxPoolSize) {  //做一次数量判断
            if (currentPoolSize.incrementAndGet() <= maxPoolSize) { //线程池的大小+1，再进行判断
                new Worker(task).start();
                return;
            } else {  //如果发现 超过了最大池大小
                currentPoolSize.decrementAndGet();  //减一
            }
        }

        //4.拒绝处理这个任务
        throw new Exception("拒绝执行");
    }
}
