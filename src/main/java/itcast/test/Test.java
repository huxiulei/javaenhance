package itcast.test;

import java.text.DecimalFormat;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Double d = 10d;
        DecimalFormat df = new DecimalFormat("#.0");
        Double dd = d / 4;
        System.out.println(Math.rint(dd));
        System.out.println(0);
        double k = 2.5;
        System.out.println("四舍五入取整:Math.rint(2.5)=" + (int) Math.rint(k));
        System.out.println("四舍五入取整:(2.5)=" + new DecimalFormat("0").format(k));


        int rand = new Random().nextInt(10);
        boolean b = new Random().nextBoolean();
        System.out.println("rand = " + rand);
        System.out.println("b = " + b);
    }
}
