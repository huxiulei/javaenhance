package heimathread;

/**
 * 子线程执行10次  主线程执行100次     如此往复50次
 * 有个设计理念:  同样的业务要放在一个业务类中  这样的话 同步这两个方法就比较方便了 直接用这个业务类即可
 * 就如: web应用中的cookie的加解密  可以放到一个cookie工具类中
 * @author hu.xl
 *
 */
public class TraditionalThreadCommunication {

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

}
class Business {
    private boolean bShouldSub = true;
    public synchronized void sub(int i){
        while(!bShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=1;j<=10;j++){
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = false;
        this.notify();
    }

    public synchronized void main(int i){
        while(bShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=1;j<=100;j++){
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = true;
        this.notify();
    }
}
