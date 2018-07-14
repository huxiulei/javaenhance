package heimathread.queue;
public class Test {

    public static void main(String[] args) {

        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        for(int i=0;i<10;i++){  //这行不能改动
            String input = i+"";  //这行不能改动
            String output = TestDo1.doSome(input);
            System.out.println(Thread.currentThread().getName()+ ":" + output);
        }
    }
}

//不能改动此TestDo类
class TestDo1 {
    public static String doSome(String input){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":"+ (System.currentTimeMillis() / 1000);
        return output;
    }
}