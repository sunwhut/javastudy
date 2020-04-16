package nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerV2 {
    public static final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        //绑定端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        System.out.println("NIO启动8080");

        //事件通知机制
        Selector selector = Selector.open();

        while (true){
            //获取新连接
            SocketChannel newSocketConnection = serverSocketChannel.accept();  //【非阻塞模式】 这个方法可以返回null
            if (newSocketConnection == null){
                continue;
            }
            newSocketConnection.configureBlocking(false);  //显式的配置为非阻塞

            newSocketConnection.register(selector, SelectionKey.OP_READ);  //注册一个事件通知机制，READ

            selector.select();
            Set<SelectionKey> eventKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = eventKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey event = iterator.next();
                if (event.isReadable()){  //某一个连接有数据产生了 OP_READ
                    SocketChannel socketConnection = (SocketChannel) event.channel();
                    threadPoolExecutor.submit(() -> {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        try {
                            socketConnection.read(byteBuffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        byteBuffer.flip();  //转换为读模式
                        System.out.println(new String(byteBuffer.array()));

                    });
                }
            }


        }

    }
}
