package codingproblem;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/9/4.
 */
public class MaxCommonSubstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int[][] a = new int[ch1.length][ch2.length];
        int max = 0;
        for (int i = 0; i < ch1.length; i++) {
            for (int j = 0; j < ch2.length; j++) {
                if (ch1[i] == ch2[j]){
                    if (i > 0 && j > 0){
                        a[i][j] = a[i - 1][j - 1] + 1;
                    } else {
                        a[i][j] = 1;
                    }
                    max = (a[i][j] > max) ? a[i][j] : max;
                }
            }
        }
        System.out.println(max);
    }
}
