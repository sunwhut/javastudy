package sortalgorithm;

/**
 * Created by Administrator on 2017/4/20.
 */

/**
 * 选择排序算法
 */
class SelectSort {
    /**
     * 选择排序
     *
     * @param num int数组
     */
    public void selectSort(int[] num) {
        int tmp = 0;
        for (int i = 0; i < num.length; i++) {
            int k = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[j] < num[k]) {
                    k = j;
                }
            }
            tmp = num[i];
            num[i] = num[k];
            num[k] = tmp;
        }
    }

    /**
     * 选择排序的改进--二元选择排序
     *
     * @param num int数组
     */
    public void selectSortImprove(int[] num) {
        int tmp = 0;
        int n = num.length;
        for (int i = 0; i < n / 2; i++) {
            //每趟排序都会记录下最大值和最小值
            int min = i;
            int max = i;
            for (int j = i + 1; j < n - i; j++) {
                if (num[j] < num[min]) {
                    min = j;
                    continue;
                }
                if (num[j] > num[max]) {
                    max = j;
                }
            }

            //将最前面一个元素与最小值交换
            if (min != i) {
                tmp = num[i];
                num[i] = num[min];
                num[min] = tmp;

                //如果之前max指向的是最前面一个元素，那么最前面一个元素跟min交换后，max应该指向min
                if (max == i) {
                    max = min;
                }
            }

            //将最后面一个元素与最大值交换
            if (max != n - 1 - i) {
                tmp = num[n - 1 - i];
                num[n - 1 - i] = num[max];
                num[max] = tmp;
            }
        }
    }

}

/**
 * 堆排序算法
 */
class HeapSort {
    /**
     * 堆排序
     *
     * @param list 待排序数组
     */
    public void heapSort(int[] list) {
        // 循环建立初始大顶堆
        for (int i = list.length / 2; i >= 0; i--) {
            adjustHeap(list, i, list.length);
        }

        // 进行n-1次循环，完成排序
        for (int i = list.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换
            int temp = list[i];
            list[i] = list[0];
            list[0] = temp;

            // 筛选调整，得到i个结点的堆
            adjustHeap(list, 0, i);
        }
    }

    /**
     * 调整堆
     *
     * @param array  待排序数组
     * @param parent  根节点下标
     * @param length  待排序序列长度
     */
    public void adjustHeap(int[] array, int parent, int length) {
        int temp = array[parent]; // temp保存当前父节点
        int child = 2 * parent + 1; // 先获得左孩子
        while (child < length) {
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (child + 1 < length && array[child] < array[child + 1]) {
                child++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= array[child])
                break;

            // 把孩子结点的值赋给父结点
            array[parent] = array[child];

            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = child;
            child = 2 * child + 1;
        }

        array[parent] = temp;
    }
}

public class SelectSortTest {
    public static void main(String[] args) {
        int[] num = {3, 1, 5, 2, 4, 6, 8, 7};
        new SelectSort().selectSort(num);
        for (int i : num) {
            System.out.print(i + ", ");
        }
        System.out.println();
        new SelectSort().selectSortImprove(num);
        for (int i : num) {
            System.out.print(i + ", ");
        }
        System.out.println();

        int[] list = {1, 3, 4, 5, 2, 6, 9, 7, 8, 0};
        new HeapSort().heapSort(list);
        for (int i : list) {
            System.out.print(i + ", ");
        }
    }
}
