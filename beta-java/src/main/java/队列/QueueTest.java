package 队列;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        Queue<String> linkedList = new LinkedList<>();
        linkedList.add("Dog");
        linkedList.add("Camel");
        linkedList.add("Cat");
        while (!linkedList.isEmpty()){
            System.out.println(linkedList.poll());
        }
    }
}
