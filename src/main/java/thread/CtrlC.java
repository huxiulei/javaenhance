package thread;

public class CtrlC implements Runnable {  
    private boolean bExit = false;  
    private class ExitHandler extends Thread {  
        public ExitHandler() {  
            super("Exit Handler");  
        }  
        public void run() {  
            System.out.println("Set exit");  
            bExit = true;  
        }  
    }  
    public CtrlC() {  
        Runtime.getRuntime().addShutdownHook(new ExitHandler());  
    }  
    public void run() {  
        while (!bExit) {  
            // Do some thing  
        }  
        System.out.println("Exit OK");  
    }  
    public static void main(String[] args) throws InterruptedException {  
        CtrlC ctrlc = new CtrlC();  
        Thread t = new Thread(ctrlc);  
        t.setName("Ctrl C Thread");  
        t.run();  
        t.join();  
    }  
}  
