package classLoader;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import bean.Employee;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Employee employee = new Employee();
        //获取所有public fields
        Field[] fields = employee.getClass().getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        //获取类的所有字段
        Field[] declaredFields = employee.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        System.out.println("========methods");
        Method[] declaredMethods = employee.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        Method setName = employee.getClass().getDeclaredMethod("setName", String.class);
        setName.invoke(employee, "zhangsan");
        System.out.println(employee.getName());

        System.out.println("-----------");
        System.out.println(employee.getAge());
        Field age = employee.getClass().getDeclaredField("age");
        age.setAccessible(true);
        age.set(employee, 18);
        System.out.println(employee.getAge());
    }
}
