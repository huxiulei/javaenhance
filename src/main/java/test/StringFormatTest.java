package test;

import java.util.Date;
import org.junit.Test;

public class StringFormatTest {

    @Test
    public void test() {
        long now = System.currentTimeMillis();
        String s = String.format("%tR", now); // "15:12"
        System.out.println(s);
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void test2() {
        // Current month/day/year
        long now = System.currentTimeMillis();
        Date d = new Date(now);
        String s = String.format("%tD", d); // "07/13/04"
        System.out.println(s);
    }

    @Test
    public void test3() {
        String s = String.format("%,d", Integer.MAX_VALUE); // "2,147,483,647"
        System.out.println(s);
        String ss = String.format("%05d", 123);
        System.out.println(ss);
    }

    @Test
    public void test4() {
        Double d = 12.3366;

        System.out.println(String.format("%2$08d", -3123, -5566));
        System.out.println(String.format("%1$9d", -31));
        System.out.println(String.format("%1$-9d", -31));
        System.out.println(String.format("%1$(9d", -31));
        System.out.println(String.format("%1$#9x", 5689));
        // 小数点后面两位
        System.out.println(String.format("%1$.2f", 5689.0)); // 必须是同类型的才能进行转换

        // 格式化的位置

        String str = "I love ni %s, you love me %s";
        String str2 = "I love ni %2$s, you love me %1$s";

        System.out.println(String.format(str, "renjunjie","songliyu"));

        System.out.println(String.format(str2, "renjunjie","songliyu"));


        // 数组的操作
        Object[] sendData = new Object[4];
        sendData[0] = Integer.valueOf(1);
        sendData[1] = "172.12.1.2";
        sendData[2] = Integer.valueOf(123);
        sendData[3] = "testadfaerfa";
        String sendDataString = String.format("%d,%s,%d,%s",(Object[]) sendData);
        System.out.println("sendDataString: " + sendDataString);
    }

}
