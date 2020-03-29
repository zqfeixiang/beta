package 泛型迭代器;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TypeTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Java虚拟机");
        list.add("Java中文社群");
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String str = iterator.next();
            if("Java虚拟机".equals(str)){
                iterator.remove();
            }
        }
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("over");
    }
}
