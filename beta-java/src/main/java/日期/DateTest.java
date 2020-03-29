package 日期;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.Executors;

public class DateTest {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) {
        long timeMillis = System.currentTimeMillis();
        Timestamp ts = new Timestamp(timeMillis);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeMillis));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        System.out.println(calendar.getTime());

        LocalDate localDate = LocalDate.now();
        System.out.println("localDate:" + localDate);

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        //获取时间戳
        long milli = Instant.now().toEpochMilli();
        long second = Instant.now().getEpochSecond();
        System.out.println("milli:" + milli);
        System.out.println("second:" + second);

        //时间格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));

        //获得昨天此刻时间
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime yesterday = dateTime.minusDays(1);
        System.out.println("yesterday:" + yesterday);

        //获取本月最后一天
        LocalDate today = LocalDate.now();
        System.out.println(today.with(TemporalAdjusters.lastDayOfMonth()));

        LocalDateTime l1 = LocalDateTime.parse("2019-08-26 19:18:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime l2 = LocalDateTime.now();
        Duration duration = Duration.between(l1, l2);
        System.out.println("seconds:" + duration.getSeconds());

        LocalDate d1 = LocalDate.parse("2019-07-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate d2 = LocalDate.now();
        System.out.println(d2);
        Period period = Period.between(d1, d2);
        System.out.println("相差月数：" + period.getMonths());
        System.out.println("相差天数：" + period.getDays());

        Integer[] arr = {1,2,3,4,5};
        Arrays.asList(arr).forEach(x -> System.out.println(x));

        System.out.println("-------------");
    }
    @Test
    public void testLocalDate(){
        int reserve_time = 200;
        String collection_last_time = "2018/09/11 19:32:00";
        LocalDateTime now = LocalDateTime.now();

        System.out.println(dtf.format(now.minusYears(reserve_time)));
        System.out.println(dtf.format(now.minusMonths(reserve_time)));
        System.out.println(dtf.format(now.minusDays(reserve_time)));
    }

}
