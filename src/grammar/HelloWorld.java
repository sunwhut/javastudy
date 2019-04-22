package grammar;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by sun on 2016/12/2.
 */
public class HelloWorld {

    public static void main(String[] args) {
        final int NMAX = 7;

        int[][] odds = new int[NMAX][];
        for (int i = 0; i < NMAX; i++){
            odds[i] = new int[i+1];
        }
        for (int i = 0; i < NMAX; i++)
            for(int j = 0; j < odds[i].length; j++){
                odds[i][j] = 1;
                for (int k = 1; k <= j; k++){
                    odds[i][j] = odds[i][j] * (i-k+1) / k;
                }
            }
        for(int[] row:odds){
                for (int num:row)
                    System.out.print(num+"    ");
                System.out.println();
        }
    }
}
