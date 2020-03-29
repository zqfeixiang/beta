package config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import 接口.Employee;

public class Utils {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-10);
        list.add(9);
        list.add(5);
        list.add(8);
        System.out.println("max is : " + max(list));

        List<String> stringList = new ArrayList<>();
        stringList.add("banana");
        stringList.add("apple");
        stringList.add("orange");
        stringList.add("jack");
        stringList.add("jungle");
        System.out.println("max is : " + max(stringList));

        list.sort(Comparator.reverseOrder());
        list.forEach(v -> System.out.println(v));

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Jack", 120000));
        employees.add(new Employee("Annie", 15000));
        employees.add(new Employee("Lily", 30000));
        employees.add(new Employee("Peter", 18000));
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        employees.forEach(employee -> {
            System.out.println(employee);
        });

    }

    public static <T extends Comparable> T max(Collection<T> c){
        if (c.isEmpty()) throw new NoSuchElementException();
        Iterator<T> iter = c.iterator();
        T largest = iter.next();
        while (iter.hasNext()){
            T next = iter.next();
            if (largest.compareTo(next) < 0){
                largest = next;
            }
        }
        return largest;
    }
}
