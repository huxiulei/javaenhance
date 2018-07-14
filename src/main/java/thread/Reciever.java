package thread;

import java.io.PipedReader;

class Reciever extends Thread {
    private PipedReader reader;
    private Sender sender;

    public Reciever(Sender sender) {
        this.sender = sender;
        try {
            reader = new PipedReader(sender.getSender());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 线程函数
    public void run() {
        int i = 0;
        while (true) {
            try {
                int r = reader.read();
                System.out.println("Read From Sender: " + r);
                if (r == -2)
                    break;
            } catch (Exception e) {
                break;
            }
        }
    }
}