package codingproblem;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/9/28.
 */
public class Coding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        long N = Integer.parseInt(str);
        long value = 0;
        for (int i = 0; i <= N; i++) {
            if (judge(Integer.toBinaryString(i).toCharArray())) value++;
        }
        System.out.println(value);
        System.out.println(Integer.toBinaryString(1534).toCharArray().length);
    }

    public static boolean judge(char[] ch){
        if (ch.length == 1) return true;
        for (int i = 0, j = ch.length - 1; i < j ; i++, j--) {
            if (ch[i] != ch[j]) return false;
        }
        return true;
    }
}
