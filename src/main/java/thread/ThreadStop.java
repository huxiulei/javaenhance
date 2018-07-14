package thread;

public class ThreadStop {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我正在执行~~~");
            }
        });
        try {
            t.start();
            t.sleep(1000);
            t.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
