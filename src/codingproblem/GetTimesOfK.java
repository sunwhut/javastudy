package codingproblem;

/**
 * Created by Administrator on 2017/9/1.
 */
public class GetTimesOfK {
    /**
     * k第一次出现的位置
     *
     * @param num
     * @param low
     * @param high
     * @param k
     * @return
     */
    public static int getFirstK(int[] num, int low, int high, int k){
        if (low > high) return -1;

        int mid = (low + high) / 2;
        if (k == num[mid]){
            if ((mid > 0 && num[mid - 1] != k) || mid == 0){
                return mid;
            } else {
                high = mid - 1;
            }
        } else if (k < num[mid]){
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return getFirstK(num, low, high, k);
    }

    /**
     * k最后一次出现的位置
     *
     * @param num
     * @param low
     * @param high
     * @param k
     * @return
     */
    public static int getLastK(int[] num, int low, int high, int k){
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (k == num[mid]){
            if ((mid < num.length - 1 && num[mid + 1] != k) || mid == num.length - 1){
                return mid;
            } else {
                low = mid + 1;
            }
        } else if (k < num[mid]){
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return getLastK(num, low, high, k);
    }

    /**
     * k在数组中出现的次数
     *
     * @param num
     * @param k
     * @return
     */
    public static int getTimesOfK(int[] num, int k){
        if (num.length < 1) return -1;

        int first = getFirstK(num, 0 ,num.length - 1, k);
        int last = getLastK(num, 0 ,num.length - 1, k);
        if (first < 0 || last < 0) return -1;
        return last - first + 1;
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 3, 3, 3, 4, 5};
        int k = 3;
        System.out.println(getTimesOfK(num, k));
    }
}
