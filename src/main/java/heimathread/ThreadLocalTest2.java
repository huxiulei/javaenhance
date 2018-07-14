package heimathread;

public class ThreadLocalTest2 extends Thread{

    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("当前线程: " + Thread.currentThread().getName() +  " 获取到的值是: " + Counter.getNextValue());
        }
    }

    public static void main(String[] args) {
        ThreadLocalTest2 t1 = new ThreadLocalTest2();
        ThreadLocalTest2 t2 = new ThreadLocalTest2();
        ThreadLocalTest2 t3 = new ThreadLocalTest2();

        t1.start();
        t2.start();
        t3.start();
    }
}



class Counter{
    private static ThreadLocal<Integer> counter = new ThreadLocal<Integer>(){
        protected synchronized Integer initialValue() {
            return 10;
        }
    };


    public static Integer getValue(){
        return counter.get();
    }

    public static void setValue(Integer value){
        counter.set(value);
    }


    public static Integer getNextValue(){
        //  counter.get()+1 或者 直接 getValue()+1 都行
        counter.set(counter.get()+1);
        return counter.get();
    }
}