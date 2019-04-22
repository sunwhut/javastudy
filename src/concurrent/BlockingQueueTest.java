package concurrent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by sun on 2017/1/18.
 */
public class BlockingQueueTest {
    private static Log log = LogFactory.getLog(BlockingQueueTest.class);

    public static void main(String[] args){
        log.info("---测试阻塞队列---");
        BlockingQueue<Character> blockingQueue = new ArrayBlockingQueue<Character>(1);
        Thread producer = new Thread(new Producer(blockingQueue), "生产者");
        Thread consumer = new Thread(new Consumer(blockingQueue), "消费者");
        consumer.start();
        producer.start();
    }
}

/**
 * 定义一个生产者类，实现Runnable接口
 */
class Producer implements Runnable{
    private BlockingQueue<Character> blockingQueue;

    public Producer(BlockingQueue<Character> bq){
        blockingQueue = bq;
    }

    @Override
    public void run() {
        for (char ch = 'A'; ch <= 'Z'; ch++){
            try {
                Thread.sleep(1000);
                blockingQueue.put(ch);
                System.out.println(Thread.currentThread().getName() + "线程,生产 : " + ch);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 定义一个消费者类，实现Runnable接口
 */
class Consumer implements Runnable{
    private BlockingQueue<Character> blockingQueue;

    public Consumer(BlockingQueue<Character> bq){
        blockingQueue = bq;
    }

    @Override
    public void run() {
        char ch = '\0';
        do{
            char tmp;
            try {
                Thread.sleep(1000);
                ch = blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程消费 ：" + ch);
        }while (ch != 'Z');
    }
}
