package exception;

/**
 * try-with-resources语句确保在语句执行完毕后，每个资源都被自动关闭。
 * 任何实现了{@link AutoCloseable}的对象, 包括所有实现了{@link java.io.Closeable}
 * 的对象, 都可以用作一个资源。
 *
 */
public class MyAutoClosable implements AutoCloseable{
    private void sayHello(){
        System.out.println("hello");
    }
    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClosable close");
    }

    public static void main(String[] args) {
        try(MyAutoClosable closable = new MyAutoClosable();
            MyAutoClosable2 closable2 = new MyAutoClosable2()){
            closable.sayHello();
            closable2.sayWorld();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
