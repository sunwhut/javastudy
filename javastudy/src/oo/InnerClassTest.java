package oo;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * Created by sun on 2016/12/23.
 */
//定义一个匿名内部类要实现的外部类
class Call{
    private String name = "";

    public Call(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    public void print(){}
}

public class InnerClassTest {
    private static int counter = 0;

    public int getCounter(){
        return counter;
    }

    public int getCounter(String name){
        //定义一个局部内部类
        class LocalClass{
            private  String name = "";

            public LocalClass(String n){
                name = n;
                counter++;
            }

            public String getName(){
                return name;
            }
        }
        LocalClass localClass = new LocalClass(name);
        System.out.print("name = " + name);
        return counter;
    }

    //定义一个成员内部类
    class MemberClass{
        private String name = "";

        public MemberClass(String n){
            name = n;
            counter++;
        }

        public String getName(){
            return name;
        }
    }

    /**
     * 参数c是一个Call类对象
     */
    public void call(Call c){
        counter++;
        c.print();
    }

    //定义一个静态内部类
    public static class StaticClass{
        private String name = "";

        StaticClass(String n){
            name = n;
            counter++;
        }

        public String getName(){
            return name;
        }
    }

    public static void main(String[] args){
        //测试成员内部类
        InnerClassTest innerClassTest = new InnerClassTest();
        InnerClassTest.MemberClass memberClass = innerClassTest.new MemberClass("我是成员内部类");
        System.out.println("name = " + memberClass.getName() + " counter = " + innerClassTest.getCounter());
        //测试局部内部类
        System.out.println(" counter = " + innerClassTest.getCounter("我是局部内部类"));
        //测试匿名内部类
        innerClassTest.call(new Call("我是匿名内部类") {
            @Override
            public void print() {
                System.out.println("name = " + getName() + " counter = " + innerClassTest.getCounter());
            }
        });
        //测试静态内部类
        StaticClass staticClass = new StaticClass("我是静态内部类");
        System.out.println("name = " + staticClass.getName() + " counter = " + innerClassTest.getCounter());
    }
}
