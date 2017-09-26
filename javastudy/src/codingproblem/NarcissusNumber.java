package codingproblem;

import java.util.Scanner;

/**
 * Created by sun on 2017/3/19.
 */
public class NarcissusNumber {
    public static void printNumber(int m, int n){
        boolean flag = false;
        int j = 0;
        for (int i = m; i <= n; i++) {
            int unit = i % 10;
            int ten = (i % 100) / 10;
            int hundred = i / 100;
            if (Math.pow(unit, 3) + Math.pow(ten, 3) + Math.pow(hundred, 3) == i){
                flag = true;
                j++;
                if (j > 1){
                    System.out.print(" " + i);
                } else {
                    System.out.print(i);
                }
            }
        }
        if (flag == false){
            System.out.println("no");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = 0;
        int n = 0;
        while (scanner.hasNextInt()){
            m = scanner.nextInt();
            n = scanner.nextInt();
            printNumber(m, n);
        }
    }
}
