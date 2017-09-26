package oo;

/**
 * Created by sun on 2016/12/22.
 */
interface Fruit{
    public abstract void eat();
};

class Apple implements Fruit{
    public void eat(){
        System.out.println("Apple");
    }
}

class Banana implements Fruit{
    public void eat(){
        System.out.println("Banana");
    }
}

class Factory{
    public static Fruit getInstance(String name){
        Fruit fruit = null;
        try {
            fruit = (Fruit) Class.forName(name).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return fruit;
    }
}
public class FactoryTest {
    public static void main(String[] args){
        Fruit fruit = Factory.getInstance("oo.Banana");
        if (fruit != null)
            fruit.eat();
    }
}

