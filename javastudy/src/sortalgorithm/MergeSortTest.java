package sortalgorithm;

/**
 * Created by Administrator on 2017/4/21.
 */

/**
 * 归并排序算法
 */
class MergeSort{
    /**
     * 归并排序
     * 先将待排序序列分解为若干个子序列，再将子序列合并为整体有序序列
     * @param num 待排序数组
     * @param low 开始下标
     * @param high 结束下标
     * @return 输出有序数组
     */
    public void sort(int[] num, int low, int high){
        int mid = (low + high) / 2;
        if (low < high){
            //分解为左子序列
            sort(num, low, mid);
            //分解为右子序列
            sort(num, mid + 1, high);
            //将左子序列和右子序列合并
            merge(num, low, mid, high);
        }
    }

    /**
     * 将左子序列和右子序列进行合并排序
     * @param num 待排序数组
     * @param low 开始下标
     * @param mid 中间位置
     * @param high 结束下标
     */
    public void merge(int[] num, int low, int mid, int high){
        int i = low;  //左指针
        int j = mid + 1;  //右指针
        int k = 0;  //临时数组的下标
        int[] tmp = new int[high - low + 1];  //临时数组

        // 扫描左子序列和右子序列，直到有一个扫描结束
        while (i <= mid && j <= high){
            // 判断左子序列和右子序列取出的数哪个更小，将较小的数存入临时数组
            if (num[i] <= num[j]){
                tmp[k] = num[i];
                i++;
                k++;
            }else {
                tmp[k] = num[j];
                j++;
                k++;
            }
        }

        //如果左子序列还没扫描完，将其全部复制到临时数组
        while (i <= mid){
            tmp[k++] = num[i++];
        }

        //如果右子序列还没扫描完，将其全部复制到临时数组
        while (j <= high){
            tmp[k++] = num[j++];
        }

        //将临时数组复制到原始数组中
        for (int l = 0; l < tmp.length; l++) {
            num[low + l] = tmp[l];
        }
    }
}

public class MergeSortTest {
    public static void main(String[] args) {
        int[] num = {3, 1, 5, 2 ,4, 6, 8, 7, 9};
        new MergeSort().sort(num, 0, num.length - 1);
        for (int i : num) {
            System.out.print(i + ", ");
        }
    }
}
