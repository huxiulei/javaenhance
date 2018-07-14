package heimathread;

public class TraditionalThreadSynchronized {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    private void init(){
        final Outputer outputer = new Outputer();
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("zhangxiaoxiang");
                }

            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output2("lihuoming");
                }

            }
        }).start();

    }

    static class Outputer{

        public void output(String name){
            int len = name.length();
            synchronized (Outputer.class)  // 类的字节码 只有唯一一份 所以万能
            {
                for(int i=0;i<len;i++){
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }

        public synchronized void output2(String name){    // 该同步是在调用此方法的类上  也就是Outputer
            int len = name.length();
            for(int i=0;i<len;i++){
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        public static synchronized void output3(String name){    // 静态方法  是使用其对象为锁的 即Outputer
            int len = name.length();
            for(int i=0;i<len;i++){
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}
