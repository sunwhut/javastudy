package multithread;

/**
 * Created by Administrator on 2017/9/5.
 */

/**
 * 面试题:设计四个线程,其中两个线程每次对变量i加1,另外两个线程每次对i减1.
 */
public class FourThreadOperateNumber {
    private int i = 0;

    public static void main(String[] args) {
        FourThreadOperateNumber f = new FourThreadOperateNumber();
        Add add = f.new Add();
        Sub sub = f.new Sub();
        new Thread(add, "ADD线程1").start();
        new Thread(add, "ADD线程2").start();
        new Thread(sub, "SUB线程1").start();
        new Thread(sub, "SUB线程2").start();
    }

    class Add implements Runnable{
        @Override
        public void run() {
            for (int j = 0; j < 10; j++) {
                addOne();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Sub implements Runnable{
        @Override
        public void run() {
            for (int j = 0; j < 10; j++) {
                subOne();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void addOne() {
        System.out.println(Thread.currentThread().getName() + ": " + (++i));
    }

    public synchronized void subOne(){
        System.out.println(Thread.currentThread().getName() + ": " + (--i));
    }

}


