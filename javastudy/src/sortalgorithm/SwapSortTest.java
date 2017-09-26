package sortalgorithm;

/**
 * Created by sun on 2017/3/13.
 */

/**
 * 快速排序算法
 */
class QuickSort{
    /**
     * 快速排序
     * @param num  int数组
     * @param start  开始下标
     * @param end  结束下标
     */
    public void quickSort(int[] num, int start, int end){
        if (start < end){
            int i = start;
            int j = end;
            int x = num[i];
            while (i < j){
                while (i < j && num[j] >= x){
                    j--;
                }
                num[i] = num[j];
                while (i < j && num[i] < x){
                    i++;
                }
                num[j] = num[i];
            }
            num[i] = x;
            quickSort(num, start, i -1);
            quickSort(num, i + 1, end);
        }
    }
}

/**
 * 冒泡排序算法
 */
class BubbleSort{
    /**
     * 冒泡排序
     * @param num int数组
     */
    public void bubbleSort(int[] num){
        int tmp = 0;
        int n = num.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swaped = false;

            for (int j = 1; j < n - i; j++) {
                if (num[j] < num[j - 1]){
                    tmp = num[j];
                    num[j] = num[j - 1];
                    num[j - 1] = tmp;

                    //只要发生了交换操作，swaped就设置为true
                    if (!swaped){
                        swaped = true;
                    }
                }
            }

            //如果上一轮没有发生交换操作，则数组已经是有序的，结束循环
            if (!swaped){
                break;
            }
        }
    }

    /**
     * 冒泡排序的改进
     * @param num int数组
     */
    public void bubbleSortImprove(int[] num){
        int low = 0;
        int high = num.length - 1;
        int tmp = 0;
        while (low < high){
            for (int i = low; i < high; i++) {  //正向冒泡，找到最大值
                if (num[i] > num[i + 1]){  //如果前一位大于后一位，交换位置
                    tmp = num[i];
                    num[i] = num[i + 1];
                    num[i + 1] = tmp;
                }
            }
            high--;

            for (int j = high; j > low ; j--) {  //反向冒泡，找到最小值
                if (num[j] < num[j - 1]){  //如果后一位小于前一位，交换位置
                    tmp = num[j];
                    num[j] = num[j - 1];
                    num[j - 1] = tmp;
                }
            }
            low++;
        }
    }

}

public class SwapSortTest {
    public static void main(String[] args) {
        int[] num = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
//        new QuickSort().quickSort(num, 0, num.length - 1);
        for (int i : num) {
            System.out.print(i + ", ");
        }
        System.out.println();
//        new BubbleSort().bubbleSort(num);
        new BubbleSort().bubbleSortImprove(num);
        for (int i : num) {
            System.out.print(i + ", ");
        }
    }
}
