package mytest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2014-2-26 下午02:21:10
 * hu.xl
 */
public class CyclicBarrierTour {
    private static int[] time4Walk = {3,6,10};
    private static int[] time4Self = {1,4,8};
    private static int[] time4Bus = {2,5,7};

    // 格式化时间
    static String nowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date()) + ": ";
    }


    static class Tour implements Runnable{
        private int[] time4Use;
        private CyclicBarrier cb;
        private String tourName;

        public Tour(int[] time4Use, CyclicBarrier cb, String tourName) {
            super();
            this.time4Use = time4Use;
            this.cb = cb;
            this.tourName = tourName;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(time4Use[0] * 1000);
                System.out.println(nowTime() + tourName + " 到达了深圳");

                cb.await();  // 等候其他人

                Thread.sleep(time4Use[1] * 1000);
                System.out.println(nowTime() + tourName + " 到达了杭州");

                cb.await();  // 等候其他人

                Thread.sleep(time4Use[2] * 1000);
                System.out.println(nowTime() + tourName + " 到达了北京");

                cb.await();  // 等候其他人
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public static void main(String[] args) {
            Runnable runner = new Runnable() {
                @Override
                public void run() {
                    System.out.println("徒步,自驾游,BUS游 均已到达终点北京");
                }
            };

            CyclicBarrier barrier = new CyclicBarrier(3,runner);
            ExecutorService pool = Executors.newFixedThreadPool(3);
            pool.submit(new Tour(time4Bus,barrier,"BUS游"));
            pool.submit(new Tour(time4Self,barrier,"自驾游"));
            pool.submit(new Tour(time4Walk,barrier,"徒步游"));
            pool.shutdown();
        }
    }
}

