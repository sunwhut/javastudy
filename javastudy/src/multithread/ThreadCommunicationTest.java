package multithread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by sun on 2017/1/18.
 */
public class ThreadCommunicationTest {
    private static Log log = LogFactory.getLog(ThreadCreateTest.class);

    public static void main(String[] args){
        //测试线程通信
        log.info("---测试线程通信---");
        ShareData shareData = new ShareData();
        Thread producer = new Thread(new Producer(shareData), "生产者");
        Thread consumer = new Thread(new Consumer(shareData), "消费者");
        consumer.start();
        producer.start();
    }
}

/**
 * 定义一个类，包含一个共享成员变量和一个标志位
 * 定义了设置方法和访问方法
 */
class ShareData{
    private char ch;
    private boolean writable = true;

    /**
     * 设置方法，用来生产一个字符
     * @param c  字符
     */
    public synchronized void put(char c){
        //若writable为false，表示生产的字符还未被消费，先等待
        while (!writable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.ch = c;
        //生产后，修改writable为false，并通知消费者消费
        writable = false;
        notifyAll();
    }

    /**
     * 访问方法，用来消费一个字符
     * @return  返回消费的字符
     */
    public synchronized char get(){
        //若writable为true,表示字符还未生产出来，先等待
        while (writable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费后，修改writable为true，并通知生产者生产
        writable = true;
        notifyAll();
        return ch;
    }
}

/**
 *定义一个生成者类，实现Runnable接口
 */
class Producer implements Runnable{
    private ShareData shareData;
    private static Log log = LogFactory.getLog(Producer.class);

    public Producer(ShareData sd){
        shareData = sd;
    }

    @Override
    public void run() {
        for (char ch = 'A'; ch <= 'Z'; ch++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shareData.put(ch);
//            System.out.println(Thread.currentThread().getName() + "线程生产 : " + ch);
            log.info(Thread.currentThread().getName() + "线程,生产 : " + ch);
        }
    }
}

/**
 * 定义一个消费者类，实现Runable接口
 */
class Consumer implements Runnable{
    private ShareData shareData;
    private static Log log = LogFactory.getLog(Consumer.class);

    public Consumer(ShareData sd){
        shareData = sd;
    }

    @Override
    public void run() {
        char ch;
        do{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ch = shareData.get();
//            System.out.println(Thread.currentThread().getName() + "线程消费 ：" + ch);
            log.info(Thread.currentThread().getName() + "线程,消费 ：" + ch);
        }while (ch != 'Z');
    }
}