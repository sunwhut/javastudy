package iostream;

import java.io.*;

/**
 * Created by sun on 2017/2/10.
 */
public class RandomAccessFileTest {
    public static void main(String[] args){
        RandomAccessFileTest randomAccessFileTest = new RandomAccessFileTest();
        File file = new File("./log/test.txt");
        int pointer = 0;
        String str = "你好世界，晚安世界！";
        randomAccessFileTest.writeFile(file, pointer, str);
        randomAccessFileTest.readFile(file);
    }

    /**
     * 写文件
     * @param file  File对象
     * @param str  任意字符串
     */
    public void writeFile(File file, int pointer, String str){
        try {
            //1.创建流对象
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            //2.使用流对象传输数据
            randomAccessFile.seek(pointer);    //定位文件指针到第pointer个字节之后
            //只能从第pointer个字节之后覆盖数据，不能插入数据，也就是说文件的大小不会发生变化，除非文件指针在文件末尾
            randomAccessFile.write(str.getBytes("UTF-8"));    //常用汉字UTF-8编码占用3个字节，一个汉字GBK编码占用2个字节
            //3.关闭流对象
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
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
        byte[] buffer = new byte[1024];
        int num = 0;
        try {
            //1.创建流对象
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            //2.使用流对象传输数据
//            randomAccessFile.seek(3);    //定位文件指针到第3个字节之后
            while ((num = randomAccessFile.read(buffer)) != -1){
                System.out.println(new String(buffer, "UTF-8"));
            }
            System.out.println("文件指针的当前位置: " + randomAccessFile.getFilePointer());
            //不管文件中保存的数据编码格式是什么,使用RandomAccessFile对象的readLine()方法都会自动将编码变成ISO-8859-1,
            // 所以，输出显示还要再进行一次转码
            randomAccessFile.seek(0);
            String str = randomAccessFile.readLine();
            System.out.println(new String(str.getBytes("ISO-8859-1"), "UTF-8"));
            //3.关闭流对象
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
