package volatiledemo;

/**
 * @author dzq
 * @date 2021/10/12 10:28 下午
 *
 * 演示volatile作用
 **/
public class VolatileDemo {

    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("waiting data...");
                while (!initFlag){

                }
                System.out.println("=======success");
            }
        }).start();

        Thread.sleep(2000);

        new Thread(() -> changeFlag()).start();
    }

    private static void changeFlag(){
        System.out.println("preparing data...");
        initFlag = true;
        System.out.println("end preparing data...");
    }
}
