package 集合排序;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShuffleTest {
    public static void main(String[] args) {
        /*List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 49; i++){
            list.add(i);
        }
        List<String> list1 = new ArrayList<>();
        list1.add("aa");
        list1.add("ab");
        list1.add("bb");
        list1 = list1.stream().filter(e -> !"aa".equals(e)).collect(Collectors.toList());
        list1.stream().forEach(e -> System.out.println(e));
        Collections.shuffle(list);
        List<Integer> list2 = list.subList(0, 6);
        Collections.sort(list2);
        System.out.println(list2);*/

        File file = new File("/Users/dzq/IdeaProjects/1//2.txt");
        if (!file.exists()){
            file.mkdirs();
        }
    }

}
