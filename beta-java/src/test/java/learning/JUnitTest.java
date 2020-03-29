package learning;

import net.bytebuddy.asm.Advice;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class JUnitTest {

    @Test
    public void testDateCompare() throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String str1 = "2019-08-27 13:31:39";
        String str2 = "2019-06-28 13:31:40";
        Date d1 = sdf.parse(str1);
        Date d2 = sdf.parse(str2);
        long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别

        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(str1));
        aft.setTime(sdf.parse(str2));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        System.out.println("相差：" + Math.abs(month + result) + "月 " +
                "" + days + "天 " + hours + "小时 " + minutes + "分");

    }

    @Test
    /**
     *  获取两个日期相差的月数
     */
    public void getMonthDiff() throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = "2015-08-16 13:31:00";
        String str2 = "2011-09-30 13:31:40";
        Date d1 = sdf.parse(str1);
        Date d2 = sdf.parse(str2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(str1));
        c2.setTime(sdf.parse(str2));
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;
        // 获取月数差值
        int monthInterval =  (month1 + 12) - month2  ;
        if(day1 < day2) monthInterval --;
        monthInterval %= 12;
        System.out.println(yearInterval * 12 + monthInterval);
    }

    @Test
    public void test(){
        String str = "apple";
        String str2 = "apple2";
        System.out.println(str == str2);
        String s1 = new String("老王");
        String s2 = new String("老王");
        System.out.println(s1.equals(s2));

        try {
            Class.forName("com.dong.beta.dao.domain.ParseRule");
            Class.forName("com.dong.beta.dao.domain.ParseRule");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
