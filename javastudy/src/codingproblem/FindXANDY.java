package codingproblem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 * Created by sun on 2017/3/19.
 */
public class FindXANDY {
    public static void main(String[] args) {
        ArrayList<Integer> inputs = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if(line != null && !line.isEmpty()) {
            int res = resolve(line.trim());
            System.out.println(String.valueOf(res));
        }
    }

    public static int resolve(String expr) {
        String[] str = expr.split("\\s+");
        LinkedList<Integer> operand = new LinkedList<Integer>();  //操作数栈
        int result = 0;
        int a;
        int b;
        for (String tmp : str) {
            if ("+".equals(tmp)){  //加法运算
                if (operand.size() < 2){
                    result = -1;
                    throw new RuntimeException();
                } else {
                    a = operand.pop();
                    b = operand.pop();
                    result = a + b;
                    operand.push(result);
                }
            } else if ("*".equals(tmp)){  //乘法运算
                if (operand.size() < 2){
                    result = -1;
                    throw new RuntimeException();
                } else {
                    a = operand.pop();
                    b = operand.pop();
                    result = a * b;
                    operand.push(result);
                }
            } else if ("^".equals(tmp)){  //自增运算
                if (operand.size() < 1){
                    result = -1;
                    throw new RuntimeException();
                } else {
                    a = operand.pop();
                    result = ++a;
                    operand.push(result);
                }
            } else {  //操作数入栈
                if (operand.size() > 16){
                    result = -2;
                    throw new RuntimeException();
                } else {
                    operand.push(Integer.parseInt(tmp));
                }
            }
        }
        return result;
    }

    /**
     * 如果使用最优的最大吞吐量负载均衡算法，按照最优模型多久能够处理完所有请求，单位毫秒。
     * @return
     */
    static long doneTime(int maxQps,String[] rtList,int requestNum,int threadNum) {
        //TODO
        int qpsSum = 0;
        for (String rtString : rtList) {
            int singleMaxQps = threadNum * 1000 / Integer.valueOf(rtString);
            if (singleMaxQps > maxQps) {
                qpsSum += maxQps;
            }else {
                qpsSum += singleMaxQps;
            }
        }

        return requestNum / qpsSum * 1000;
    }
}
