//package networking;
//we'd comment here, or it will print error message, like cann't find main class.

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by sun on 2017/2/14.
 */
public class Server {
    public static void main(String[] args){
        try {
            //1.create Socket object
            ServerSocket serverSocket = new ServerSocket(8088);
            //2.connect
            Socket server = serverSocket.accept();
            //3.communicate
            InputStream inputStream = server.getInputStream();
            byte[] buffer = new byte[1024];  //default value 1024 time 0
            int num = inputStream.read(buffer);
            String fileName = new String(buffer, 0, num);  //from designed offset start
            System.out.println("receiving filename : " + fileName);
            File file = new File("E:\\server\\" + fileName);
            if (file.exists()){
                //if another progress is reading the same file, exists() returns false
                System.out.println(fileName + " exists!");
            }else {
                file.createNewFile();
                System.out.println("create " + fileName + " success!");
            }
            int blockNum = inputStream.read();
            System.out.println("receiving blockNum: " + blockNum);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            for (int i = 0; i < blockNum; i++) {
                 num = inputStream.read(buffer,0 , 1024);
                 System.out.println("reading " + i + "th block...");
                 bufferedOutputStream.write(buffer, 0 , num);
            }
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Date date = (Date) objectInputStream.readObject();
            System.out.println("receiving " + date);
            //4.close
            objectInputStream.close();
            bufferedOutputStream.close();
            inputStream.close();
            server.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
