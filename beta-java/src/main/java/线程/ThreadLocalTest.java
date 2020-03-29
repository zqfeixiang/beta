package 线程;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ThreadLocalTest {
    @Test
    public void test(){
        ThreadLocal local = new ThreadLocal();
        local.set(Arrays.asList(1,2,3));
        List list = (List) local.get();
        System.out.println(list);
        local.remove();
        System.out.println(local.get());

        ThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
        inheritableThreadLocal.set("Dong");
        new Thread(() -> {
            System.out.println(inheritableThreadLocal.get());
        }).start();
    }
}
