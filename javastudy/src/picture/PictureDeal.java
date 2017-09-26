package picture;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sun on 2016/12/8.
 */

/**
 * 定义一个简单的图片处理类
 */
public class PictureDeal {
    private int picIndex;    //图片索引
    private String picUri;    //图片URI

    public PictureDeal(int picI, String picU){
        picIndex = picI;
        picUri = picU;
    }

    public int getPicIndex() {
        return picIndex;
    }

    public String getPicUri() {
        return picUri;
    }

    /**
     * 下载图片
     * @param i    图片索引
     * @param picUri    图片URI
     */
    public void downloadPicture(int i, String picUri){
        Connection conn = Jsoup
                .connect(picUri)
                .ignoreContentType(true)
                .userAgent(
                        "Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13")
                .cookie("auth", "token");
        try {
            Connection.Response response = conn.execute();
            byte[] data = response.bodyAsBytes();
            File pic = new File("./src/10gPicture/"+i+".jpg");
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(pic));
            out.write(data);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        PictureDeal pic1 = new PictureDeal(1,
                "https://r.jiedaibao10g.com/%E5%80%9F%E8%B4%B7%E5%AE%9D9%E6%9C%8825%E6%97%A5%E6%9B%B4%E6%96%B0/%E7%8E%8B%E5%8D%83%E6%83%A0%EF%BC%88%E6%BC%82%E4%BA%AE%EF%BC%89/Cache_48e47aa04f37f9ac..jpg");
        PictureDeal pic2 = new PictureDeal(2,
                "https://r.jiedaibao10g.com/%E5%80%9F%E8%B4%B7%E5%AE%9D9%E6%9C%8825%E6%97%A5%E6%9B%B4%E6%96%B0/%E7%8E%8B%E5%8D%83%E6%83%A0%EF%BC%88%E6%BC%82%E4%BA%AE%EF%BC%89/Screenshot_2016-09-12-08-29-07-64.png");
        PictureDeal pic3 = new PictureDeal(3,
                "https://r.jiedaibao10g.com/%E5%80%9F%E8%B4%B7%E5%AE%9D9%E6%9C%8825%E6%97%A5%E6%9B%B4%E6%96%B0/%E7%8E%8B%E5%8D%83%E6%83%A0%EF%BC%88%E6%BC%82%E4%BA%AE%EF%BC%89/Cache_5568d82c59b6ee62..jpg");
        PictureDeal pic4 = new PictureDeal(4,
                "https://r.jiedaibao10g.com/%E5%80%9F%E8%B4%B7%E5%AE%9D9%E6%9C%8825%E6%97%A5%E6%9B%B4%E6%96%B0/%E7%8E%8B%E5%8D%83%E6%83%A0%EF%BC%88%E6%BC%82%E4%BA%AE%EF%BC%89/Cache_-2b0fa358bdcf5402..jpg");
        new Thread(new DownloadPictureThread(pic1)).start();
        new Thread(new DownloadPictureThread(pic2)).start();
        new Thread(new DownloadPictureThread(pic3)).start();
        new Thread(new DownloadPictureThread(pic4)).start();
    }
}

/**
 * 定义一个下载图片线程类，实现Runnable接口
 */
class DownloadPictureThread implements Runnable{
    private PictureDeal pictureDeal;

    public DownloadPictureThread(PictureDeal p){
        pictureDeal = p;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pictureDeal.downloadPicture(pictureDeal.getPicIndex(), pictureDeal.getPicUri());
        System.out.println("第" + pictureDeal.getPicIndex() + "幅图片下载完毕！");
    }
}