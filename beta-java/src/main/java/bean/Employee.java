package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Comparable{
    private String name;
    private Integer age;
    private double salary;

    @Override
    public int compareTo(Object o) {
        if (o instanceof Employee){
            Employee em = (Employee) o;
            return -Double.compare(this.salary, em.getSalary());
        }
        return 0;
    }
}
