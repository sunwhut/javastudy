package codingproblem;

/**
 * Created by Administrator on 2017/9/16.
 */
public class LongestAscentSequence {
    public static void main(String[] args) {
        int[] A = {2, 1, 4, 3, 1, 5, 6};
        System.out.println(longestAscentSequence(A, A.length));
    }

    public static int longestAscentSequence(int[] A, int n){
        if (n == 0) return 0;
        int[] dp = new int[n];  //dp[i]表示从dp[0]到dp[i]包含的最长递增子序列的长度
        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
}
