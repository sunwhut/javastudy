package oo;

/**
 * Created by sun on 2016/12/22.
 */

enum Color{
    RED("红色", 1), GREEN("绿色", 2), BLUE("蓝色", 3);
    private String name;
    private int index;

    private Color(String s, int i){
        name = s;
        index = i;
    }

    public String getName(){
        return name;
    }

    public int getIndex(){
        return index;
    }
}
public class EnumTest {
    public static void main(String[] args){
        for (Color color:Color.values()) {
                System.out.println(color + " name : " + color.getName() + " index : " + color.getIndex());
        }
    }
}
