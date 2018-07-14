package mytest;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(), 2000);

        while(true){
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
class MyTimerTask extends TimerTask{
    private static int count = 0;
    @Override
    public void run() {
        count = (count+1)%2;
        System.out.println("爆炸~~");
        new Timer().schedule(new MyTimerTask(), 2000+2000*count);
    }
}
