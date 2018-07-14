package heimathread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * FIXME 缓存的伪代码实现   防止并发  采用的读写锁实现
 * @author hu.xl
 *
 */
public class CacheDemo {

    private Map<String, Object> cache = new HashMap<String, Object>();
    public static void main(String[] args) {

    }

    private ReadWriteLock rwl = new ReentrantReadWriteLock(); // 读写锁
    public Object getData(String key){
        rwl.readLock().lock();
        Object value = null;
        try{
            value = cache.get(key);
            if(value == null){
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try{
                    if(value==null){  // 此处再次判断是防止另一个也在写
                        value = "aaaa";// 实际失去queryDB();
                    }
                }finally{
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
        }finally{
            rwl.readLock().unlock();
        }
        return value;
    }
}
