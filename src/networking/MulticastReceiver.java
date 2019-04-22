//package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Created by sun on 2017/2/15.
 */
public class MulticastReceiver {
    private InetAddress inetAddress;
    private int port;

    public MulticastReceiver(InetAddress inetAddress, int port){
        this.inetAddress = inetAddress;
        this.port = port;
    }

    /**
     * receive message
     */
    public void receive(){
        byte[] buffer = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        try {
            MulticastSocket receiver = new MulticastSocket(port);
            receiver.joinGroup(inetAddress);
            receiver.receive(datagramPacket);
            receiver.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
        System.out.println("receiving: " + str);
    }

    public static void main(String[] args){
        try {
            InetAddress inetAddress = InetAddress.getByName("224.0.0.1");
            MulticastReceiver multicastReceiver = new MulticastReceiver(inetAddress, 8000);
            while (true){
                multicastReceiver.receive();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
