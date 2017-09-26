package multithread;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Administrator on 2017/8/14.
 */
public class CountTest extends Thread{
//    private static int count = 0;
//    private static AtomicInteger count = new AtomicInteger(0);
    private static LongAdder count = new LongAdder();


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
//            count++;
//            count.addAndGet(1);
            count.increment();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new CountTest().start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);

        int r = new Random().nextInt(101);
        System.out.println(r);
    }
}
