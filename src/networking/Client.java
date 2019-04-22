//package networking;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Created by sun on 2017/2/14.
 */
public class Client {
    public static void main(String[] args){
        try {
            //1.connect
            Socket socket = new Socket("127.0.0.1", 8088);
            //2.communicate
            OutputStream outputStream = socket.getOutputStream();
            String fileName = "childs2011.html";
            File file = new File("E:\\client\\" + fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int num;
            int blockNum = (int) Math.ceil(file.length() / 1024.0);  //两个int相除，结果一定是int
            outputStream.write(fileName.getBytes());
            System.out.println("sending fileName: " + fileName);
            outputStream.write(blockNum);
            System.out.println("sending: blockNum: " + blockNum);
            int count = 0;
            while ((num = bufferedInputStream.read(buffer, 0 ,1024)) != -1){
                outputStream.write(buffer, 0, num);
                System.out.println("sending " + count + "th block...");
                count++;
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            Date date = new Date();
            objectOutputStream.writeObject(date);
            System.out.println("sending " + date);
            //3.close Socket object
            objectOutputStream.close();
            bufferedInputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
