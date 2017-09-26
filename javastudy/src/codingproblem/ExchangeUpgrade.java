package codingproblem;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/9/15.
 */
public class ExchangeUpgrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(countWays(n));

    }

    public static int countWays(int n){
        if (n == 0) return 1;

        int k = 1;
        int line = 0;
        while (k <= n){
            k <<= 1;
            line++;
        }
        int[] coins = new int[line];
        for (int i = 0; i < line; i++) {
            coins[i] = (int) Math.pow(2, i);
        }
        int[][] ways = new int[line][n + 1];
        for (int i = 0; i <= n; i++) {
            ways[0][i] = (i <= 2) ? 1 : 0;
        }
        for (int i = 0; i < line; i++) {
            ways[i][0] = 1;
        }
        int sum = 2;
        for (int i = 1; i < line; i++) {
            sum += 2 * coins[i];
            for (int j = 1; j <= n; j++) {
                if (j == sum){
                    ways[i][j] = 1;
                    break;
                }
                if (j >= coins[i]){
                    ways[i][j] = ways[i - 1][j] + ways[i][j - coins[i]];
                } else {
                    ways[i][j] = ways[i - 1][j];
                }
            }
        }
        return ways[line - 1][n];
    }
}
