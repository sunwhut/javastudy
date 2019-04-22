package iostream;

import java.io.*;

/**
 * Created by sun on 2017/2/9.
 */
public class TransformStreamTest {
    public static void main(String[] args){
        TransformStreamTest transformStreamTest = new TransformStreamTest();
        File file = new File("./log/test.txt");
        transformStreamTest.writeFile(file);
        transformStreamTest.readFile(file);
    }

    /**
     * 写文件
     * @param file  File对象
     */
    public void writeFile(File file){
        try {
            //1.创建文件输出流对象
            //使用转换流OutputStreamWriter把字节输出流转换成字符输出流，并使用指定字符集进行编码
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "GBK"));
            //2.使用流对象传输数据
            bufferedWriter.write("你好世界");
            bufferedWriter.newLine();
            bufferedWriter.write("晚安世界");
            //3.关闭流对象
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读文件
     * @param file  File对象
     */
    public void readFile(File file){
        try {
            //1.创建输入流对象
            //使用转换流InputStreamReader把字节输入流转换成字符输入流，并使用指定字符集进行解码
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "GBK"));
            //2.使用流对象传输数据
            String str;
            while ((str = bufferedReader.readLine()) != null){
                System.out.println(str);
            }
            //3.关闭流对象
            bufferedReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
