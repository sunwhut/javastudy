package rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2017/10/7.
 */

/**
 * 测试类
 */
public class RPCTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServiceCenter serviceCenter = new ServiceCenterImpl(8086);  //创建服务中心
                serviceCenter.register(HelloService.class, HelloServiceImpl.class);  //注册服务
                try {
                    serviceCenter.start();  //开启服务中心
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //客户端获取远程代理对象
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class,
                new InetSocketAddress("localhost", 8086));
        //像调用本地服务一样调用远程服务
        System.out.println(service.sayHello("孙"));
    }
}
