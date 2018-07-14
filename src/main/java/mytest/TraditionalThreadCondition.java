package mytest;

public class TraditionalThreadCondition {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final Buiness buiness = new Buiness();

        new Thread(
                new Runnable() {

                    @Override
                    public void run() {

                        for(int i=1;i<=50;i++){
                            buiness.sub(i);
                        }

                    }
                }
        ).start();

        for(int i=1;i<=50;i++){
            buiness.main(i);
        }
    }

}


class Buiness{
    private boolean isShouldSub = true;
    public synchronized void main(int j){
        while(isShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=1;i<=100;i++){
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }
        isShouldSub = true;
        this.notify();
    }


    public synchronized void sub(int j){
        while(!isShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=1;i<=10;i++){
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        isShouldSub = false;
        this.notify();
    }
}