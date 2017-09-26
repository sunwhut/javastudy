package multithread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/18.
 */
public class TimedTask {
    private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
//        useThread();
//        useTimer();
        useScheduledExecutorService();
    }

    public static void useThread() {
        Thread threadA = new Thread(new Task(dateFormat), "A");
        Thread threadB = new Thread(new Task(dateFormat), "B");
        Thread threadC = new Thread(new Task(dateFormat), "C");
        threadA.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadC.start();
    }

    public static void useTimer() {
        TimerTask taskA = new TimerTask() {
            @Override
            public void run() {
                System.out.println("ThreadA: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        };
        TimerTask taskB = new TimerTask() {
            @Override
            public void run() {
                System.out.println("ThreadB: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            }
        };
        TimerTask taskC = new TimerTask() {
            @Override
            public void run() {
                System.out.println("ThreadC: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(taskA, 0, 30000);
        timer.scheduleAtFixedRate(taskB, 10000, 30000);
        timer.scheduleAtFixedRate(taskC, 20000, 30000);
    }

    public static void useScheduledExecutorService(){
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(3);
        Thread threadA = new Thread(new Task(dateFormat), "A");
        Thread threadB = new Thread(new Task(dateFormat), "B");
        Thread threadC = new Thread(new Task(dateFormat), "C");
        scheduled.scheduleAtFixedRate(threadA, 0, 30, TimeUnit.SECONDS);
        scheduled.scheduleAtFixedRate(threadB, 10, 30, TimeUnit.SECONDS);
        scheduled.scheduleAtFixedRate(threadC, 20, 30, TimeUnit.SECONDS);
    }

}

class Task implements Runnable {
    ThreadLocal<DateFormat> dateFormat;

    public Task(ThreadLocal<DateFormat> dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Thread" + Thread.currentThread().getName() + ": " +
                    dateFormat.get().format(new Date()));
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
