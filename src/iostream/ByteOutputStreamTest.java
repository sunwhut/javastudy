package iostream;

import java.io.*;

/**
 * Created by sun on 2017/2/9.
 */
public class ByteOutputStreamTest {
    public static void main(String[] args){
        String str = "我是字节数组输出流";
        ByteOutputStreamTest byteOutputStreamTest = new ByteOutputStreamTest();
        byteOutputStreamTest.testByteArrayOutputStream(str);
        byteOutputStreamTest.testDataOutputStream(str);
        byteOutputStreamTest.testFileOutputStream(new File("./log/test.txt"));
    }

    /**
     * 测试字节数组输出流
     * @param str  任意字符串
     */
    public void testByteArrayOutputStream(String str){
        byte[] bytes = null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //1.创建输出流对象
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //2.使用流对象传输数据
        byteArrayOutputStream.write(bytes, 0, bytes.length);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        //3.关闭流对象
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String newStr = "";
        try {
             newStr = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(newStr);
    }

    /**
     * 测试数据输出流
     * @param str  任意字符串
     */
    public void testDataOutputStream(String str){
        byte[] bytes = null;
        byte[] buffer = null;
        String newStr = "";
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //1.创建输出流对象
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            //2.使用流对象传输数据
            dataOutputStream.write(bytes, 0, bytes.length);
            buffer = byteArrayOutputStream.toByteArray();
            //3.关闭流对象
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            newStr = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(newStr);
    }

    /**
     * 测试字节文件输出流
     * @param file  File对象
     */
    public void testFileOutputStream(File file){
        try {
            //1.创建文件输出流对象
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream(file));
            //2.使用流对象传输数据
            byte[] bytes = "我是字节文件输出流".getBytes("UTF-8");
            bufferedOutputStream.write(bytes, 0, bytes.length);
            //3.关闭流对象
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
