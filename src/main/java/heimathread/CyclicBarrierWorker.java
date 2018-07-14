package heimathread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierWorker {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(2,new Runnable() {
            @Override
            public void run() {
                System.out.println("-已打通-");
            }
        });
        new Thread(new Worker(cb),"工人1"){}.start();
        new Thread(new Worker(cb),"工人2"){}.start();
    }



    static class Worker implements Runnable{
        private CyclicBarrier cb1;

        public Worker(CyclicBarrier _cb) {
            cb1 = _cb;
        }
        @Override
        public void run() {
            try{
                Thread.sleep(new Random().nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " 线程已经到达汇合点!");
                cb1.await();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
