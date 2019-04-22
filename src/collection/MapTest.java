package collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sun on 2016/12/29.
 */
public class MapTest {
    public static void main(String[] args){
        //测试HashMap用法
        System.out.println("---测试HashMap用法---");
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Sun", 23);
        hashMap.put("Wang", 23);
        hashMap.put("Li", 24);
        hashMap.put("He", 23);
        printMap(hashMap);
        hashMap.remove("Li");
        printMap(hashMap);
        hashMap.put("Sun", 24);
        printMap(hashMap);
        System.out.println(hashMap.get("Sun") + "\n-----");
        System.out.println(hashMap.containsKey("Sun") + "\n-----");
        System.out.println(hashMap.containsValue(23) + "\n-----");
        //判断Map是否为空
        System.out.println("Map是否为空： " + hashMap.isEmpty() + "\n-----");     //推荐使用
        System.out.println("Map是否为空： " + hashMap.size() + "\n-----");
//        hashMap.clear();     //将映射置空
        System.out.println("Map是否为空： " + hashMap.isEmpty() + "\n-----");     //推荐使用
        Set<String> keySet = hashMap.keySet();
        System.out.println("Key的集合： " + keySet + "\n-----");
        Collection<Integer> valueCollection = hashMap.values();
        System.out.println("Value的集合： " + valueCollection + "\n-----");
        //获取Map.Entry的集合，即Map中键值对的集合，其中Map.Entry是Map的内部接口
        Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }

        //测试LinkedHashMap用法
        System.out.println("---测试LinkedHashMap用法---");
        Map<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        linkedHashMap.put("Sun", 23);
        linkedHashMap.put("Chen", 23);
        linkedHashMap.put("Wang", 23);
        linkedHashMap.put("Li", 24);
        printMap(linkedHashMap);
        System.out.println(linkedHashMap.get("Sun") + "\n-----");

        //测试HashTable用法
        System.out.println("---测试HashTable用法---");
        Map<String, Integer> hashTable = new Hashtable<String, Integer>();
        hashTable.put("Sun", 23);
        hashTable.put("Li", 25);
        hashTable.put("Wang", 23);
        hashTable.put("Chen", 24);
        printMap(hashTable);
        System.out.println(hashTable.get("Sun") + "\n-----");

        //测试TreeMap用法
        System.out.println("---测试TreeMap用法---");
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
        treeMap.put("Sun", 25);
        treeMap.put("Chen", 24);
        treeMap.put("Wang", 23);
        treeMap.put("Li", 24);
        printMap(treeMap);
        System.out.println("第一个键： " + treeMap.firstKey() + "\n-----");
        System.out.println("最后一个键： " + treeMap.lastKey() + "\n-----");

        //测试ConcurrentHashMap用法
        System.out.println("---测试ConcurrentHashMap用法---");
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<String, Integer>();
        concurrentHashMap.put("1", 1);
        concurrentHashMap.put("2", 2);
        concurrentHashMap.put("3", 3);
        printMap(concurrentHashMap);


    }

    /**
     *打印<String, Integer>类型Map的实现类的对象
     * @param map <String, Integer>类型Map的实现类的对象
     */
    public static void printMap(Map<String, Integer> map){
        System.out.println(map + "\n------");
    }
}
