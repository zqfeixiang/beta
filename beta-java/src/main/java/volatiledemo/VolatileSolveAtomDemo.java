package volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

// 使用原子类保证数据原子性
public class VolatileSolveAtomDemo {
    // 原子Integer类型，保证原子性
    private AtomicInteger atomicNumber = new AtomicInteger();

    // 底层通过CAS保证原子性
    public void addPlusPlus() {
        atomicNumber.getAndIncrement();
    }

    public static void main(String[] args) {
        VolatileSolveAtomDemo volatileSolveAtomDemo = new VolatileSolveAtomDemo();
        for (int j = 0; j < 20; j++) {
            new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    volatileSolveAtomDemo.addPlusPlus();
                }
            }, String.valueOf(j)).start();
        }

        // 后台默认两个线程：一个是main线程，一个是gc线程
        // 使用 yield 方法让掉当前 CPU 的调度权
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        // 因为volatile不保证原子性，所以选择原子类AtomicInteger来解决volatile不保证原子性问题
        // 最终每次程序执行结果都等于20000
        System.out.println(Thread.currentThread().getName() + "tfinal number result = " + volatileSolveAtomDemo.atomicNumber.get());

    }
}