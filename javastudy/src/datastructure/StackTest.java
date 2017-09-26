package datastructure;

/**
 * Created by sun on 2017/3/1.
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * 自己用数组实现的栈
 * @param <E>
 */
class Stack<E>{
    private Object[] elementData;  //存储元素的数组
    private int size;  //栈的大小，即存储元素的个数
    private int capacity;  //容量

    public Stack(){
        capacity = 10;
        elementData = new Object[capacity];
        size = 0;
    }

    /**
     * 在栈顶入栈
     * @param e
     */
    public void push(E e){
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    /**
     * 在栈顶出栈，并返回栈顶元素
     * @return  栈顶元素
     */
    public E pop(){
        return (E) elementData[--size];
    }

    /**
     * 查看栈顶元素，但不删除
     * @return  栈顶元素
     */
    public E peek(){
        return (E) elementData[size - 1];
    }

    /**
     * 查看栈是否为空
     * @return  栈为空时，返回true;否则返回false
     */
    public boolean empty(){
        return (size == 0);
    }

    /**
     * 获取指定元素离栈顶最近的出现位置到栈顶的距离，堆栈中栈顶的距离为1，若指定元素不在栈中返回-1
     * @param e  指定元素
     * @return  指定元素到栈顶的距离
     */
    public int search(E e){
        int value = -1;
        for (int i = size-1; i > 0; i--) {
            if (e.equals(elementData[i])){
                value = size - i;
                break;
            }
        }
        return value;
    }

    /**
     * 获取栈的大小
     * @return  栈的大小
     */
    public int size(){
        return size;
    }

    /**
     * 确认栈的当前容量大于等于指定最小容量，否则需要扩容
     * @param minCapacity
     */
    public void ensureCapacity(int minCapacity){
        if (capacity < minCapacity){
            //capacity扩大2倍，直到大于等于指定最小容量为止
            while (capacity < minCapacity){
                capacity <<= 1;
            }
            //返回指定数组的副本，并使副本具有指定长度
            elementData = Arrays.copyOf(elementData, capacity);
        }
    }
}

public class StackTest {
    public static void main(String[] args) {
        Stack myStack = new Stack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
//        System.out.println(myStack.search(3));
        for (int i = 0; i < 5; i++) {
//            System.out.println(myStack.pop());
        }

        //从控制台中接收一个二进制串，并将其转换为十进制
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] charArray = str.toCharArray();
        int number;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < charArray.length; i++) {
            //将字符型转换为int型
//            number =  Integer.parseInt(String.valueOf(charArray[i]));  //方法1
            number = charArray[i] - 48;  //方法2
            //将二进制串从高位到低位依次入栈
            stack.push(number);
        }
        double sum = 0;
        for (int i = 0; i < charArray.length; i++) {
            number = stack.pop();
            //第i位的值为第i位乘以2^(i-1),再将每一位的值累加
            sum += number * Math.pow(2, i);
        }
        System.out.println(sum);
    }
}
