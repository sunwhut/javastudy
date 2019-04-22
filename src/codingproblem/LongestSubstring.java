package codingproblem;

/**
 * Created by Administrator on 2017/9/16.
 */
public class LongestSubstring {
    public static void main(String[] args) {
        String A = "1AB2345CD";
        String B = "12345EF";
        System.out.println(longestSubstring(A, A.length(), B, B.length()));
    }

    public static int longestSubstring(String A, int n, String B, int m){
        if (n == 0 || m == 0) return 0;
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int max = 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i] == b[j]){
                    if (i == 0 || j == 0){
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    max = (dp[i][j] > max) ? dp[i][j] : max;
                }
            }
        }
        return max;
    }
}
