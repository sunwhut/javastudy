package iostream;

import java.io.*;

/**
 * Created by sun on 2017/2/9.
 */
public class ReaderTest {
    public static void main(String[] args){
        ReaderTest readerTest = new ReaderTest();
        String str = "我是字符数组输入流";
        readerTest.testCharArrayReader(str);
        readerTest.testStringReader(str);
        readerTest.testFileReader(new File("./log/test.txt"));
    }

    /**
     * 测试字符数组输入流
     * @param str  任意字符串
     */
    public void testCharArrayReader(String str){
        char[] chars = str.toCharArray();
        char[] buffer = new char[1024];
        //1.创建字符数组输入流对象
        CharArrayReader charArrayReader = new CharArrayReader(chars);
        try {
            //2.使用流对象传输数据
            charArrayReader.read(buffer, 0, chars.length);
            //3.关闭流对象
            charArrayReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(buffer));
    }

    /**
     * 测试字符串输入流
     * @param str  任意字符串
     */
    public void testStringReader(String str){
        char[] buffer = new char[1024];
        //1.创建字符串输入流对象
        StringReader stringReader = new StringReader(str);
        try {
            //2.使用流对象传输数据
            stringReader.read(buffer, 0, str.length());
            //3.关闭流对象
            stringReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(buffer));
    }

    /**
     * 测试字符文件输入流
     * @param file  File对象
     */
    public void testFileReader(File file){
        try {
            //1.创建输入流对象
            //FileReader不能设置指定字符集进行解码，只能按系统默认字符集进行解码，所以可能会出现乱码
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            //2.使用流对象传输数据
            String str;
            while((str = bufferedReader.readLine()) != null){
                System.out.println(str);
            }
            //3.关闭流对象
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
