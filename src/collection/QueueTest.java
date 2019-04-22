package collection;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by sun on 2016/12/29.
 */
public class QueueTest {
    public static void main(String[] args){
        //测试PriorityQueue用法
        System.out.println("---测试PriorityQueue用法---");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        priorityQueue.offer(1);
        priorityQueue.offer(3);
        priorityQueue.offer(5);
        priorityQueue.offer(5);
        priorityQueue.offer(7);
        printQueue(priorityQueue);
        System.out.println(priorityQueue.poll() + "\n-----");
        System.out.println(priorityQueue.peek() + "\n-----");
        printQueue(priorityQueue);

        //测试ArrayDeque用法
        System.out.println("---测试ArrayDeque用法---");
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        arrayDeque.offer(3);
        arrayDeque.offer(5);
        printQueue(arrayDeque);
        System.out.println(arrayDeque.poll() + "\n-----");
        System.out.println(arrayDeque.peek() + "\n-----");
        printQueue(arrayDeque);

        //用ArrayDeque来表示一个栈
        System.out.println("---用ArrayDeque来表示一个栈---");
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        stack.offerFirst(1);
        stack.offerFirst(3);
        stack.offerFirst(9);
        stack.offerFirst(11);
        printQueue(stack);
        System.out.println(stack.pollFirst() + "\n-----");
        System.out.println(stack.peekFirst() + "\n-----");
        printQueue(stack);

        //用ArrayDeque来表示一个队列
        System.out.println("---用ArrayDeque来表示一个队列---");
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        queue.offerLast(1);
        queue.offerLast(2);
        queue.offerLast(5);
        queue.offerLast(7);
        printQueue(queue);
        System.out.println(queue.pollFirst() + "\n-----");
        System.out.println(queue.peekFirst() + "\n-----");
        printQueue(queue);

    }

    /**
     * 打印Integer类型的Queue实现类的对象
     * @param queue Integer类型的Queue实现类的对象
     */
    public static void printQueue(Queue<Integer> queue){
        for (Integer element : queue) {
            System.out.println(element);
        }
        System.out.println("-----");
    }
}
