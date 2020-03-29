package 映射;

import java.util.HashMap;
import java.util.Map;

import 接口.Employee;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Employee> staff = new HashMap<>();
        staff.put("001", new Employee("Jack", 12000));
        staff.put("002", new Employee("Peter", 10000));
        staff.put("003", new Employee("Allen", 15000));
        staff.put("004", new Employee("Don", 19000));

        //print all entries
        System.out.println(staff);

        //remove an entry
        staff.remove("002");

        //replace an entry
        staff.put("003", new Employee("Alice", 20000));

        //look up a value
        System.out.println(staff.get("003"));

        //iterate through all entries
        staff.forEach((K, V) -> {
            System.out.println("K=" + K + " V=" + V);
        });
    }
}
