package collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sun on 2017/1/9.
 */
public class MapLoopTest {
    private static Log log = LogFactory.getLog(MapLoopTest.class);

    public static void main(String[] args){
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("sun", 23);
        hashMap.put("he", 23);
        hashMap.put("li", 27);
        hashMap.put("wang", 24);
        //第一种，通过Map.entrySet使用iterator遍历key和value
        log.info("---通过Map.entrySet使用iterator遍历key和value---");
        Iterator<Map.Entry<String, Integer>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
        }

        //第二种，通过Map.entrySet使用foreach遍历key和value
        log.info("---通过Map.entrySet使用foreach遍历key和value---");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
        }

        //第三种，通过Map.keySet使用foreach遍历key和value
        log.info("---通过Map.keySet使用foreach遍历key和value---");
        for (String string : hashMap.keySet()) {
            System.out.println("key : " + string + ", value : " + hashMap.get(string));
        }

        //第四种，通过Map.values使用foreach遍历所有的value，但不能遍历key
        log.info("---通过Map.values使用foreach遍历所有的value，但不能遍历key---");
        for (Integer integer : hashMap.values()) {
            System.out.println("value : " + integer);
        }

        //第五种，TreeMap通过descendingKeySet使用foreach降序遍历key和value
        log.info("---通过descendingKeySet使用foreach降序遍历key和value---");
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(hashMap);
        for (String string : treeMap.descendingKeySet()) {
            System.out.println("key : " + string + ", value : " + treeMap.get(string));
        }

        //第六种，使用Map.forEach方法
        System.out.println("----使用Map.forEach方法----");
        hashMap.forEach((k, v) -> System.out.println("key: " + k + ", value: " + v));
    }
}
