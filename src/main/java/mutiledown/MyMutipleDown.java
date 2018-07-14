package mutiledown;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 2014-3-10 上午09:21:25
 * hu.xl
 */
public class MyMutipleDown {
    public static final String downFilePath = "http://192.168.6.166:8080/youdao.exe";

    public static void main(String[] args) throws Exception{
        // 1.打开这个链接  获取文件的大小  创建一个大小相同的空文件 RandomAccessFile
        URL url = new URL(downFilePath);
        HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:27.0) Gecko/20100101 Firefox/27.0");

        int result = conn.getResponseCode();
        if(result == 200){
            int len = conn.getContentLength();
            RandomAccessFile raf = new RandomAccessFile("youdao.exe", "rws");
            raf.setLength(len);

            // 2.开启线程
            int threadnum = 3;
            // 每个线程下载的大小
            int blocksize = len / threadnum;
            for(int i=0;i<threadnum;i++){
                int startposition = blocksize * i;
                int endposition = blocksize * (i+1);
                if(threadnum == i){
                    endposition = len;
                }
                MyDownLoadTask downloadTask = new MyDownLoadTask(downFilePath,i,raf,startposition,endposition);
                downloadTask.start();
            }
        }
    }
}

class MyDownLoadTask extends Thread{
    private String path;
    private int threadid;
    private RandomAccessFile file;
    private int startposition;
    private int endposition;
    public MyDownLoadTask(String path, int threadid, RandomAccessFile file,
                          int startposition, int endposition) {
        this.path = path;
        this.threadid = threadid;
        this.file = file;
        this.startposition = startposition;
        this.endposition = endposition;
    }

    @Override
    public void run() {

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.out.println("线程" + threadid + "正在下载 " + "开始位置 : "
                    + startposition + ",结束位置:  " + endposition);

            File positionfile = new File(threadid+".txt");
            if(positionfile.exists()){ // 说明之前中断过
                FileInputStream fis = new FileInputStream(positionfile);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = fis.read(buffer))!=-1){
                    bos.write(buffer, 0, len);
                }
                fis.close();
                bos.flush();
                byte[] result = bos.toByteArray();
                int currentposition = Integer.parseInt(new String(result));
                if(currentposition > startposition){
                    startposition = currentposition;
                }
            }

            conn.setRequestProperty("Range", "bytes=" + startposition + "-" + endposition);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:27.0) Gecko/20100101 Firefox/27.0");
            RandomAccessFile file1 = new RandomAccessFile("youdao.exe","rwd");
            InputStream is = conn.getInputStream();
            file1.seek(startposition);
            int len = 0;
            byte[] buffer = new byte[1024];
            // 代表当前读到的服务器数据的位置 ,同时这个值已经存储的文件的位置
            int currentPostion = startposition;

            while((len = is.read(buffer)) != -1){
                file1.write(buffer, 0, len);

                currentPostion += len;
                // 需要把currentPostion 信息给持久化到存储设备
                String position = currentPostion + "";
                FileOutputStream fos = new FileOutputStream(positionfile);
                fos.write(position.getBytes());
                fos.flush();
                fos.close();
            }
            file1.close();
            System.out.println("线程" + threadid + "下载完毕");
            // 当线程下载完毕后 把文件删除掉
            if (positionfile.exists()) {
                positionfile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}