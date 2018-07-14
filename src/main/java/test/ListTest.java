package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;


public class ListTest {

    // 现在有一个需求,在一个长度为50的列表中删除位置是10~20的元素
    @Test
    public void test() {
        List<Integer> list = new ArrayList<Integer>();
        // 增加50行
        for (int i = 0, size = 50; i < size; i++) {
            list.add(i);
        }
        // 方法1
        for (int i = 10; i < 20; i++) {
            list.remove(10); // 此处是10  每移除一个后下一个就会往前填充,下表就又是10了
        }
        System.out.println(list);
        // 方法2
//		list.subList(10, 10).clear();
//		System.out.println(list);
    }

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        List<Integer> subList = list.subList(0, 2);
        // 原列表增加数据
        list.add(3);
        // 对比元素数量
        System.out.println(list.size() == subList.size());

        // 运行结果,抛出java.util.ConcurrentModificationException异常
        /**
         * 看到这个异常,很多人会觉得很郁闷,并发修改异常?可我程序并没有运行在多线程的环境当中,
         * 而原因是因为,当创建subList时,list会将1个变量modCount传递到subList的构造参数当中,
         * 而当list进行修改时,就会对modCount进行累加,而subList在进行操作时,
         * 会检查modCount是否和list的一致,若然不一直就抛出异常,这就是原因,
         * 这也是subList和数据库视图不同的地方,表修改后直接反应到视图,而list修改后,不会反应到subList,
         * 所以在使用subList时,应该使用如下方式对list进行加锁
         */
    }

    @Test
    public void test3() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        List<Integer> subList = list.subList(0, 2);
        // 设置列表为只读状态
        list = Collections.unmodifiableList(list);
        // 再对列表进行操作
        list.add(4);
        System.out.println(list);
        // 运行结果,抛出java.lang.UnsupportedOperationException异常
    }

    @Test
    public void test4() {
        // 使用匿名内部类方式实现
        List<Integer> list = new ArrayList<Integer>();
        Collections.sort(list, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

        // TreeMap实现
        Map<String, Integer> treeMap = new TreeMap<String, Integer>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return 0;
                    }
                });
    }

    @Test
    public void test5() {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();

        // 并集操作
        list1.addAll(list2);

        // 交集操作,retainAll会删除list1中没有出现在list2中的元素
        list1.retainAll(list2);

        // 差集操作
        list1.removeAll(list2);

        // 无重复并集
        list2.removeAll(list1);
        list1.addAll(list2);
    }

    @Test
    public void test6(){
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count = count++;
        }
        System.out.println(count);
    }
}

