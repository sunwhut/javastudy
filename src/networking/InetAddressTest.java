package networking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by sun on 2017/2/14.
 */
public class InetAddressTest {
    private static Log log = LogFactory.getLog(InetAddressTest.class);

    public static void main(String[] args){
        log.info("---测试InetAddress类---");
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName("www.baidu.com");  //根据域名获取InetAddress对象
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(inetAddress);
        System.out.println("IP地址: " + inetAddress.getHostAddress());  //获取IP地址
        System.out.println("域名: " + inetAddress.getHostName());  //获取域名
        try {
            InetAddress[] inetAddresses = InetAddress.getAllByName("www.baidu.com");  //根据域名获取所有可能的InetAddress对象
            for (InetAddress inet : inetAddresses) {
                System.out.println(inet);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        InetAddress local = null;
        try {
             local = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(local);
        System.out.println(local.getHostAddress());
        System.out.println(local.getHostName());
    }
}
