package collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sun on 2017/1/10.
 */
public class CollectionsTest {
    private static Log log = LogFactory.getLog(CollectionsTest.class);

    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<String>();;
        arrayList.add("sun");
        arrayList.add("li");
        arrayList.add("wang");
        arrayList.add("he");
        printList(arrayList);
        //自然排序
        log.info("---自然排序---");
        Collections.sort(arrayList);
        printList(arrayList);
        //二分查找
        log.info("---二分查找---");
        System.out.println(Collections.binarySearch(arrayList, "sun"));
        //交换
        log.info("---交换---");
        Collections.swap(arrayList, 0, 2);
        printList(arrayList);
        //反转
        log.info("---反转---");
        Collections.reverse(arrayList);
        printList(arrayList);
        //随机排序
        log.info("---随机排序---");
        Collections.shuffle(arrayList);
        printList(arrayList);
        //返回最大元素
        log.info("---返回最大元素---");
        System.out.println(Collections.max(arrayList));
        //返回最小元素
        log.info("---返回最小元素---");
        System.out.println(Collections.min(arrayList));
        //返回指定列表对应的同步对象
        log.info("---返回指定列表对应的同步对象---");
        printList(Collections.synchronizedList(arrayList));
    }

    /**
     * 打印String类型List实现类的对象
     * @param list
     */
    public static void printList(List<String> list){
        for (String string : list) {
            System.out.println(string);
        }
    }
}
