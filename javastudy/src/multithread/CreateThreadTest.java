package multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateThreadTest {
    public static void main(String[] args) {
        ThreadTest a = new ThreadTest();
        a.start();
        a.interrupt();
//        new ThreadTest().start();

//        RunnableTest runnableTest = new RunnableTest();
//        new Thread(runnableTest, "线程1").start();
//        new Thread(runnableTest, "线程2").start();

        System.out.println("主线程执行开始");
//        Thread threadA = new Thread(new RunnableTest(), "线程A");
//        threadA.start();
//        try {
//            threadA.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("主线程执行结束");
        /*
        CallableTest callableTest = new CallableTest();
        FutureTask<Integer> futureTask = new FutureTask<>(callableTest);
        new Thread(futureTask).start();
        try {
            System.out.println("子线程的返回值: " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
         */
    }
}

class ThreadTest extends Thread{
    private int i = 0;

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " is running: " + i);
        }
    }
}

class RunnableTest implements Runnable{
    private int i = 0;

    @Override
    public void run() {

//        for (; i < 100; i++) {
//            System.out.println(Thread.currentThread().getName()  + " is running: " + i);
//        }
        System.out.println(Thread.currentThread().getName() + "执行开始");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束");
    }
}

class CallableTest implements Callable{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i < 101; i++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + " is running: " + sum);
        return sum;
    }
}
