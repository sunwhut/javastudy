package multithread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(2);
        for (int i = 0; i < 10; i++) {
            int data = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.enqueue(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Integer data = queue.dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

class MyBlockingQueue<E>{
    int size;  //阻塞队列最大容量

    Lock lock = new ReentrantLock();

    LinkedList<E> list = new LinkedList<>();

    Condition notFull = lock.newCondition();  //队列满时的等待条件
    Condition notEmpty = lock.newCondition();  //队列空时的等待条件

    public MyBlockingQueue(int size){
        this.size = size;
    }

    public void enqueue(E e) throws InterruptedException {
        lock.lock();
        try{
            while (list.size() == size){  //队列已满,在notFull条件上等待
                notFull.await();
            }
            list.add(e);
            System.out.println("入队:" + e);
            notEmpty.signal();  //通知在notEmpty条件上等待的线程
        } finally {
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        try{
            while (list.size() == 0){  //队列为空,在notEmpty条件上等待
                notEmpty.await();
            }
            e = list.removeFirst();
            System.out.println("出队:" + e);
            notFull.signal();  //通知在notFull条件上等待的线程
            return e;
        } finally {
            lock.unlock();
        }
    }
}
