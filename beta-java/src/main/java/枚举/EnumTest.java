package 枚举;

import org.junit.Test;

import java.util.Scanner;

public class EnumTest {
    @Test
    public void testEnum(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter s size:(SMALL, MEDIUM, LARGE, EXTRA_LARGE)");
            String input = scanner.next().toUpperCase();
            Size size = Enum.valueOf(Size.class, input);
            System.out.println("input = " + size);
            System.out.println("size = " + size.getSize());
    }
}
