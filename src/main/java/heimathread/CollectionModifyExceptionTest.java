package heimathread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

@SuppressWarnings("all")
/**
 * 普通的ArrayList集合中 有并发的判断  当在遍历的过程中 对齐进行移除操作 下标达到某个临界的时候会抛错异常
 * 采用 CopyOnWriteArrayList 可以解决  内部是对集合进行了一次拷贝
 */
public class CollectionModifyExceptionTest {
    public static void main(String[] args) {
        // 直接使用ArrayList的时候  会报错 在移除首尾的时候  移除中间的元素 不会 但是会导致遍历后少一个元素
        Collection users = new ArrayList();//new CopyOnWriteArrayList();

        //
        users.add(new User("张三", 28));
        users.add(new User("李四", 25));
        users.add(new User("王五", 31));
        Iterator itrUsers = users.iterator();
        while (itrUsers.hasNext()) {
            System.out.println("aaaa");
            User user = (User) itrUsers.next();
            if ("王五".equals(user.getName())) {
                users.remove(user);
                // itrUsers.remove();
            } else {
                System.out.println(user);
            }
        }
    }
}
