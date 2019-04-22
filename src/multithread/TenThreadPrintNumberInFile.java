package multithread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

/**
 一个文件中有10000个数，用Java实现一个多线程程序将这个10000个数输出到5个不用文件中（不要求输出到每个文件中的数量相同）。
 要求启动10个线程，两两一组，分为5组。每组两个线程分别将文件中的奇数和偶数输出到该组对应的一个文件中，
 需要偶数线程每打印10个偶数以后，就将奇数线程打印10个奇数，如此交替进行。
 同时需要记录输出进度，每完成1000个数就在控制台中打印当前完成数量，并在所有线程结束后，在控制台打印"Done".

  参考：https://www.nowcoder.com/questionTerminal/daebda9742f34fc98650b30d996439cf
 */
public class TenThreadPrintNumberInFile {
    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        String str = "";
        try {
            bufferedReader = new BufferedReader(
                    new FileReader(new File("./src/multithread/data.txt")));
            //读取文件中的数字
            str = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //将一行字符串全部解析为10000个数字
        String[] strs = str.split(",");
        List<String> strList = new ArrayList<String>();
        //存储数据的容器
        int[] records = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            records[i] = Integer.parseInt(strs[i]);
            strList.add(strs[i]);
        }

        Print[] prints = new Print[5];
        //初始化5个Print对象
        for (int i = 0; i < prints.length; i++) {
            prints[i] = new Print(records, 2000 * (i + 1) - 1,
                    new File("./src/multithread/" + (i + 1) +".txt"));
        }
        for (int i = 0; i < prints.length; i++) {
//            new Thread(new OddThread(prints[i])).start();
//            new Thread(new EvenThread(prints[i])).start();
        }

        PrintNumbers printNumbers = new PrintNumbers(strList);
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 2500; j++) {
                        printNumbers.print();
                    }
                }
            }).start();
        }
    }
}

class Print {
    private static int count;  //所有类对象共享的同一个计数器count，记录输出的记录总数
    private int[] records;  //存储数据的容器
    public int border;  //访问容器中数据的边界
    public int oddIndex;  //记录容器中奇数的下标
    public int evenIndex;  //记录容器中偶数的下标
    private int threadID = 2;  //线程通信标识符，值为2时代表evenThreadRun方法可执行，
                              // 值为1时代表oddThreadRun方法可执行
    private File outFile;  //输出文件对象

    public Print(int[] records, int border, File outFile) {
        this.records = records;
        this.border = border;
        this.outFile = outFile;
        this.oddIndex = border - 1999;  //指定访问奇数的开始下标
        this.evenIndex = border - 1999;  //指定访问偶数的开始下标
    }

    /**
     *奇数线程执行的方法
     */
    public synchronized void oddThreadRun() {
        //如果该分组中的偶数没有输出完，则执行"阻塞自己，唤醒对方"的代码；否则直接执行后面输出奇数的代码
        if (evenIndex <= border) {
            while (threadID != 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        BufferedWriter bufferedWriter = null;
        int tmpCount = 0;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(outFile, true));
            int i;
            for (i = oddIndex; i <= border; i++) {
                if (records[i] % 2 == 1) {
                    bufferedWriter.write(String.valueOf(records[i]));
                    bufferedWriter.newLine();
                    //锁定整个类，10个线程共用一个全局变量count，以对其提供原子操作
                    synchronized (Print.class){
                        count++;
                        if (count % 1000 == 0) System.out.println(Thread.currentThread().getName() +
                                " has printed " + count);
                        if (count == 10000) System.out.println("Done!");
                    }
                    tmpCount++;
                    if (tmpCount == 10) break;
                }
            }
            //奇数的起始位置要后移一位
            oddIndex = i + 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //更新线程通信标识符
        threadID = 2;
        //唤醒另一个线程
        notifyAll();
    }

    /**
     * 偶数线程执行的方法
     */
    public synchronized void evenThreadRun() {
        //如果该分组中的奇数没有输出完，则执行"阻塞自己，唤醒对方"的代码；否则直接执行后面输出偶数的代码
        if (oddIndex <= border) {
            while (threadID != 2) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        BufferedWriter bufferedWriter = null;
        int tmpCount = 0;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(outFile, true));
            int i;
            for (i = evenIndex; i <= border; i++) {
                if (records[i] % 2 == 0) {
                    bufferedWriter.write(String.valueOf(records[i]));
                    bufferedWriter.newLine();
                    evenIndex = i;
                    //锁定整个类，10个线程共用一个全局变量count，以对其提供原子操作
                    synchronized (Print.class){
                        count++;
                        if (count % 1000 == 0) System.out.println(Thread.currentThread().getName() +
                                " has printed " + count);
                        if (count == 10000) System.out.println("Done!");
                    }
                    tmpCount++;
                    if (tmpCount == 10) break;
                }
            }
            //偶数的起始位置要后移一位
            evenIndex = i + 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //更新线程通信标识符
        threadID = 1;
        //唤醒另一个线程
        notifyAll();
    }
}

class OddThread implements Runnable {
    Print print;

    public OddThread(Print print) {
        this.print = print;
    }

    @Override
    public void run() {
        //该分组中的奇数还没输出完，则继续执行oddThreadRun方法，否则循环结束，奇数线程执行完毕
        while (print.oddIndex <= print.border) {
            print.oddThreadRun();
        }
    }
}

class EvenThread implements Runnable {
    Print print;

    public EvenThread(Print print) {
        this.print = print;
    }

    @Override
    public void run() {
        //该分组中的偶数还没输出完，则继续执行evenThreadRun方法，否则循环结束，偶数线程执行完毕
        while (print.evenIndex <= print.border) {
            print.evenThreadRun();
        }
    }
}

class PrintNumbers{
    private List<String> strList;
    public int count;

    public PrintNumbers(List<String> strList){
        this.strList = strList;
        this.count = 0;
    }


    public synchronized void print(){
            String str = strList.get(count++);
            System.out.println(Thread.currentThread().getName() + ": " + str);
    }
}