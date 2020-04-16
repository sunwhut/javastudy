package nio;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerV1 {
    public static final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        //绑定端口
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("启动服务8080");
        while (true){
            //获取新连接
            Socket socketConnection = serverSocket.accept();  //阻塞

            threadPoolExecutor.submit(() -> {
                byte[] request = new byte[1024];
                try {
                    socketConnection.getInputStream().read(request);  //阻塞
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(request));
                return "ok";
            });

        }

    }
}
