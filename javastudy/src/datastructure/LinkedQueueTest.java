package datastructure;

/**
 * Created by sun on 2017/3/2.
 */
//自己用链表实现的链队列
class LinkedQueue<E>{
    //成员内部类Node,用来存储结点
    class Node<E>{
        E e;  //数据域
        Node<E> next;  //指针域

        Node(){}

        Node(E e, Node<E> next){
            this.e = e;
            this.next = next;
        }
    }

    private Node<E> front;  //队头指针
    private Node<E> rear;  //队尾指针
    private int size;  //队列大小，即存储元素的个数

    public LinkedQueue(){
        front = new Node<E>();
        rear = front;
        size = 0;
    }

    /**
     * 在队尾插入指定元素
     * @param e  指定元素
     * @return  插入成功返回true
     */
    public boolean offer(E e){
        Node<E> node = new Node<E>(e, null);
        //入队操作
        rear.next = node;
        rear = node;
        size++;
        return true;
    }

    /**
     * 在队头移出元素
     * @return  队头元素
     */
    public E poll(){
        if (empty()){
            throw new RuntimeException("队列为空！");
        } else {
            Node<E> p = front.next;
            E value = p.e;
            //出队操作
            front.next = p.next;
            if (rear == p){
                rear = front;
            }
            size--;
            return value;
        }
    }

    /**
     * 获取队头元素
     * @return  队头元素
     */
    public E peek(){
        if (empty()){
            throw new RuntimeException("队列为空!");
        } else {
            return front.next.e;
        }
    }

    /**
     * 查看队列是否为空
     * @return  队列为空返回true,否则返回false
     */
    public boolean empty(){
        return (size == 0);
    }

    /**
     * 获取队列大小
     * @return  队列大小
     */
    public int size(){
        return size;
    }
}

public class LinkedQueueTest {
    public static void main(String[] args) {
         LinkedQueue linkedQueue = new LinkedQueue();
         linkedQueue.offer(1);
         linkedQueue.offer(2);
         linkedQueue.offer(3);
         linkedQueue.offer(4);
         linkedQueue.offer(5);
//        System.out.println(linkedQueue.peek());
        for (int i = 0; i < 5; i++) {
            System.out.println(linkedQueue.poll());
        }
    }
}
