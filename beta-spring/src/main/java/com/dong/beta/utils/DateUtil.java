package com.dong.beta.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author dzq
 * @date 2021/11/3 10:05 下午
 **/

public class DateUtil {

    /**
     * 格式化String时间
     * @param time String类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的Date日期
     */
    public static Date parseStrToDate(String time, String timeFromat) {
        if (time == null || time.equals("")) {
            return null;
        }

        Date date=null;
        try{
            DateFormat dateFormat=new SimpleDateFormat(timeFromat);
            date=dateFormat.parse(time);
        }catch(Exception e){

        }
        return date;
    }

    public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static Timestamp getUTCTimestamp(){
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime utc = ZonedDateTime.of(ldt, ZoneId.of("UTC"));
        Timestamp timestamp = Timestamp.valueOf(utc.toLocalDateTime());
        return timestamp;
    }

    public static void main(String[] args) {
        System.out.println(getUTCTimestamp());
    }
}
