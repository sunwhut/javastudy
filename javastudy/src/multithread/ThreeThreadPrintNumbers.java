package multithread;

import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/9/5.
 */

/**
 * 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15.
 * 接着再由线程1打印16,17,18,19,20....以此类推, 直到打印到75. 程序的输出结果应该为:
 * 线程1: 1
 * 线程1: 2
 * 线程1: 3
 * 线程1: 4
 * 线程1: 5
 * 线程2: 6
 * 线程2: 7
 * 线程2: 8
 * 线程2: 9
 * 线程2: 10
 * ...
 * 线程3: 71
 * 线程3: 72
 * 线程3: 73
 * 线程3: 74
 * 线程3: 75
 */
public class ThreeThreadPrintNumbers {
    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber("oneThread", 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    printNumber.oneThreadRun();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    printNumber.twoThreadRun();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    printNumber.threeThreadRun();
                }
            }
        }).start();
    }
}

class PrintNumber {
    String name;
    int count;

    public PrintNumber(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public synchronized void oneThreadRun() {
        while (!"oneThread".equals(name)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + (++count));
        }
        name = "twoThread";
        notifyAll();
    }

    public synchronized void twoThreadRun() {
        while (!"twoThread".equals(name)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + (++count));
        }
        name = "threeThread";
        notifyAll();
    }

    public synchronized void threeThreadRun() {
        while (!"threeThread".equals(name)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + (++count));
        }
        name = "oneThread";
        notifyAll();
    }
}