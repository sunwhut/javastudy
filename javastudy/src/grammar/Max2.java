package grammar;

import java.util.Arrays;

/**
 * @Author: xiaohuan
 * @Date: 2020/2/18 21:39
 */
public class Max2 {
    public static void main(String[] args){
        int[] arr = {1,6,13,5,2,9};
        System.out.println(Arrays.stream(arr).toString());
        Point x1=new Point(0),x2=new Point(0);
        max2(arr, 0, arr.length-1, x1,x2);
        System.out.println(x1.value+","+x2.value);
    }

    public static void max2(int[] arr, int low, int high, Point x1, Point x2){
        // 刚好数组 [low, high]只有这样的两个元素的情况下
        if(high-low == 1){
            if(arr[low] < arr[high]){
                x1.value = arr[high];
                x2.value = arr[low];
            }else {
                x1.value = arr[low];
                x2.value = arr[high];
            }
            return;
        }
        // 刚好数组 [low, high]只有三个元素的情况下
        if(high - low ==2){
            if(arr[low] < arr[low+1] && arr[low] < arr[low+2]){
                if(arr[low+1]<arr[low+2]){
                    x1.value = arr[low+2];
                    x2.value = arr[low+1];
                }else{
                    x1.value = arr[low+1];
                    x2.value = arr[low+2];
                }
            }else if(arr[low+1] < arr[low] && arr[low+1] < arr[low+2]){
                if(arr[low]<arr[low+2]){
                    x1.value = arr[low+2];
                    x2.value = arr[low];
                }else{
                    x1.value = arr[low];
                    x2.value = arr[low+2];
                }
            }else {
                if(arr[low]<arr[low+1]){
                    x1.value = arr[low+1];
                    x2.value = arr[low];
                }else{
                    x1.value = arr[low];
                    x2.value = arr[low+1];
                }
            }
            return;
        }
        int middle = (high + low)/2;
        Point x1L=new Point(0), x1R=new Point(0), x2L=new Point(0), x2R=new Point(0);
        max2(arr, low, middle, x1L, x1R);
        max2(arr, middle+1, high, x2L, x2R);
        if(x1L.value > x2L.value){
            x1.value = x1L.value;
            if(x1R.value > x2L.value){
                x2.value = x1R.value;
            }else {
                x2.value = x2L.value;
            }
        }else {
            x1.value = x2L.value;
            if(x2R.value > x1L.value){
                x2.value = x2R.value;
            }else {
                x2.value = x1L.value;
            }
        }

    }

    static class Point{
        int value;
        Point(int a){
            this.value =a;
        }
    }
}
