//package networking;

import java.io.IOException;
import java.net.*;

/**
 * Created by sun on 2017/2/15.
 */
public class UDPClient {
    public static void main(String[] args){
        try {
            //1.create DatagramSocket object
            DatagramSocket client = new DatagramSocket();
            //2.communicate
            //send message
            byte[] mess = "Hello, I'm client!".getBytes();
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            DatagramPacket datagramPacket = new DatagramPacket(mess, mess.length, inetAddress, 8080);
            client.send(datagramPacket);
            System.out.println("sending Hello, I'm client!");

            //receive message
            byte[] buffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            client.receive(receivePacket);
            String str = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("received " + str);

            //3.close
            client.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
