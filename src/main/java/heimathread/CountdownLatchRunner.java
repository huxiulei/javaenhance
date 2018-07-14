package heimathread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/** 该程序执行逻辑
 *  1) 10个线程都开始运行,执行到begin.await后阻塞,等待begin的计数变为0
 2) 主线程调用begin的countDown方法,使begin的计数器为0
 3) 10个线程继续运行
 4) 主线程继续执行下一个语句,end的计数器不为0,主线程等待
 5) 每个线程运行结束时把end的计数器减1,标志着本线程运行完毕
 6) 10个线程全部结束,end计数器为0
 7) 主线程继续执行,打印出平均成绩
 * @author hu.xl
 *
 */
public class CountdownLatchRunner {

    public static void main(String[] args) {
        int count = 10;
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(count);
        ExecutorService pool = Executors.newFixedThreadPool(count);

        List<Future<Integer>> scores = new ArrayList<Future<Integer>>();
        for (int i = 0; i < count; i++) {
            scores.add(pool.submit(new Runner(begin,end)));
        }

        try {
            begin.countDown(); // 让1变为0 开始执行主线程 下达命令
            end.await();       // 等待所有运动员跑完
            int totalScore = 0;
            for(Future<Integer> score:scores){
                totalScore += score.get();
            }
            System.out.println("平均成绩是: " + totalScore/count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Runner implements Callable<Integer>{
        private CountDownLatch begin;  // 开始信号
        private CountDownLatch end;    // 结束信号

        public Runner(CountDownLatch _begin, CountDownLatch _end) {
            begin = _begin;
            end = _end;
        }

        public Integer call() throws Exception{
            int score = new Random().nextInt(10);
            begin.await(); // 运动员开始跑了 主线程等待
            TimeUnit.SECONDS.sleep(score);
            end.countDown();  // 运动员均已跑完
            return score;
        }
    }
}
