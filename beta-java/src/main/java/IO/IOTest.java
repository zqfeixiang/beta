package IO;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class IOTest {

    Logger logger = LoggerFactory.getLogger(IOTest.class);

    @Test
    public void testWriter(){
        try {
            Writer writer = new FileWriter("./test.txt", true);
            writer.append("IOTest");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReader(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./test.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInputStream() throws Exception {
        InputStream inputStream = new FileInputStream(new File("./test.txt"));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String content = new String(bytes, "utf-8");
        System.out.println(content);
        logger.info(content);
    }

    @Test
    public void testOutputStream() throws Exception {
        OutputStream outputStream = new FileOutputStream(new File("./test.txt"), true);
        outputStream.write("Hello World".getBytes());
        outputStream.close();
    }
}
