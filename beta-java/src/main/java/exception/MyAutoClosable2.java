package exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyAutoClosable2 implements AutoCloseable{
    public void sayWorld(){
        System.out.println("world");
    }
    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClosable2 close");
    }

    public static void main(String[] args) {
        try(FileReader reader = new FileReader("/Users/dzq/test.txt");
            BufferedReader br = new BufferedReader(reader)) {
            String s = "";
            while ((s = br.readLine()) != null){
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
