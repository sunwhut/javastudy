package multithread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/10/14.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch begin = new CountDownLatch(1);  //开始信号
        final CountDownLatch end = new CountDownLatch(10);  //结束信号

        for (int i = 0; i < 10; i++) {
            new Thread(new Player(begin, end, i)).start();
        }

        //对开始信号倒计数
        begin.countDown();
        try {
            //结束信号变为0之前，主线程一直阻塞在这里
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("比赛结束");

    }


}

class Player implements Runnable{
    private final CountDownLatch begin;
    private final CountDownLatch end;
    private int id;

    Player(CountDownLatch begin, CountDownLatch end, int id) {
        this.begin = begin;
        this.end = end;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            //开始信号倒计数之前，所有线程都阻塞在这里
            begin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int t = new Random().nextInt(11);
        try {
            Thread.sleep(t * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(id + "号选手用时" + t + "秒");
        //线程执行完之后对结束信号倒计数
        end.countDown();
    }
}