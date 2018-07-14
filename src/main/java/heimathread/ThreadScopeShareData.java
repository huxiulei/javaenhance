package heimathread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 多线程的变量共享   每个线程只能得到自己线程产生的变量   不能串取变量
 * 产生数据的时候 将该数据放入到Map中   Map的key为当前线程  值为产生的数据
 * @author hu.xl
 *
 */
public class ThreadScopeShareData {

    private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()
                            + " has put data :" + data);
                    threadData.put(Thread.currentThread(), data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }

    static class B{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }
}
