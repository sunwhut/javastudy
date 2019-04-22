package multithread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.*;

/**
 * Created by sun on 2017/2/7.
 */
public class ThreadPoolTest {
    private static Log log = LogFactory.getLog(ThreadPoolTest.class);

    public static void main(String[] args){
        log.info("---测试ThreadPoolTest类---");
//        ExecutorService executor = Executors.newSingleThreadExecutor();    //创建一个单线程的线程池
//        ExecutorService executor = Executors.newFixedThreadPool(5);    //创建一个固定大小的线程池
//        ExecutorService executor = Executors.newCachedThreadPool();    //创建一个可缓存的线程池
        for (int i = 0 ; i < 10; i++){
//            executor.execute(new MyJob(i));
        }
//        executor.shutdown();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);    //创建一个定长线程池，支持定时及周期性执行任务
        /*executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟3秒执行");
            }
        }, 3000, TimeUnit.MILLISECONDS);*/
        /*executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟1秒后每3秒执行一次");
            }
        }, 1000, 3000, TimeUnit.MILLISECONDS);*/
        testThreadPool();
        System.out.println("所有线程执行结束！");
    }

    public static void testThreadPool(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1000,
                TimeUnit.MICROSECONDS, new LinkedBlockingDeque<Runnable>(5));
        for (int i = 0; i < 15; i++) {
            executor.execute(new MyJob(i));
            System.out.println("线程池中当前线程数目：" + executor.getPoolSize() +
                "任务缓存队列中等待执行的任务数：" + executor.getQueue().size());
        }
        executor.shutdown();
    }
}

/**
 * 自定义一个工作任务类，实现Runnable接口
 */
class MyJob implements Runnable{
    private int index;

    public MyJob(int i){
        index = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始执行。index = " + index);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 执行结束。");
    }
}