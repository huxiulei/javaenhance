package heimathread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService threadPool1 = Executors.newFixedThreadPool(3); // LinkedBlockingQueue
        ExecutorService threadPool2 = Executors.newCachedThreadPool(); // 使用的SynchronousQueue队列 即每次新加入一个元素 即可唤醒一个线程
        ExecutorService threadPool3 = Executors.newSingleThreadExecutor(); // LinkedBlockingQueue
        for(int i=1;i<=10;i++){
            final int task = i;
            threadPool3.execute(new Runnable(){
                @Override
                public void run() {
                    for(int j=1;j<=10;j++){
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
                    }
                }
            });
        }
        System.out.println("all of 10 tasks have committed! ");
        //threadPool.shutdownNow();

        // 通过线程池实现的固定频率的爆炸
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
                new Runnable(){
                    @Override
                    public void run() {
                        System.out.println("bombing!");

                    }},
                6,
                2,
                TimeUnit.SECONDS);
    }

}
