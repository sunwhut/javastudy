package codingproblem;

/**
 * Created by Administrator on 2017/9/13.
 *
 * 凑硬币问题:http://blog.csdn.net/wickedvalley/article/details/76358479
 */
public class Exchange {
    public static void main(String[] args) {
        int[] changes = {1, 5, 10 , 25};
        int n = changes.length;
        int t = 6;
//        System.out.println(countWays(changes, n, t));
        System.out.println(countWays2(changes, n, t));
    }

    public static int countWays(int[] changes, int n, int t){
        int[][] ways = new int[n][t + 1];  //ways[i][j]表示前(i+1)种硬币用来表示金额j的方法种数
        //第一行只有changes[0]的整数倍的金额才能有1种方法
        for (int i = 0; i * changes[0] <= t; i++) {
            ways[0][i * changes[0]] = 1;
        }
        //第一列全为1，因为组成0元就只有一种方法
        for (int i = 0; i < n; i++) {
            ways[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= t; j++) {
                if (j >= changes[i]){
                    //加入此种硬币：加入该枚硬币之前的种数 + 加入该枚硬币之后的种数
                    ways[i][j] = ways[i - 1][j] + ways[i][j - changes[i]];
                } else {
                    //不加入此种硬币
                    ways[i][j] = ways[i - 1][j];
                }
            }
        }
        return ways[n - 1][t];

    }

    public static int countWays2(int[] changes, int n, int t){
        int[] ways = new int[t + 1];  //ways[j]为表示金额j的方法种数
        ways[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = changes[i]; j <= t; j++) {
                //加入该枚硬币之前的种数 + 加入该枚硬币之后的种数
                ways[j] = ways[j] + ways[j - changes[i]];
            }
        }
        return ways[t];
    }
}
