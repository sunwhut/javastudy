package collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sun on 2017/1/9.
 */
public class CollectionArrayConvert {
    private static Log logger = LogFactory.getLog(CollectionArrayConvert.class);

    public static void main(String[] args) {
        //集合转数组,List转换为Array
        logger.info("---List转换为Array---");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("java");
        String[] stringArray = arrayList.toArray(new String[arrayList.size()]);
        for (String string : stringArray) {
            System.out.println(string);
        }
        //数组转集合,Array转换为List
        logger.info("---Array转换为List---");
        Integer[] integers = {1, 2, 3, 3, 4, 5, 6};
        List<Integer> integerList = Arrays.asList(integers);
        for (Integer integer : integerList) {
            System.out.println(integer);
        }
        //数组转换成的集合不支持增删的操作，只支持改查的操作，因为返回的ArrayList类对象是Arrays的一个私有静态类
        integerList.set(3, 7);
        for (Integer integer : integerList) {
            System.out.println(integer);
        }
        System.out.println("索引为3的元素 : " + integerList.get(3));
        //如果要支持动态操作，可以用转换后的集合创建一个真正的ArrayList类
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>(integerList);
        integerArrayList.add(8);
        for (Integer integer : integerArrayList) {
            System.out.println(integer);
        }
    }
}
