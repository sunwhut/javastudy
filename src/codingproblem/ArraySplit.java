package codingproblem;

/**
 * Created by Administrator on 2017/9/15.
 *
 * 分割数组问题：http://blog.csdn.net/souldak/article/details/12354325
 */
public class ArraySplit {
    public static void main(String[] args) {
        int[] value = {2, 4, 5, 6, 7};
        System.out.println(arraySplit(value));
    }

    public static int arraySplit(int[] values){
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        int[][] f = new int[values.length][sum / 2 + 1]; //f[i][j]表示前(i+1)个物体装入容量为j的背包的最大重量
        for (int i = 0; i <= sum / 2; i++) {
            f[0][i] = (i >= values[0]) ? values[0] : 0;  //f[0][j]表示第1个物体装入容量为j的背包的最大重量
        }
        for (int i = 0; i < values.length; i++) {
            f[i][0] = 0;  //背包容量为0，能装入物体的最大重量为0
        }
        for (int i = 1; i < values.length; i++) {
            for (int j = 1; j <= sum/2 ; j++) {
                if (j >= values[i]){
                    //背包容量大于当前物体，最大重量为不加入当前物体和加入当前物体时的最大值
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - values[i]] + values[i]);
                } else {
                    //背包容量小于当前物体，不加入当前物体
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        int result = sum - 2 * f[values.length - 1][sum / 2];
        return result;
    }
}
