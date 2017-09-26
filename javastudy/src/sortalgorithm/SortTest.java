package sortalgorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/8/29.
 */
public class SortTest {
    /**
     * 直接插入排序
     *
     * @param num
     */
    public void insertSort(int[] num) {
        for (int i = 1; i < num.length; i++) {
            int x = num[i];
            int j = 0;
            for (j = i - 1; j >= 0 && num[j] > x; j--) {
                num[j + 1] = num[j];
            }
            num[j + 1] = x;
        }
    }

    /**
     * 希尔排序
     *
     * @param num
     */
    public void shellSort(int[] num) {
        for (int d = num.length / 2; d >= 1; d /= 2) {
            for (int i = d; i < num.length; i++) {
                int x = num[i];
                int j = 0;
                for (j = i - d; j >= 0 && num[j] > x; j -= d) {
                    num[j + d] = num[j];
                }
                num[j + d] = x;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param num
     */
    public void selectSort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            int min = num[i];
            int index = i;
            int j = 0;
            for (j = i + 1; j < num.length; j++) {
                if (num[j] < min) {
                    min = num[j];
                    index = j;
                }
            }
            int temp = num[index];
            num[index] = num[i];
            num[i] = temp;
        }
    }

    /**
     * 选择排序的改进-二元选择排序
     *
     * @param num
     */
    public void selectSortImprove(int[] num) {
        for (int i = 0, j = num.length - 1; i < j; i++, j--) {
            int k = 0;
            int min = i;
            int max = j;
            for (k = i; k <= j; k++) {
                if (num[k] < num[min]) {
                    min = k;
                    continue;
                }
                if (num[k] > num[max])
                    max = k;
            }
            int temp;
            if (min != i) {
                temp = num[min];
                num[min] = num[i];
                num[i] = temp;
                //如果max指向i，则i与min对应的元素互换之后，max应该重新指向min
                if (max == i)
                    max = min;
            }

            if (max != j) {
                temp = num[max];
                num[max] = num[j];
                num[j] = temp;
            }

        }
    }

    /**
     * 堆排序
     *
     * @param num
     */
    public void heapSort(int[] num) {
        for (int i = num.length - 1; i > 0; i--) {
            for (int j = i; j >= 0; j--) {
                adjustHead(num, j, i);
            }
            int temp = num[i];
            num[i] = num[0];
            num[0] = temp;
        }

    }

    /**
     * 调整堆
     *
     * @param num    待排序数组
     * @param parent 根节点下标
     * @param border 边界
     */
    public void adjustHead(int[] num, int parent, int border) {
        int lchild = 2 * parent + 1;
        int rchild = 2 * parent + 2;
        boolean hasChild = (lchild > border) && (rchild > border);
        if (hasChild)
            return;

        int temp;
        int childMax;
        if (rchild > border) {
            childMax = lchild;
        } else {
            childMax = (num[lchild] > num[rchild]) ? lchild : rchild;
        }
        while (num[parent] < num[childMax]) {
            temp = num[parent];
            num[parent] = num[childMax];
            num[childMax] = temp;

            parent = childMax;
            adjustHead(num, parent, border);
        }
    }

    /**
     * 冒泡排序
     *
     * @param num
     */
    public void bubbleSort(int[] num) {
        for (int i = num.length - 1; i > 0; i--) {
            boolean swap = false;
            for (int j = 0; j < i; j++) {
                int temp;
                if (num[j] > num[j + 1]) {
                    temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                    swap = true;
                }
            }

            //如果上一轮没有发生交换操作，则数组已经是有序的，结束循环
            if (!swap)
                break;
        }
    }

    /**
     * 冒泡排序的改进
     *
     * @param num
     */
    public void bubbleSortImprove(int[] num) {
        for (int i = 0; i < num.length / 2; i++) {
            int j, k;
            boolean swap = false;
            for (j = i, k = num.length - 1 - i; j < num.length - 1 - i && k > 0; j++, k--) {
                int temp;
                if (num[j] > num[j + 1]) {
                    temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                    swap = true;
                }
                if (num[k - 1] > num[k]) {
                    temp = num[k - 1];
                    num[k - 1] = num[k];
                    num[k] = temp;
                    swap = true;
                }
            }

            if (!swap) {
                break;
            }
        }
    }

    /**
     * 快排
     * @param num
     * @param start
     * @param end
     */
    public void quickSort(int[] num, int start, int end) {
        if (start >= end)
            return;

        int x = num[start];
        int i = start, j = end;
        while (i < j) {
            while (i < j && num[j] >= x)
                j--;
            if (i < j){
                num[i] = num[j];
                i++;
            }

            while (i < j && num[i] < x)
                i++;
            if (i < j){
                num[j] = num[i];
                j--;
            }
        }

        num[i] = x;
        quickSort(num, start, i - 1);
        quickSort(num, i + 1, end);
    }

    /**
     * 归并排序
     * 先将待排序数组分解为若干个子序列，再合并为整体有序序列
     *
     * @param num
     * @param low
     * @param high
     */
    public void mergeSort(int[] num, int low, int high){
        if (low < high){
            int mid = (low + high) / 2;
            mergeSort(num, low, mid);
            mergeSort(num, mid + 1, high);
            merge(num, low, mid, high);
        }
    }

    /**
     * 将左子序列和右子序列合并
     *
     * @param num
     * @param low
     * @param mid
     * @param high
     */
    public void merge(int[] num, int low, int mid, int high) {
        int[] data = new int[high - low + 1];
        int index = 0;
        int i, j;
        for (i = low, j = mid + 1; i <= mid && j <= high;) {
            if (num[i] <= num[j])
                data[index++] = num[i++];
            else
                data[index++] = num[j++];
        }
        while (i <= mid)
            data[index++] = num[i++];
        while (j <= high)
            data[index++] = num[j++];
        for (int k = 0; k < data.length; k++) {
            num[low + k] = data[k];
        }
    }

    /**
     * 计数排序
     * @param num
     */
    public void countSort(int[] num){
        int max = num[0];  //num中的最大数
        int[] data = new int[num.length];
        for (int i = 1; i < num.length; i++) {
            max = (num[i] > max) ? num[i] : max;
        }
        int[] count = new int[max + 1];

        for (int i = 0; i < num.length; i++) {
            count[num[i]]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int value;
        for (int i = num.length - 1; i >= 0; i--) {
            value = num[i];
            data[--count[value]] = value;
        }

        System.arraycopy(data, 0, num, 0, data.length);
    }

    /**
     * 基于桶排序的基数排序
     *
     * @param num  待排序数组
     * @param radix  基数
     * @param d  最大位数
     */
    public void radixSort(int[] num, int radix, int d){
        LinkedList[] list = new LinkedList[radix];
        for (int i = 0; i < list.length; i++) {
            list[i] = new LinkedList();
        }

        int k = 1;
        int rate = 1;
        int[] tmp = new int[num.length];
        while (k <= d){
            //分配的过程
            for (int i = 0; i < num.length; i++) {
                int digit = (num[i] / rate) % radix;
                list[digit].add(num[i]);
            }

            //收集的过程
            int index = 0;
            for (LinkedList linkedlist : list) {
                for (Object element : linkedlist) {
                    tmp[index] = (int) element;
                    index++;
                }
                linkedlist.clear();
            }
            System.arraycopy(tmp, 0, num, 0, tmp.length);
            rate *= radix;
            k++;
        }
    }

    /**
     * 获取第K大的数
     *
     * @param num
     * @param low
     * @param high
     * @param k
     * @return
     */
    public int getKthLargeNumber(int[] num, int low, int high, int k){
        if (low >= high)
            return num[low];
        int mid = partion(num, low, high);
        while (mid != num.length - k){
            if (mid < num.length - k){
                mid = partion(num, mid + 1, high);
            } else if (mid > num.length - k){
                mid = partion(num, low, mid - 1);
            }
        }
        return num[mid];

    }

    /**
     * 将数组划分为左子数组和右子数组
     *
     * @param num
     * @param low
     * @param high
     * @return
     */
    public int partion(int[] num, int low, int high){
        if (low >= high)
            return low;
        int i = low, j = high;
        int x = num[i];
        while (i < j){
            while (i < j && num[j] >= x)
                j--;
            if (i < j){
                num[i] = num[j];
                i++;
            }
            while (i < j && num[i] < x)
                i++;
            if (i < j) {
                num[j] = num[i];
                j--;
            }
        }
        num[i] = x;
        return i;
    }

    /**
     * 获取第K大的数
     *
     * @param num
     * @param k
     * @return
     */
    public int findKthLargeNumber(int[] num, int k){
        PriorityQueue heap = new PriorityQueue();
        for (int i : num) {
            if (heap.size() < k){
                heap.offer(i);
            } else {
                if (i > (int) heap.peek()){
                    heap.poll();
                    heap.offer(i);
                }
            }
        }
        return (int)heap.peek();
    }



    public static void main(String[] args) {
//        int[] num = {1, 3, 4, 5, 2};
        int[] num = {135, 242, 192, 93, 345, 11, 24, 19};
        SortTest sortTest = new SortTest();
//        sortTest.insertSort(num);
//        sortTest.shellSort(num);
//        sortTest.selectSort(num);
//        sortTest.selectSortImprove(num);
//        sortTest.heapSort(num);
//        sortTest.bubbleSort(num);
//        sortTest.bubbleSortImprove(num);
//        sortTest.quickSort(num, 0, num.length - 1);
//        sortTest.mergeSort(num, 0, num.length - 1);
//        sortTest.countSort(num);
//        sortTest.radixSort(num, 10 , 3);
//        System.out.println(sortTest.getKthLargeNumber(num, 0, num.length - 1, 3));
        System.out.println(sortTest.findKthLargeNumber(num, 2));
        for (int i : num) {
            System.out.print(i + ", ");
        }

    }
}
