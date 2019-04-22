package multithread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by sun on 2017/2/7.
 */
public class ThreadGroupTest {
    private static Log log = LogFactory.getLog(ThreadGroupTest.class);

    public static void main(String[] args){
        log.info("---测试ThreadGroupTest类---");
        ThreadGroup threadGroup1 = new ThreadGroup("线程组1");
        ThreadGroup threadGroup2 = new ThreadGroup("线程组2");
        Thread thread1 = new Thread(threadGroup1, new MThread(), "线程1");
        Thread thread2 = new Thread(threadGroup2, new MThread(), "线程2");
        thread1.start();
        thread2.start();
    }
}

class MThread implements Runnable{
    @Override
    public void run() {
        System.out.println("线程组名称： " + Thread.currentThread().getThreadGroup().getName()
                + " , 线程名： " + Thread.currentThread().getName());
    }
}
