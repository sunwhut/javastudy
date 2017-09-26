package codingproblem;

/**
 * Created by Administrator on 2017/8/28.
 */
public class GetUglyNumber {
    /**
     * 获取第index个丑数
     * @param index
     * @return
     */
    public static int getUglyNumber(int index){
        if (index <= 0)
            return 0;

        int[] uglyNumbers = new int[index];
        uglyNumbers[0] = 1;
        int next = 1;
        int m2 = 0, m3 = 0, m5 = 0;
        while (next < index){
            uglyNumbers[next] = Math.min(Math.min(uglyNumbers[m2] * 2, uglyNumbers[m3] * 3),
                    uglyNumbers[m5] *5 );

            while (uglyNumbers[m2] * 2 <= uglyNumbers[next])
                m2++;
            while (uglyNumbers[m3] * 3 <= uglyNumbers[next])
                m3++;
            while (uglyNumbers[m5] * 5 <= uglyNumbers[next])
                m5++;

            next++;
        }

        return uglyNumbers[index - 1];
    }

    public static void main(String[] args) {
        System.out.println(getUglyNumber(1500));
    }
}
