package datastructure;

/**
 * Created by sun on 2017/3/1.
 */

import javax.swing.tree.AbstractLayoutCache;
import java.sql.Time;

/**
 * 自己用链表实现的LinkedList类
 */
class LinkedList<E>{
    /**
     * 成员内部类Node类，代表一个结点
     * @param <E>
     */
    class Node<E>{
        E data;
        Node<E> next;

        Node(){}

        Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }
    }

    public Node<E> getHead() {
        return head;
    }

    private Node<E> head;  //头指针，也是头结点
    private Node<E> tail;  //尾指针，指向最后一个结点
    private int size;  //存储的元素的个数

    public LinkedList(){
        head = new Node<E>();
    }

    /**
     * 在链表的末尾添加指定元素
     * @param e  指定元素
     */
    public void add(E e){
        if (size == 0){
            head.data = e;
            tail = head;
        } else {
//            Node<E> node = getNode(size - 1);
            Node<E> newNode = new Node<E>(e, null);
            //在最后一个结点后添加结点
            tail.next = newNode;
            //让尾指针指向当前最后一个结点
            tail = newNode;
        }
        size++;
    }

    /**
     * 在链表的指定位置插入指定元素
     * @param index  指定位置
     * @param e  指定元素
     */
    public void add(int index, E e){
        Node<E> node = getNode(index - 1);
        Node<E> newNode = new Node<E>(e, null);
        //插入新结点
        newNode.next = node.next;
        node.next = newNode;
        size++;
    }

    /**
     * 删除列表指定位置上的元素
     * @param index  指定位置
     */
    public void remove(int index){
        validate(index);
        if (index == 0){
            head = head.next;
        } else {
            Node<E> node = getNode(index - 1);
            node.next = node.next.next;
        }
        size--;
    }

    /**
     * 将链表指定位置的值设置为指定元素
     * @param index  指定位置
     * @param e  指定元素
     */
    public void set(int index, E e){
        validate(index);
        Node<E> node = getNode(index);
        node.data = e;
    }

    /**
     * 获取链表指定位置的元素
     * @param index  指定位置
     * @return  指定位置的元素
     */
    public E get(int index){
        validate(index);
        return getNode(index).data;
    }

    /**
     * 获取指定元素在链表中第一次出现的索引，若链表中不包含此元素，返回-1
     * @param e  指定元素
     * @return  指定元素在链表中第一次出现的索引
     */
    public int indexOf(E e){
        int index = -1;
        Node<E> node = new Node<E>();
        node = head;
        for (int i = 0; i < size; i++) {
            if (e.equals(node.data)){
                index = i;
                break;
            }else {
                node = node.next;
            }
        }
        return index;
    }

    /**
     * 获取链表中元素的个数
     * @return  链表中元素的个数
     */
    public int size(){
        return size;
    }

    /**
     * 遍历链表，根据指定索引获取结点
     * @param index
     * @return
     */
    public Node<E> getNode(int index){
        validate(index);
        Node<E> node = new Node<E>();
        node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 验证索引的有效性
     * @param index  指定索引
     */
    public void validate(int index){
        if (index < 0 || index >= size){
            throw new RuntimeException("索引值异常:" + index);
        }
    }

    /**
     * 反转链表
     */
    public void reverse(){
        //链表为空或只有一个节点，无需反转，直接返回原链表头指针
        if (head == null || head.next == null){
            return;
        }
        Node<E> prev = head;
        Node<E> node = head;
        Node<E> next = head.next;
        while (next != null){
            if (node == head){
                node.next = null;
                node = next;
                next = next.next;
            }else {
                node.next = prev;
                prev = node;
                node = next;
                next = next.next;
            }
        }
        node.next = prev;
        head = node;
    }

    /**
     * 从尾到头打印单链表
     */
    public void reversePrintList(){
        reversePrint(head);
    }

    /**
     * 从尾到头打印单链表，采用递归方式实现
     * @param node  结点
     */
    public void reversePrint(Node<E> node){
        if (node != null){
            reversePrint(node.next);
            System.out.println(node.data);
        }
    }

    /**
     * 获取单链表中倒数第K个节点
     * @param pHead  头指针
     * @param k  倒数第K个
     * @return  倒数第K个节点
     */
    public Node<E> getKthNode(Node<E> pHead, int k){
        //链表为空或k<1返回null
        if (pHead == null || k < 1){
             return null;
        }
        Node ahead = pHead;
        Node behind = pHead;
        while (k > 1 && ahead.next != null){
            ahead = ahead.next;
            k--;
        }
        //链表中节点个数小于k，返回null
        if (k > 1){
            return null;
        }
        while (ahead.next != null){
            ahead = ahead.next;
            behind = behind.next;
        }
        return behind;
    }

    /**
     * 判断链表中是否有环
     * @param head  头指针
     * @return  如果链表中有环返回true，否则返回false
     */
    public boolean hasLoop(Node<E> head){
        Node fast = head;
        Node slow = head;
        if (fast == null){
            return false;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
            if (fast.next != null){
                fast = fast.next;
                if (fast == slow){
                    return true;
                }
            }
        }
        return !(fast.next == null);
    }

    /**
     * 找到链表中环的入口节点
     * @param head  头指针
     * @return  环的入口节点
     */
    public Node<E> findLoopEntrance(Node<E> head){
        Node fast = head;
        Node slow = head;
        if (fast == null){
            return null;
        }
        while (fast != null || fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                break;
            }
        }

        if (fast == null || fast.next == null){
            return null;
        }

        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 获取链表的长度
     * @param head
     * @return
     */
    public int getLength(Node head){
        if (head == null)
            return 0;

        int result = 0;
        while (head != null){
            result++;
            head = head.next;
        }
        return result;
    }

    /**
     * 获取两个链表的第一个公共节点
     * @param head1
     * @param head2
     * @return
     */
    public Node getFirstCommonNode(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return null;
        }

        int len1 = getLength(head1);
        int len2 = getLength(head2);
        int diff = Math.abs(len1 - len2);

        Node longHead = head1;
        Node shortHead = head2;
        if (len1 < len2){
            longHead = head2;
            shortHead = head1;
        }

        while (diff > 0){
            longHead = longHead.next;
            diff--;
        }

        while (longHead != shortHead){
            longHead = longHead.next;
            shortHead = shortHead.next;
        }
        return longHead;
    }
}

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList myLinkedList = new LinkedList();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(6);
        myLinkedList.add(7);

        LinkedList myLinkedList2 = new LinkedList();
        myLinkedList2.add(4);
        myLinkedList2.add(5);
        myLinkedList2.add(6);
        myLinkedList2.add(7);

        System.out.println(myLinkedList.getFirstCommonNode(myLinkedList.getHead(),
                myLinkedList2.getHead()).data);

//        myLinkedList.add(2, 6);
//        myLinkedList.remove(2);
//        myLinkedList.set(2, 6);
//        System.out.println(myLinkedList.indexOf(3));
        //反转链表
//        myLinkedList.reverse();
        //从尾到头打印链表
//        myLinkedList.reversePrintList();
        for (int i = 0; i < myLinkedList.size(); i++) {
            if (i == myLinkedList.size() - 1){
                System.out.println(myLinkedList.get(i));
            }else {
                System.out.print(myLinkedList.get(i) + " -> ");
            }
        }
//        System.out.println(myLinkedList.getKthNode(myLinkedList.getHead(), 1).data);
        System.out.println("链表中是否有环： " + myLinkedList.hasLoop(myLinkedList.getHead()));
//        System.out.println("链表中环的入口节点： " + myLinkedList.findLoopEntrance(myLinkedList.getHead()).data);

        //测试自己实现的LinkedList添加100000条数据所需时间
        myLinkedList = new LinkedList();
        Long myBeginTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            myLinkedList.add(i);
        }
        Long myEndTime = System.currentTimeMillis();
        System.out.println("自己实现的LinkedList所需时间: " + (myEndTime - myBeginTime) + "ms");

        //测试JDK提供的LinkedList添加100000条数据所需时间
        java.util.LinkedList linkedList = new java.util.LinkedList();
        Long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("JDK提供的LinkedList所需时间: " + (endTime - beginTime) + "ms");
    }
}
