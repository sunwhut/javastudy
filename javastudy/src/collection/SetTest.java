package collection;

import java.util.*;

/**
 * Created by sun on 2016/12/29.
 */
public class SetTest {
    public static void main(String[] args){
        //测试HashSet用法
        System.out.println("---测试HashSet用法---");
        Set<String> hashSet = new HashSet<String>();
        hashSet.add("hello");
        hashSet.add("world");
        hashSet.add("hello");
        hashSet.add("java");
        printSet(hashSet);
        hashSet.remove("hello");
        printSet(hashSet);
        System.out.println(hashSet.contains("java") + "\n-----");

        //测试LinkedHashSet用法
        System.out.println("---测试LinkedHashSet用法---");
        Set<String> linkedHashSet = new LinkedHashSet<String>();
        linkedHashSet.add("hello");
        linkedHashSet.add("world");
        linkedHashSet.add("hello");
        linkedHashSet.add("java");
        printSet(linkedHashSet);
        linkedHashSet.remove("hello");
        printSet(linkedHashSet);
        System.out.println(linkedHashSet.contains("java") + "\n-----");

        //测试TreeSet用法
        System.out.println("---测试TreeSet用法---");
        TreeSet<String> treeSet = new TreeSet<String>();
        treeSet.add("hello");
        treeSet.add("world");
        treeSet.add("hello");
        treeSet.add("java");
        treeSet.add("set");
        printSet(treeSet);
        treeSet.remove("hello");
        printSet(treeSet);
        System.out.println(treeSet.contains("java") + "\n-----");
        System.out.println(treeSet.first() + "\n-----");
        System.out.println(treeSet.last() + "\n-----");
        System.out.print("头有序子集合： ");
        printSet(treeSet.headSet("set"));
        System.out.print("尾有序子集合： ");
        printSet(treeSet.tailSet("set"));
    }

    //打印String类型的Set接口实现类的对象
    public static void printSet(Set<String> set){
        for (String element : set) {
            System.out.println(element);
        }
        System.out.println("-----");
    }
}
