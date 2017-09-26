//package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * Created by sun on 2017/2/15.
 */
public class MulticastSender {
    private InetAddress inetAddress;
    private int port;

    public MulticastSender(InetAddress inetAddress, int port){
        this.inetAddress = inetAddress;
        this.port = port;
    }

    /**
     * send message
     * @param str
     */
    public void send(String str){
        //the default charset is GBK, and a char occupies one byte in GBK/UTF-8.
        byte[] bytes = str.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, port);
        try {
            MulticastSocket sender = new MulticastSocket();
            sender.joinGroup(inetAddress);
            sender.send(datagramPacket);
            System.out.println("sending: " + str);
            sender.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            InetAddress inetAddress = InetAddress.getByName("224.0.0.1");
            MulticastSender multicastSender = new MulticastSender(inetAddress, 8000);
            multicastSender.send("hello world");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
