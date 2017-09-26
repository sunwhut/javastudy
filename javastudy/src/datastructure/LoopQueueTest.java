package datastructure;

/**
 * Created by sun on 2017/3/2.
 */

/**
 * 自己用数组实现的循环队列
 * @param <E>
 */
class LoopQueue<E>{
    private Object[] elementData;  //存储数据的数组
    private int front;  //队头下标
    private int rear;  //队尾下标
    private int size;  //队列长度
    private int maxSize;  //队列容量

    public LoopQueue(){
        //队列默认容量为10
        this(10);
    }

    public LoopQueue(int initialSize){
        maxSize = initialSize;
        elementData = new Object[maxSize];
        front = rear = 0;
        size = 0;
    }

    /**
     * 在队尾插入指定元素
     * @param e  指定元素
     * @return  插入成功返回true,否则返回false
     */
    public boolean offer(E e){
        if ( (rear + 1) % maxSize == front){
            throw new RuntimeException("队列已满！");
        } else {
            elementData[rear] = e;
            rear = (rear + 1) % maxSize;
            size++;
            return true;
        }
    }

    /**
     * 在队头移出元素
     * @return  队头元素
     */
    public E poll(){
        if (rear == front){
            throw new RuntimeException("队列为空！");
        } else {
            E e = (E) elementData[front];
            elementData[front] = null;
            front = (front + 1) % maxSize;
            size--;
            return e;
        }
    }

    /**
     * 查看队头元素
     * @return  队头元素
     */
    public E peek(){
        if (rear == front){
            throw new RuntimeException("队列为空！");
        } else {
            return (E) elementData[front];
        }
    }

    /**
     * 查看队列是否为空
     * @return  若队列为空，返回true，否则返回false
     */
    public boolean empty(){
        return (size == 0);
    }

    /**
     * 查看队列大小
     * @return  队列大小
     */
    public int size(){
        return size;
    }
}
public class LoopQueueTest {
    public static void main(String[] args) {
        LoopQueue loopQueue = new LoopQueue(5);
        loopQueue.offer(1);
        loopQueue.offer(2);
        loopQueue.offer(3);
        loopQueue.offer(4);
        System.out.println(loopQueue.poll());
        System.out.println(loopQueue.poll());
        loopQueue.offer(5);
        for (int i = 0; i < 4; i++) {
//            System.out.println(loopQueue.poll());
        }
    }
}
