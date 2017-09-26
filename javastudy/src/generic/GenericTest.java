package generic;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by sun on 2016/12/27.
 */
public class GenericTest{
    public static void main(String[] args){
        //实例化Point对象pointA
        Point<Integer, Integer> pointA = new Point<Integer, Integer>();
        pointA.setT1(10);
        pointA.setT2(100);
//        System.out.println("点的坐标是： " + pointA.getT1() + " , " + pointA.getT2());
        //测试泛型方法
        pointA.printPoint(pointA.getT1(), pointA.getT2());
        //实例化Point对象pointB
        Point<Integer, String> pointB = new Point<Integer, String >();
        pointB.setT1(100);
        pointB.setT2("北纬22.5度");
//        System.out.println("点的坐标是： " + pointB.getT1() + " , " + pointB.getT2());
        //测试泛型方法
        pointB.printPoint(pointB.getT1(), pointB.getT2());
        //测试泛型接口
        Generic<String> generic = new GenericImp<String>("这是一个泛型接口");
        System.out.println(generic.getData());
        //测试泛型类型上限
        Integer[] array = {1, 2, 3, 6, 10 ,21, 22, 6};
        System.out.println("最大值是： " + getMax(array));
        //测试通配符类型
        Stats<Integer> a = new Stats<Integer>(1);
        Stats<Double> b = new Stats<Double>(1.0);
        b.doSome(a);
    }

    /**
     *获取Number类或其子类数组的最大值
     * 参数：Number类或其子类的数组
     */
    public static <T extends Number> T getMax(T[] array){
        T max = array[0];
        for (T element : array) {
            max = element.doubleValue() > max.doubleValue() ? element : max;
        }
        return max;
    }
}

//定义泛型类
class Point<T1, T2>{
    private T1 t1;
    private T2 t2;

    public void setT1(T1 t1){
        this.t1 = t1;
    }

    public void setT2(T2 t2) {
        this.t2 = t2;
    }

    public T1 getT1() {
        return t1;
    }

    public T2 getT2() {
        return t2;
    }

    //定义泛型方法
    public <V1, V2> void printPoint(V1 t1, V2 t2){
        System.out.println("点的坐标是： " + t1 + " , " + t2);
    }
}

//定义泛型接口
interface Generic<T>{
    public T getData();
}

//实现泛型接口
class GenericImp<T> implements Generic<T>{
    private T data;

    public GenericImp(T d){
        this.setData(d);
    }

    public void setData(T d){
        data = d;
    }

    @Override
    public T getData() {
        return data;
    }
}

//通配类型参数
class Stats <T extends Number>{
    T number;

    public Stats(T n){
        number = n;
    }

    //stats可以是任意Stats泛型类型的对象
    void doSome(Stats <?> stats){
        System.out.println(stats.getClass().getName());
    }
}