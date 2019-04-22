package iostream;

import java.io.*;
import java.util.Random;

/**
 * Created by sun on 2017/2/9.
 */
public class WriterTest {
    public static void main(String[] args) {
        WriterTest writerTest = new WriterTest();
        String str = "我是字符数组输出流";
//        writerTest.testCharArrayWriter(str);
        writerTest.testFileWriter(new File("./src/multithread/data.txt"));
    }

    /**
     * 测试字符数组输出流
     *
     * @param str 任意字符串
     */
    public void testCharArrayWriter(String str) {
        //1.创建输出流对象
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        try {
            //2.使用流对象传输数据
            charArrayWriter.write(str);
            //3.关闭流对象
            charArrayWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(charArrayWriter.toString());
    }

    /**
     * 测试字符文件输出流
     *
     * @param file File对象
     */
    public void testFileWriter(File file) {
        try {
            //1.创建输出流对象
            //FileWriter使用系统默认的字符集进行编码，不能设置指定字符集进行编码
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            //2.使用流对象传输数据
            for (int i = 1; i <= 10000; i++) {
                int r = new Random().nextInt(10001);
                bufferedWriter.write(String.valueOf(r) + ",");
            }
//            bufferedWriter.write("你好世界");
//            bufferedWriter.newLine();
//            bufferedWriter.write("我是字符文件输出流");
            //3.关闭流对象
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
