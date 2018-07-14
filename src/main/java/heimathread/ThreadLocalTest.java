package heimathread;

import java.util.Random;

/**
 * FIXME ThreadLocal每次只能存入一个值 所以为了达到保存多个值的 我们可以通过封装一个对象 把值放到对象中 如此以来可行
 * 比较巧妙的设计方式就是 采用类似单例的设计
 * @author hu.xl
 *
 */
public class ThreadLocalTest {
    /**
     * ThreadLocal 是以空间换时间的方式 为每个线程提供一个变量的副本
     * synchronized 是以时间换空间的    提供一份变量 让不同的线程排队访问
     */
    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();
    private static ThreadLocal<MyThreadScopeData> myThreadScopeData = new ThreadLocal<MyThreadScopeData>();
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()
                            + " has put data :" + data);
                    x.set(data);
/*					MyThreadScopeData myData = new MyThreadScopeData();
					myData.setName("name" + data);
					myData.setAge(data);
					myThreadScopeData.set(myData);*/
                    MyThreadScopeData.getThreadInstance().setName("name" + data);
                    MyThreadScopeData.getThreadInstance().setAge(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A{
        public void get(){
            int data = x.get();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data :" + data);
/*			MyThreadScopeData myData = myThreadScopeData.get();;
			System.out.println("A from " + Thread.currentThread().getName()
					+ " getMyData: " + myData.getName() + "," +
					myData.getAge());*/
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " getMyData: " + myData.getName() + "," +
                    myData.getAge());
        }
    }

    static class B{
        public void get(){
            int data = x.get();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data :" + data);
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " getMyData: " + myData.getName() + "," +
                    myData.getAge());
        }
    }
}

class MyThreadScopeData{
    private MyThreadScopeData(){}
    //private static MyThreadScopeData instance = null;//new MyThreadScopeData();
    // 相当于搞了一个池出来 不用每次都去创建这个ThreadLocal
    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();
    public static /*synchronized*/ MyThreadScopeData getThreadInstance(){
        MyThreadScopeData instance = map.get();
        if(instance == null){
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }

    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
