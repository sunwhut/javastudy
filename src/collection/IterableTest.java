package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by sun on 2017/1/5.
 */
public class IterableTest {
    public static void main(String[] args){
        //测试Iterable实现类
        System.out.println("---测试Iterable实现类---");
        MyString myString = new MyString("hello");
        for (char ch : myString) {
            System.out.println(ch);
        }
        //测试Iterator的使用
        System.out.println("---测试Iterator的使用---");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("hello");
        arrayList.add("java");
        arrayList.add("world");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Iterator<String> iterator2 = arrayList.iterator();
        iterator2.next();
        iterator2.remove();    //删除上次调用next方法时返回的元素
        iterator2.next();
        iterator2.remove();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }
    }
}

/**
 * 定义一个类，实现Iterable接口
 */
class MyString implements Iterable<Character>{
    private int length = 0;
    private String str = "";

    /**
     * 构造方法
     * @param s  字符串
     */
    public MyString(String s){
        str = s;
        length = s.length();
    }

    /**
     * 重写iterator方法，返回一个Iterator对象
     * @return
     */
    public Iterator<Character> iterator(){
        /**
         * 返回一个Iterator匿名类的实例化对象
         */
        return new Iterator<Character>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index != length;
            }

            @Override
            public Character next() {
                Character character = str.charAt(index);
                index++;
                return character;
            }
        };
    }
}