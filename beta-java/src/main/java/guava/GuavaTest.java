package guava;

import com.google.common.base.Joiner;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuavaTest {

    @Test
    public void test1(){
        String[] headers = {"aa", "bb", "cc"};
        List<String> list = Arrays.asList(headers);
        String result = Joiner.on(",").join(list);
        log.info(result);
    }
}
