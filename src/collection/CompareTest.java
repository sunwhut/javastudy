package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by sun on 2017/1/5.
 */
public class CompareTest {
    public static void main(String[] args){
        //测试Comparable实现类
        System.out.println("---测试Comparable实现类---");
        ArrayList<StudentCompare> arrayList = new ArrayList<StudentCompare>();
        arrayList.add(new StudentCompare("Sun", 23));
        arrayList.add(new StudentCompare("Wang", 22));
        arrayList.add(new StudentCompare("Li", 24));
        //进行排序
        Collections.sort(arrayList);
        for (StudentCompare studentCompare : arrayList){
            System.out.println(studentCompare.toString());
        }
        //测试Comparable实现类
        System.out.println("---测试Comparator实现类---");
        Collections.sort(arrayList, new AgeAscComparator());
        for (StudentCompare studentCompare : arrayList){
            System.out.println(studentCompare.toString());
        }
    }
}

/**
 *定义StudentCompare类，实现Comparable接口
 */
class StudentCompare implements Comparable<StudentCompare>{
    private String name;
    private int age;

    /**
     * 构造方法
     * @param n  姓名
     * @param a  年龄
     */
    public StudentCompare(String n, int a){
        name = n;
        age = a;
    }

    /**
     * 重写compareTo方法
     * @param o 一个StudentCompare类型的实例对象
     * @return  如果调用compareTo方法的对象比实参对象的name要大，则返回正数;如果相等，返回0;否则，返回-1
     */
    @Override
    public int compareTo(StudentCompare o) {
        return name.compareTo(o.name);
    }

    /**
     * 重写toString方法
     * @return  返回一个包含name和age的字符串
     */
    @Override
    public String toString() {
        return name + " : " + age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

/**
 * 实现Comparator接口，对StudentCompare对象进行排序，实现一个按照年龄进行升序排列的比较器
 */
class AgeAscComparator implements Comparator<StudentCompare>{
    /**
     * 重写compare方法，如果o1的age>o2的age，则返回正数;如果相等，返回0;否则，返回负数
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(StudentCompare o1, StudentCompare o2) {
        return o1.getAge() - o2.getAge();
    }
}
