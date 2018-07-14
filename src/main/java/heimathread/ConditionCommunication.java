package heimathread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 通过Lock和condition来实现的 线程间的通讯
 * 以前使用的是  this.wait()和 this.notify()/this.notifyAll()
 * 采用Condition  则是调用Condition.await() 和 Condition.signal()
 * @author hu.xl
 *
 */
public class ConditionCommunication {

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
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        private boolean bShouldSub = true;
        public  void sub(int i){
            lock.lock();
            try{
                while(!bShouldSub){
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for(int j=1;j<=10;j++){
                    System.out.println("sub thread sequence of " + j + ",loop of " + i);
                }
                bShouldSub = false;
                condition.signal();
            }finally{
                lock.unlock();
            }
        }

        public  void main(int i){
            lock.lock();
            try{
                while(bShouldSub){
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for(int j=1;j<=100;j++){
                    System.out.println("main thread sequence of " + j + ",loop of " + i);
                }
                bShouldSub = true;
                condition.signal();
            }finally{
                lock.unlock();
            }
        }
    }
}
