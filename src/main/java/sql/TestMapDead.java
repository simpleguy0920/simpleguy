package sql;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestMapDead {
    public static void main(String[] args) {
        HashMapThread thread0 = new HashMapThread();
        HashMapThread thread1 = new HashMapThread();
        HashMapThread thread2 = new HashMapThread();
        HashMapThread thread3 = new HashMapThread();
        HashMapThread thread4 = new HashMapThread();
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class HashMapThread extends Thread {
    private static final AtomicInteger ai = new AtomicInteger();
    private static final Map<Integer, Integer> map = new HashMap<>();

    @Override
    public void run() {
        while (ai.get() < 10000000) {
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
        }
    }
}
