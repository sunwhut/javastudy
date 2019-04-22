package javabean;

/**
 * Created by sun on 2017/2/27.
 */
interface MathOperation{
    int operation(int a, int b);
}

public class LambdaTest {
    public static void main(String[] args) {
        MathOperation mathOperation = (int a, int b) -> {return a+b;};
        System.out.println(mathOperation.operation(10, 20));
    }
}
