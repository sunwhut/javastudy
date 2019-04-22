package sortalgorithm;

/**
 * Created by Administrator on 2017/4/19.
 */

/**
 * 插入排序算法
 */
class InsertSort{
    /**
     * 插入排序
     * @param num  int数组
     */
    public void insertSort(int[] num){
        int x;
        int j;
        for (int i = 1; i < num.length; i++) {
            x = num[i];
            for (j = i; j > 0 && x < num[j - 1]; j--) {
                num[j] = num[j - 1];
            }
            num[j] = x;
        }
    }
}

/**
 * 希尔排序算法
 */
class ShellSort{
    /**
     * 希尔排序
     * @param num int数组
     */
    public void shellSort(int[] num){
        int d = 0;
        int x = 0;
        for (d = num.length / 2; d > 0; d /= 2){
            for (int i = d; i < num.length; i++) {
                x = num[i];
                int j = 0;
                for (j = i; j >= d ; j -= d) {
                    if (x < num[j - d]){
                        num[j] = num[j - d];
                    } else {
                        break;
                    }
                }
                num[j] = x;
            }
        }
    }
}

public class InsertSortTest {
    public static void main(String[] args) {
        int[] num = {3, 1, 5, 2 ,4, 6, 8, 7};
        new InsertSort().insertSort(num);
        for (int i : num) {
            System.out.print(i + ", ");
        }
        System.out.println();
        new ShellSort().shellSort(num);
        for (int i : num) {
            System.out.print(i + ", ");
        }
    }
}
