package threadlocal;

import bean.Employee;

public class ServiceContextHolder {
    private static ThreadLocal<Employee> employeeThreadLocal = new ThreadLocal<>();

    private static Employee set(Employee e){
        employeeThreadLocal.set(e);
        return e;
    }

    private static Employee get(){
        return employeeThreadLocal.get();
    }

    private void remove(){
        employeeThreadLocal.remove();
    }
}
