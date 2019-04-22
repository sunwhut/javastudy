package multithread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by sun on 2017/1/19.
 */
public class PrintABCTest {
    private static Log log = LogFactory.getLog(PrintABCTest.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("---打印ABC测试---");
        /*GetChar getChar = new GetChar();
        new Thread(new AThread(getChar)).start();
        new Thread(new BThread(getChar)).start();
        new Thread(new CThread(getChar)).start();*/
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Thread ThreadA = new Thread(new PrintThread("A", c, a), "线程A");
        Thread ThreadB = new Thread(new PrintThread("B", a, b), "线程B");
        Thread ThreadC = new Thread(new PrintThread("C", b, c), "线程C");
        ThreadA.start();
        Thread.sleep(100);
        ThreadB.start();
        Thread.sleep(100);
        ThreadC.start();
        Thread.sleep(100);

    }
}

/**
 * 定义一个普通类，包含三个方法，分别获取字符'A','B','C'
 */
class GetChar{
    private char ch;
    private boolean aAvailable = true;
    private boolean bAvailable = false;
    private boolean cAvailable = false;

    /**
     * 获取字符'A'
     * @return
     */
    public synchronized char getA(){
        while (!aAvailable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ch = 'A';
        aAvailable = false;
        bAvailable = true;
        notifyAll();
        return ch;
    }

    /**
     * 获取字符'B'
     * @return
     */
    public synchronized char getB(){
        while (!bAvailable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ch = 'B';
        bAvailable = false;
        cAvailable = true;
        notifyAll();
        return ch;
    }

    /**
     * 获取字符'C'
     * @return
     */
    public synchronized char getC(){
        while (!cAvailable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ch = 'C';
        cAvailable = false;
        aAvailable = true;
        notifyAll();
        return ch;
    }
}

/**
 * A线程，获取字符'A'
 */
class AThread implements Runnable{
    private GetChar getChar;

    public AThread(GetChar g){
        getChar = g;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + ": " + getChar.getA());
        }
    }
}

/**
 * B线程，获取字符'B'
 */
class BThread implements Runnable{
    private GetChar getChar;

    public BThread(GetChar g){
        getChar = g;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + " : " + getChar.getB());
        }
    }
}

/**
 * C线程，获取字符'C'
 */
class CThread implements Runnable{
    private GetChar getChar;

    public CThread(GetChar g){
        getChar = g;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + ": " + getChar.getC());
        }
    }
}

/**
 *定义一个打印线程类，实现Runnable接口
 */
class PrintThread implements Runnable{
    private String name;
    private Object prev;
    private Object self;

    public PrintThread(String n, Object p, Object s){
        name = n;
        prev = p;
        self = s;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0){
            //先获取前置对象的锁
            synchronized (prev){
                //再获取当前对象的锁
                synchronized (self){
                    System.out.println(Thread.currentThread().getName() + " : " + name);
                    count--;

                    self.notify();    //唤醒等待当前对象的对象锁的线程
                }

                try {
                    prev.wait();    //释放前置对象的锁，当前线程进入等待前置对象的对象锁的等待池
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}