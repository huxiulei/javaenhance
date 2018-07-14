package heimathread;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 通过阻塞队列来实现锁机制
 * @author hu.xl
 *
 */
public class BlockingQueueCommunication {

    /**
     * @param args
     */
    public static void main(String[] args) {

        final Business business = new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        for(int i=1;i<=50;i++){
                            business.sub(i);
                        }
                    }
                }
        ).start();

        for(int i=1;i<=50;i++){
            business.main(i);
        }
    }

    static class Business {
        // 此处需要定义阻塞队列的大小为1 才可实现该事例的阻塞
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);

        // 一进入的时候  就会先往queue1中放入数据   {} 会在所有的构造方法前先执行
        {
            // Collections.synchronizedMap(null); // 可以得到一个线程安全的HashMap
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void sub(int i){
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int j=1;j<=10;j++){
                System.out.println("sub thread sequece of " + j + ",loop of " + i);
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public  void main(int i){
            try {
                queue2.put(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            for(int j=1;j<=100;j++){
                System.out.println("main thread sequece of " + j + ",loop of " + i);
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
