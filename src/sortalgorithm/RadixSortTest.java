package sortalgorithm;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/4/21.
 */

/**
 * 基数排序算法
 */
class RadixSort {
    /**
     * 基于计数排序的基数排序算法
     * @param data 待排序数组
     * @param radix 基数，代表排序元素的位数
     * @param d 最大位数
     */
    public void radixSort(int[] data, int radix, int d) {
        // 临时数组
        int[] tmp = new int[data.length];
        // buckets用于记录待排序元素出现的次数，用于计数排序
        int[] buckets = new int[radix];

        for (int i = 0, rate = 1; i < d; i++) {

            // 重置count数组，开始统计下一个关键字
            Arrays.fill(buckets, 0);
            // 将data中的元素完全复制到tmp数组中
            System.arraycopy(data, 0, tmp, 0, data.length);

            // 计算每个待排序数据的子关键字
            for (int j = 0; j < data.length; j++) {
                int subKey = (tmp[j] / rate) % radix;
                buckets[subKey]++;
            }

            for (int j = 1; j < radix; j++) {
                buckets[j] = buckets[j] + buckets[j - 1];
            }

            // 按子关键字对指定的数据进行排序,运用了计数排序
            for (int m = data.length - 1; m >= 0; m--) {
                int subKey = (tmp[m] / rate) % radix;
                data[--buckets[subKey]] = tmp[m];
            }
            rate *= radix;
        }
    }
}

public class RadixSortTest {
    public static void main(String[] args) {
        int[] num = {3, 1, 5, 2, 14, 26, 127, 100, 2333};
        new RadixSort().radixSort(num, 10, 4);
        for (int i : num) {
            System.out.print(i + ", ");
        }
    }
}
