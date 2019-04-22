package rpc;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Administrator on 2017/10/7.
 */

/**
 * 服务消费者-客户端的远程代理对象
 * @param <T>
 */
public class RPCClient<T> {
    public static <T> T getRemoteProxyObj(final Class<?> service, final InetSocketAddress address){
        //1.将本地的接口调用转换成JDK动态代理，在动态代理中调用服务提供者提供的服务
        return (T) Proxy.newProxyInstance(service.getClassLoader(),
                new Class<?>[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = null;
                        ObjectOutputStream output = null;
                        ObjectInputStream input = null;
                        try {
                            //2.创建socket，根据指定套接字地址连接远程服务提供者
                            socket = new Socket();
                            socket.connect(address);

                            //3.将调用远程服务所需的接口名、方法名、参数类型、参数值进行序列化后发送给服务提供者
                            output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(service.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(args);

                            //4.同步阻塞等待服务提供方返回结果，获取结果后再返回
                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        } finally {
                            if (input != null) input.close();
                            if (output != null) output.close();
                            if (socket != null) socket.close();
                        }
                    }
                });
    }
}
