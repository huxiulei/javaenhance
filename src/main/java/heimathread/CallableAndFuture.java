package heimathread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 多线程的实现有两种方式,继承自Thread,实现Runnable结果  但是两者都有缺点: :run方法没有返回值  不能抛出异常(归根结底还是Runnable结果的问题,Thread也是
 * 实现了Runnable结果.. 所以需要一个线程的执行结果  就需要用到如下的Callable和Future)
 * 好处是:  第一:尽可能多的占用系统资源,提供快速运算    第二:可以监控线程的执行情况,如是否执行完成,是否有异常等
 *         第三:提供更好的支持提示,如例子中给出的进度提示   ####
 * @author hu.xl
 */
public class CallableAndFuture {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService threadPool =  Executors.newSingleThreadExecutor();
        Future<String> future =
                threadPool.submit(
                        new Callable<String>() {
                            public String call() throws Exception {
                                Thread.sleep(2000);
                                return "hello";
                            };
                        }
                );
        System.out.println("等待结果");
        try {
            // 此处可以判断线程是否执行完成, 对应给出提示信息
            while(!future.isDone()){
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.print("#");
            }
            System.out.println("拿到结果：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        ExecutorService threadPool2 =  Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
        for(int i=1;i<=10;i++){
            final int seq = i;   // 解决下面return seq;的问题   如果直接使用return i; 会提示:Cannot refer to a non-final variable i inside an inner class defined in a different method
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }
        for(int i=0;i<10;i++){
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
