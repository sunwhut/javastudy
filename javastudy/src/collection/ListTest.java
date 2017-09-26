package collection;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by sun on 2016/12/29.
 */
public class ListTest {
    public static void main(String[] args){
        //测试ArrayList用法
        System.out.println("---测试ArrayList用法---");
        List<String> arrayList = new ArrayList<String>();
        //往ArrayList中增加一个元素
        arrayList.add("Jack");
        arrayList.add("Rose");
        arrayList.add("Mary");
        arrayList.add(1, "Neo");
        printList(arrayList);
        //往ArrayList中删除一个元素
        arrayList.remove("Rose");
        printList(arrayList);
        //往ArrayList中修改一个元素
        arrayList.set(2, "Frank");
        printList(arrayList);
        //往ArrayList中查看一个元素
        System.out.println(arrayList.get(1) + "\n-----");
        //在ArrayList中查询是否包含某元素
        System.out.println(arrayList.contains("Neo") + "\n-----");
        System.out.println(arrayList.indexOf("Neo") + "\n-----");
        System.out.println(arrayList.indexOf("Rose") + "\n-----");
        //判断ArrayList是否为空
        System.out.println("ArrayList是否为空： " + arrayList.isEmpty() + "\n-----");     //推荐使用
        System.out.println("ArrayList是否为空： " + arrayList.size() + "\n-----");
        arrayList.clear();     //清空数组列表
        System.out.println("ArrayList是否为空： " + arrayList.isEmpty() + "\n-----");     //推荐使用

        //测试LinkedList用法
        System.out.println("---测试LinkedList用法---");
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.addFirst("Jackson");
        linkedList.addFirst("Jane");
        linkedList.addLast("Mark");
        printList(linkedList);
        System.out.println(linkedList.removeFirst() + "\n-----");
        System.out.println(linkedList.removeLast() + "\n-----");
        printList(linkedList);
        //用LinkedList来表示一个栈
        System.out.println("---用LinkedList来表示一个栈---");
        LinkedList<String> stack = new LinkedList<String>();
        stack.push("Anny");
        stack.push("Portman");
        stack.push("Lily");
        printList(stack);
        System.out.println(stack.pop() + "\n-----");
        printList(stack);
        //用LinkedList来表示一个队列
        System.out.println("---用LinkedList来表示一个队列---");
        LinkedList<String> queue = new LinkedList<String>();
        queue.offerFirst("Moretz");
        queue.offerFirst("Hathaway");
        queue.offerLast("Scarlett");
        printList(queue);
        System.out.println(queue.pollFirst() + "\n-----");
        System.out.println(queue.pollLast() + "\n-----");
        printList(queue);
        System.out.println(queue.peek() + "\n-----");

        //测试Stack用法
        System.out.println("---测试Stack用法---");
        Stack<String> oneStack = new Stack<String>();
        oneStack.push("Smith");
        oneStack.push("Van");
        oneStack.push("Wil");
        printList(oneStack);
        System.out.println(oneStack.pop() + "\n-----");
        System.out.println(oneStack.peek() + "\n-----");
        printList(oneStack);
        System.out.println(oneStack.search("Smith") + "\n-----");     //查找元素并返回到栈顶的距离，从1开始
        System.out.println("栈是否为空: " + oneStack.empty() + "\n-----");
        System.out.println("栈是否为空: " + oneStack.isEmpty() + "\n-----");
    }

    //打印String类型的List接口实现类的对象
    public static void printList(List<String> list){
        for (String element : list) {
            System.out.println(element);
        }
        System.out.println("-----");
    }
}
