package codingproblem;

/**
 * Created by Administrator on 2017/9/1.
 */
public class GetMaxSumOfSubArray {
    /**
     * 连续子数组的最大和
     *
     * @param num
     * @return
     */
    public static int getMaxSumOfSubArray(int[] num) {
        if (num.length <= 0) return -1;
        int maxSum = 0;
        int subSum = 0;
        for (int i = 0; i < num.length; i++) {
            if (subSum <= 0) {
                subSum = num[i];
            } else {
                subSum += num[i];
            }

            maxSum = (subSum > maxSum) ? subSum : maxSum;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] num = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(getMaxSumOfSubArray(num));
    }
}
