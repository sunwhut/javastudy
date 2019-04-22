package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/10/7.
 */

/**
 * 服务中心实现类
 */
public class ServiceCenterImpl implements ServiceCenter{
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            1000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(Integer.MAX_VALUE)
    );

    private static HashMap<String, Class> serviceRegister = new HashMap<String, Class>();

    private static boolean isRunning;

    private static int port;

    public ServiceCenterImpl(int port){
        this.port = port;
        isRunning = true;
    }

    /**
     * 开启服务中心
     * @throws IOException
     */
    public void start() throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        System.out.println("start server...");
        try{
            while (isRunning){
                //1.监听客户端的TCP连接，获取TCP连接后将其封装成task，交给线程池执行
                threadPoolExecutor.execute(new ServiceTask(server.accept()));
            }
        } finally {
            server.close();
        }
    }

    /**
     * 关闭服务中心
     */
    public void stop(){
        isRunning = false;
        threadPoolExecutor.shutdown();
    }

    /**
     * 注册服务
     * @param service
     * @param serviceImpl
     */
    public void register(Class service, Class serviceImpl){
        serviceRegister.put(service.getName(), serviceImpl);
    }

    public boolean isRunning(){
        return isRunning;
    }

    public int getPort(){
        return port;
    }

    private static class ServiceTask implements Runnable{
        Socket client = null;

        public ServiceTask(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;

            try{
                //2.将客户端发送的二进制流反序列化成对象，通过反射调用服务提供者提供的服务，获取执行结果
                input = new ObjectInputStream(client.getInputStream());
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Class serviceClass = serviceRegister.get(serviceName);
                if (serviceClass == null){
                    throw new ClassNotFoundException(serviceName + "not found");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);

                //3.将执行结果进行序列化，通过socket发送给客户端
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (output != null){
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (input != null){
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (client != null){
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


}
