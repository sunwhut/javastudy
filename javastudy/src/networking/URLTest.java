package networking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sun on 2017/2/14.
 */
public class URLTest {
    private static Log log = LogFactory.getLog(URLTest.class);

    public static void main(String[] args){
        System.out.println("---测试URL类---");
        URL url = null;
        try {
            //1.创建URL
            url = new URL("http://www.baidu.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //2.解析URL
        System.out.println("协议:" + url.getProtocol());
        System.out.println("主机:" + url.getHost());
        System.out.println("文件:" + url.getFile());
        System.out.println("端口:" + url.getPort());
        try {
            //3.从URL读取网络资源
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = "";
            while ((str = bufferedReader.readLine()) != null){
                System.out.println(str);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //4.获取URLConnection对象
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();    //获取从远程对象读取的输入流
            byte[] bytes = new byte[1024];
            int num;
            while ((num = inputStream.read(bytes)) != -1){
                System.out.print(new String(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


