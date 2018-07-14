package heimathread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 连环爆炸
 * 一: 可以自己定义一个炸弹 在爆炸的时候重新new一个自己出来   就是循环炸了    因为要求爆炸的时间间隔不同  所以需要一个变量
 * 二: 定义两个Timer类 互相调用    每个都有自己的爆炸时间间隔
 * @author hu.xl
 *
 */
public class TraditionalTimerTest {

    private static int count = 0;
    public static void main(String[] args) {
/*		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("bombing!");
				
			}
		}, 10000,3000);*/


        class MyTimerTask extends TimerTask{

            @Override
            public void run() {
                count = (count+1)%2;
                System.out.println("bombing!");
                new Timer().schedule(/*new TimerTask() {
					
					@Override
					public void run() {
						System.out.println("bombing!");
					}
				}*/new MyTimerTask(),2000+2000*count);
            }
        }

        new Timer().schedule(new MyTimerTask(), 2000);

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
