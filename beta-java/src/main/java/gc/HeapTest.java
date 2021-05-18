package gc;

import java.util.ArrayList;
import java.util.List;

public class HeapTest {
    public static void main(String[] args) throws InterruptedException {
        List<HeapTest> heapTests = new ArrayList<>();
        while (true){
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }
}
