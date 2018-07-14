package thread;

import java.io.PipedWriter;
import java.util.Random;

class Sender extends Thread {
    private PipedWriter writer;

    public Sender() {
        writer = new PipedWriter();
    }

    public PipedWriter getSender() {
        return writer;
    }

    // 线程函数
    public void run() {
        int i = 0;
        while (true) {
            try {
                int w = new Random().nextInt(1000);
                System.out.println(" Sender Write: " + w);
                Thread.sleep(w);
                if (i++ < 20)
                    writer.write(w);
                else {
                    writer.write(-2);
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
    }
}