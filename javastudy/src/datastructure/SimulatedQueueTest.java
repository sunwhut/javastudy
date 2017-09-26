package datastructure;

/**
 * Created by sun on 2017/3/2.
 */

/**
 * 用两个栈模拟一个队列
 * @param <E>
 */
class SimulatedQueue<E>{
    private Stack<E> stack1;
    private Stack<E> stack2;

    public SimulatedQueue(){
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    /**
     * 在队尾插入指定元素
     * @param e  指定元素
     */
    public void offer(E e){
        stack1.push(e);
    }

    /**
     * 在队头移出元素
     * @return  队头元素
     */
    public E poll(){
        if (stack2.size() == 0){
            while (stack1.size() > 0){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
public class SimulatedQueueTest {
    public static void main(String[] args) {
        SimulatedQueue simulatedQueue = new SimulatedQueue();
        simulatedQueue.offer(1);
        simulatedQueue.offer(2);
        simulatedQueue.offer(3);
        System.out.println(simulatedQueue.poll());
        simulatedQueue.offer(4);
        System.out.println(simulatedQueue.poll());
        simulatedQueue.offer(5);
        System.out.println(simulatedQueue.poll());
        System.out.println(simulatedQueue.poll());
        System.out.println(simulatedQueue.poll());
    }
}
