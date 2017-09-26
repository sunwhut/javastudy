package multithread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by sun on 2017/1/19.
 */
public class ThreadLocalTest {
    private static Log log = LogFactory.getLog(ThreadLocalTest.class);

    public static void main(String[] args){
        log.info("---测试ThreadLocal类");
        ThreadLoc threadLoc = new ThreadLoc();
        threadLoc.set();
        System.out.println(threadLoc.getLong());
        System.out.println(threadLoc.getString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLoc.set();
                System.out.println(threadLoc.getLong());
                System.out.println(threadLoc.getString());
            }
        }, "线程1").start();
    }
}

class ThreadLoc{
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public long getLong(){
        return longThreadLocal.get();
    }

    public String getString(){
        return stringThreadLocal.get();
    }
}