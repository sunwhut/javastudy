//package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by sun on 2017/2/15.
 */
public class UDPServer {
    public static void main(String[] args){
        try {
            //1.create DatagramSocket object
            DatagramSocket server = new DatagramSocket(8080);
            //2.communicate
            //receive message
            byte[] buffer = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            server.receive(datagramPacket);
            String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println("received " + str);

            //send message
            InetAddress inetAddress = datagramPacket.getAddress();
            int port = datagramPacket.getPort();
            byte[] mess = "Hello, I'm server!".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(mess, mess.length, inetAddress, port);
            server.send(sendPacket);
            System.out.println("sending Hello, I'm server!");

            //3.close
            server.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
