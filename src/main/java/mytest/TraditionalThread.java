package mytest;



public class TraditionalThread {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        t1.start();

        Thread t2 = new Thread(new Thread2());
        t2.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable.run()~~~~~");
            }
        }){
            // 因为我们覆写了父类的run方法  所以就直接执行了我们的  如果没有覆盖的话  父类发现run方法为空 然后就去找有没有runnable 有了就执行
            public void run() {
                System.out.println("Thread.run()~~~~~");
            };
        }.start();
    }
}

class Thread1 extends Thread{
    @Override
    public void run() {
        System.out.println("我是继承自Thread的线程");
    }
}


class Thread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("我是实现了Runnable接口的线程");
    }
}