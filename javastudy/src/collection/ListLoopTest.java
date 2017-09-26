package collection;

import com.sun.org.apache.bcel.internal.classfile.DescendingVisitor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Logger;

/**
 * Created by sun on 2017/1/9.
 * 测试集合的
 */
public class ListLoopTest {
    private static Log log = LogFactory.getLog(ListLoopTest.class);

    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("java");
        arrayList.add("python");
        //第一种，使用for循环进行遍历
        log.info("---使用for循环进行遍历---");
        for (int i = 0; i < arrayList.size(); i++){
            System.out.println(arrayList.get(i));
            /*  在for循环中删除元素，列表的大小在发生变化，同一个下标对应的元素也在发生变化  */
//            arrayList.remove(i);
        }

        //第二种，使用foreach循环进行遍历
        log.info("---使用foreach循环进行遍历---");
        for (String string : arrayList) {
            System.out.println(string);
            /*  foreach循环中调用remove方法删除元素会报错  */
//            arrayList.remove("hello");
        }

        //第三种，使用Iterator进行遍历
        log.info("---使用Iterator进行遍历---");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //第四种，使用ListIterator进行遍历
        log.info("---使用ListIterator进行遍历---");
        ListIterator<String> listIterator = arrayList.listIterator();
        System.out.println("---由前向后遍历---");
        while (listIterator.hasNext()){
            System.out.println(listIterator.next());
        }
        System.out.println("---由后向前遍历---");
        while (listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }

        //第五种，LinkedList可以使用DescendingIterator进行遍历
        log.info("---LinkedList可以使用DescendingIterator进行遍历---");
        LinkedList<String> linkedList = new LinkedList<String>(arrayList);
        Iterator<String> stringIterator = linkedList.descendingIterator();
        while (stringIterator.hasNext()){
            System.out.println(stringIterator.next());
        }

    }
}
