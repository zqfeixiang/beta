package 接口;

import java.util.Arrays;
import java.util.Comparator;

public class EmployeeTest {

    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("zhangsan", 35000);
        staff[1] = new Employee("lisi", 3000);
        staff[2] = new Employee("wangwu", 40000);
        Arrays.sort(staff);
        Arrays.sort(staff, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });
        for (Employee e : staff){
            System.out.println("name:" + e.getName() + " salary:" + e.getSalary());
        }
    }
}
