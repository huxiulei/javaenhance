package itcast.day1;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @文件名   :
 * @作者信息 :huxiulei
 * @版本信息 :version 1.0
 * @创建时间 :2013-4-7 下午02:47:29
 * @功能说明 :
 */
public class MyReflectTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        ReflectPoint p = new ReflectPoint(5,6);


        Field[] fileds = p.getClass().getFields(); // 得到ReflectPoint类中的所有非私有属性字段
        for(Field field :fileds){
            System.out.println(field);
            if(field.getType() == String.class){
                String oldChar = (String)field.get(p);
                System.out.println("oldChar = " + oldChar);
                String newChar = oldChar.replace("a", "b");
                field.set(p, newChar);
            }
        }
        System.out.println(p);


        String[] strs = new String[]{"a","b","c"};
        System.out.println(Arrays.asList(strs));
        System.out.println(MyReflectTest.class.getClassLoader());

    }

}
