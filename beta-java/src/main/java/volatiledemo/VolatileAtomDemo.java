package volatiledemo;

/**
 *  volatile不保证原子性代码验证
 *  原子性：保证数据一致性、完整性
 */
public class VolatileAtomDemo {

    volatile int number = 0;

    public void addPlusPlus() {
        number++;
    }

    public static void main(String[] args) {
        VolatileAtomDemo volatileAtomDemo = new VolatileAtomDemo();
        for (int j = 0; j < 20; j++) {
            new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    volatileAtomDemo.addPlusPlus();
                }
            }, String.valueOf(j)).start();
        }

        // 后台默认两个线程：一个是main线程，一个是gc线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        // 如果volatile保证原子性的话，最终的结果应该是20000
        // 但是每次程序执行结果都不等于20000
        System.out.println(Thread.currentThread().getName() + "tfinal number result = " + volatileAtomDemo.number);
    }
}