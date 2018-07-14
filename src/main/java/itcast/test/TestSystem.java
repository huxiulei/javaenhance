package itcast.test;

import java.util.Map;
import java.util.Properties;

public class TestSystem {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Properties  pro = System.getProperties();
//		for(Object p:pro.keySet()){
//			System.out.println(pro.get(p));
//		}

        // 环境变量
        Map m = System.getenv();
        System.out.println("env----> " + m);

        System.setProperty("mykey", "myvalue");
        System.out.println(System.getProperty("mykey"));
    }


}
