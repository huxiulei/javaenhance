package mytest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CollectionTest {
    @Test
    public void testIterator(){
        Map<String,String> param = new HashMap<>();
        param.put("key1","value1");
        param.put("key2","value2");
        Iterator<Map.Entry<String,String>> iter = param.entrySet().iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }

        //这次遍历就不行了 因为迭代器Iterator 只能遍历一次
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}
