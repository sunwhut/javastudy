package multithread;

/**
 * Created by Administrator on 2017/9/5.
 */

/**
 * 面试题:编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 */
public class MainThreadAndSubThread {
    public static void main(String[] args) {
        final SubThread subThread = new SubThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    subThread.subthreadRun();
                }
            }
        }).start();
        for (int i = 0; i < 10; i++) {
            subThread.mainthreadRun();
        }
    }
}

class SubThread {
    private boolean subRunnable = true;  //子线程可执行时，该标志位为true

    public synchronized void subthreadRun() {
        while (!subRunnable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("SubThread" + ":" + i);
        }
        subRunnable = false;
        notifyAll();
    }

    public synchronized void mainthreadRun() {
        while (subRunnable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("MainThread" + ":" + i);
        }
        subRunnable = true;
        notifyAll();
    }
}
