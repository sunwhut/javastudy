package datastructure;

/**
 * Created by sun on 2017/2/28.
 */

import java.util.Arrays;
import java.util.Locale;

/**
 * 自己用数组实现的ArrayList
 * @param <E>
 */
class ArrayList <E> {
    private int capaticy;    //容量
    private int size;  //大小，即实际存储的元素数
    private Object[] elementData;  //存储元素的数组

    public int getCapaticy() {
        return capaticy;
    }

    /**
     * 默认构造方法，构造一个初始容量为10的空列表
     */
    public ArrayList(){
        this(10);
    }

    /**
     * 构造指定初始容量的空列表
     * @param initialCapacity  指定初始容量
     */
    public ArrayList(int initialCapacity){
        if (initialCapacity < 0 ){
            throw new RuntimeException("数组容量错误: " + initialCapacity);
        }else {
            capaticy = initialCapacity;
            elementData = new Object[capaticy];
            size = 0;
        }
    }

    /**
     * 确认列表当前容量大于指定的最小容量，如果满足，则不执行操作，如果不满足，则扩充容量
     * @param minCapacity
     */
    public void ensureCapacity(int minCapacity){
        if (capaticy < minCapacity){
            //不断地将capacity*2,直到capacity大于等于minCapacity为止
            while (capaticy < minCapacity){
                capaticy = capaticy << 1;
            }
            //创建一个扩容的数组，将当前数组的元素复制到新数组中，并将新数组赋给当前数组
            /*Object[] newData = new Object[capaticy];
            for (int i = 0; i < size; i++) {
                newData[i] = elementData[i];
            }
            elementData = newData;*/
            elementData = Arrays.copyOf(elementData, capaticy);
        }
    }

    /**
     * 在列表的尾部添加指定元素
     * @param e  元素
     */
    public void add(E e){
        //添加前确认列表容量
        ensureCapacity(size + 1);
        elementData[size] = e;
        size++;
    }

    /**
     * 在列表的指定位置插入指定元素
     * @param index  指定位置
     * @param e  指定元素
     */
    public void add(int index, E e){
        validate(index);
        //添加前确认列表容量
        ensureCapacity(size + 1);
        //将index开始所有元素,从后往前后移
        /*for (int i = size -1 ; i >= index; i--) {
            elementData[i+1] = elementData[i];
        }*/
        System.arraycopy(elementData, index, elementData, index+1, size - index);
        elementData[index] = e;
        size++;
    }

    /**
     * 删除列表指定位置上的元素
     * @param index  指定位置
     * @return 被删除的元素
     */
    public E remove(int index){
        validate(index);
        E oldValue = (E) elementData[index];
        //将index以后所有元素，从前往后前移
        /*for (int i = index + 1; i < size; i++) {
            elementData[i-1] = elementData[i];
        }*/
        System.arraycopy(elementData, index+1, elementData, index, size - index - 1);
        size--;
        //最后一个元素清空
        elementData[size] = null;
        return oldValue;
    }

    /**
     * 在列表的指定位置处设置指定元素
     * @param index  指定位置
     * @param e  指定元素
     */
    public void set(int index, E e){
        validate(index);
        elementData[index] = e;
    }

    /**
     * 获取列表指定位置的元素
     * @param index  指定位置
     * @return  指定位置的元素
     */
    public E get(int index){
        validate(index);
        return (E) elementData[index];
    }

    /**
     * 获取指定元素在列表中第一次出现的索引，如果列表不包含指定元素，返回-1
     * @param e  指定元素
     * @return  指定元素在列表中第一次出现的索引
     */
    public int indexOf(E e){
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (e.equals(elementData[i])){
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 判断列表是否包含指定元素，如果有，返回true；否则，返回false
     * @param e  指定元素
     * @return  如果列表包含指定元素，返回true；否则，返回false
     */
    public boolean contains(E e){
        boolean value = false;
        for (int i = 0; i < size; i++) {
            if (e.equals(elementData[i])){
                value = true;
                break;
            }
        }
        return value;
    }

    /**
     * 获取列表中元素的个数
     * @return  列表中元素的个数
     */
    public int size(){
        return size;
    }

    /**
     * 验证当前索引是否合法，如果不合法，抛出运行时异常
     * @param index  当前索引
     */
    public void validate(int index){
        if (index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException("数组索引越界: " + index);
        }
    }
}
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList myArrayList = new ArrayList(5);
        System.out.println("当前容量: " + myArrayList.getCapaticy());
        for (int i = 0; i < 5; i++) {
            myArrayList.add(i+1);
        }
//        myArrayList.add(2, 6);
//        myArrayList.remove(2);
//        myArrayList.set(2, 6);
//        System.out.println(myArrayList.indexOf(3));
        System.out.println("当前容量: " + myArrayList.getCapaticy());
        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i));
        }

        //测试自己实现的ArrayList类添加100000条数据所需时间
        myArrayList = new ArrayList();
        Long myBeginTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            myArrayList.add(i);
        }
        Long myEndTime = System.currentTimeMillis();
        System.out.println("自己实现的ArrayList用时: " + (myEndTime - myBeginTime) + "ms");

        //测试JDK提供的ArrayList类添加100000条数据所需时间
        java.util.ArrayList arrayList = new java.util.ArrayList();
        Long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(i);
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("JDK提供的ArrayList用时: " + (endTime - beginTime) + "ms");
    }
}
