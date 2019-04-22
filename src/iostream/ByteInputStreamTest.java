package iostream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * Created by sun on 2017/2/9.
 */
public class ByteInputStreamTest {
    private static Log log = LogFactory.getLog(ByteInputStreamTest.class);

    public static void main(String[] args){
//        log.info("---测试字节输入流---");
        ByteInputStreamTest byteInputStreamTest = new ByteInputStreamTest();
        String str = "我是字节数组输入流";
        byteInputStreamTest.testByteArrayInputStream(str);
        byteInputStreamTest.testDataInputStream(str);
        byteInputStreamTest.testFileInputStream(new File("./log/logfile.log"));
    }

    /**
     * 测试字节数组输入流
     * @param str  任意字符串
     */
    public void testByteArrayInputStream(String str){
        byte[] bytes = null;
        byte[] buffer = new byte[1024];
        String newStr = "";
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //1.创建输入流对象
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        //2.使用流对象传输数据
        byteArrayInputStream.read(buffer, 0, bytes.length);
        try {
            newStr = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //3.关闭流对象
        try {
            byteArrayInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newStr);
    }

    /**
     * 测试数据输入流
     * @param str  任意字符串
     */
    public void testDataInputStream(String str){
        byte[] bytes = null;
        byte[] buffer = new byte[1024];
        String newStr = "";
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //1.创建输入流对象
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
        //2.使用流对象传输数据
        try {
            dataInputStream.read(buffer, 0, bytes.length);
            newStr = new String(buffer, "UTF-8");
            //3.关闭流对象
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newStr);
    }

    /**
     * 测试字节文件输入流
     * @param file File对象
     */
    public void testFileInputStream(File file){
        byte[] buffer = new byte[1024];
        int num;
        try {
            //1.创建输入流对象
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            //2.使用流对象传输数据
            while((num = bufferedInputStream.read(buffer, 0, 1024)) != -1){
                System.out.print(new String(buffer, 0, num));
            }
            //3.关闭流对象
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
