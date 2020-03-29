package 队列;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));
        System.out.println("Iterating over elements...");

        for (LocalDate l : pq){
            System.out.println(l);
        }

        System.out.println("Removing elements...");
        while (!pq.isEmpty()){
            pq.remove();
        }
    }
}
