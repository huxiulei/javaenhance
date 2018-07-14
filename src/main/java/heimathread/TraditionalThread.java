package heimathread;
/**
 * 实现线程的两种方式
 * 一: 继承Thread
 * 二: 实现Runnable接口
 * @author hu.xl
 *
 */
public class TraditionalThread {
    /**
     * @param args
     */
    public static void main(String[] args) {

        Thread thread = new Thread("me"){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1:" + Thread.currentThread().getName());
                    System.out.println("2:" + this.getName()); // 此处的this正好指的是当前的这个thread
                }
            }
        };
        thread.start();


        Thread thread2 = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1:" + Thread.currentThread().getName());
                    // 此处就不能使用this.getName()了. 因为this在此处代表Runnable 它不是个线程 是线程要执行的代码
                }
            }
        });
        thread2.setName("you");
        thread2.start();


        new Thread(
                new Runnable(){
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("runnable :" + Thread.currentThread().getName());
                        }
                    }
                }
        ){
            // 此处我们覆写了父类的run方法  所以直接输出我们的方法  如果没有覆写的话 会去找Runnable 然后执行其run方法
            public void run() {
                while(true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread :" + Thread.currentThread().getName());
                }
            }
        }.start();
    }
}
