/**
 * Copyright (C), 2015-2019
 * FileName: Java8TimeDemo
 * Author:   luo.yongqian
 * Date:     2019/5/30 16:32
 * Description: java8时间demo
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/30 16:32      1.0.0               创建
 */
package com.roboslyq.java.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 *
 * 〈java8时间demo〉
 * @author luo.yongqian
 * @create 2019/5/30
 * @since 1.0.0
 * 1、java8引入了一套全新的时间日期API，本篇随笔将说明学习java8的这套API。
 *
 * 2、java。time包中的是类是不可变且线程安全的。新的时间及日期API位于java.time中，下面是一些关键类
 *      ●Instant——它代表的是时间戳
 *      ●LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
 *      ●LocalTime——它代表的是不含日期的时间
 *      ●LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
 *      ●ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
 */
public class Java8TimeDemo {
    public static void main(String[] args) {
        Java8TimeDemo timeDemo = new Java8TimeDemo();
//        timeDemo.dateNow();
//        timeDemo.formatterDate();
        timeDemo.format();
    }

    /**
     * 获取当前日期，不包含时间
     */
    public void dateNow(){
        //完整日期，如：2019-05-30
        System.out.println(LocalDate.now());
        //获取年
        System.out.println(LocalDate.now().getYear());
        //获取月
        System.out.println(LocalDate.now().getMonthValue());
        //获取日
        System.out.println(LocalDate.now().getDayOfMonth());
        //获取月(英语枚举，如：MAY,详情见MONTH枚举类)
        System.out.println(LocalDate.now().getMonth());
        //获取一周中的星期几(DayOfWeek桥梁)
        System.out.println(LocalDate.now().getDayOfWeek());
        //获取一年中的第几天
        System.out.println(LocalDate.now().getDayOfYear());

        //将字符串转换成日期
        System.out.println(LocalDate.of(2019,8,20));

    }

    /**
     * 日期格式化（将字符串转换成日期）
     */
    public void formatterDate(){
        // 定义一个任意格式的日期时间字符串
        String str1 = "2014==04==12 01时06分09秒";
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter fomatter1 = DateTimeFormatter.ofPattern("yyyy==MM==dd HH时mm分ss秒");
        // 执行解析
        LocalDateTime dt1 = LocalDateTime.parse(str1, fomatter1);
        System.out.println(dt1); // 输出 2014-04-12T01:06:09

        //
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2017-06-17", formatter);
        System.out.println(formatter.format(date));
    }

    public void format(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2019-01-26T10:15:30+01:00[Europe/Paris]");
        System.out.println(DateTimeFormatter.BASIC_ISO_DATE.format(zonedDateTime));         // 20190126+0100
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(zonedDateTime));         // 2019-01-26
        System.out.println(DateTimeFormatter.ISO_OFFSET_DATE.format(zonedDateTime));        // 2019-01-26+01:00
        System.out.println(DateTimeFormatter.ISO_DATE.format(zonedDateTime));               // 2019-01-26+01:00
        System.out.println(DateTimeFormatter.ISO_LOCAL_TIME.format(zonedDateTime));         // 10:15:30
        System.out.println(DateTimeFormatter.ISO_OFFSET_TIME.format(zonedDateTime));        // 10:15:30+01:00
        System.out.println(DateTimeFormatter.ISO_TIME.format(zonedDateTime));               // 10:15:30
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(zonedDateTime));    // 2019-01-26T10:15:30
        System.out.println(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(zonedDateTime));   // 2019-01-26T10:15:30+01:00
        System.out.println(DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime));    // 2019-01-26T10:15:30+01:00[Europe/Paris]
        System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime));          // 2019-01-26T10:15:30+01:00[Europe/Paris]
        System.out.println(DateTimeFormatter.ISO_ORDINAL_DATE.format(zonedDateTime));       // 2019-026+01:00
        System.out.println(DateTimeFormatter.ISO_WEEK_DATE.format(zonedDateTime));          // 2019-W04-6+01:00
        System.out.println(DateTimeFormatter.ISO_INSTANT.format(zonedDateTime));            // 2019-01-26T09:15:30Z
        System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(zonedDateTime));     // Sat, 26 Jan 2019 10:15:30 +0100

        System.out.println(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL).format(zonedDateTime));          // 上午10时15分30秒 CET
        System.out.println(DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG).format(zonedDateTime));          // 上午10时15分30秒
        System.out.println(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(zonedDateTime));        // 10:15:30
        System.out.println(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(zonedDateTime));         // 上午10:15

        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(zonedDateTime));          // 2019年1月26日 星期六
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(zonedDateTime));          // 2019年1月26日
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(zonedDateTime));        // 2019-1-26
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(zonedDateTime));         // 19-1-26

        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTime));      // 2019年1月26日 星期六 上午10时15分30秒 CET
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(zonedDateTime));      // 2019年1月26日 上午10时15分30秒
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(zonedDateTime));    // 2019-1-26 10:15:30
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(zonedDateTime));     // 19-1-26 上午10:15
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM).format(zonedDateTime));  // 2019年1月26日 星期六 10:15:30

        TemporalAccessor parsed = DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2019-01-26T10:15:30");
        LocalTime time = parsed.query(LocalTime::from);
        Period extraDays = parsed.query(DateTimeFormatter.parsedExcessDays());
        System.out.println(time);       // 10:15:30
        System.out.println(extraDays);  // P0D

        Boolean query = parsed.query(DateTimeFormatter.parsedLeapSecond());
        System.out.println(query);      // false


// 自定义格式化格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format1 = dateTimeFormatter.format(zonedDateTime);
        String format2 = dateTimeFormatter.format(localDateTime);
        System.out.println(format1);    // 2019-01-26 10:15:30
        System.out.println(format2);    // 2019-01-26 13:41:56

// Locale语言环境
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(localDateTime)); // 2019-01-26 13:49:33
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.KOREAN).format(localDateTime));// 2019-01-26 13:49:33

// 得到{},ISO resolved to 2019-01-26T10:15:30对象
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse(format1);
        int year = temporalAccessor.get(ChronoField.YEAR);
        int hour = temporalAccessor.get(ChronoField.CLOCK_HOUR_OF_DAY);
        System.out.println(temporalAccessor);
        System.out.println(year);   // 2019
        System.out.println(hour);   // 10

// 另一种格式化和解析
        String format3 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format3);    // 2019-01-26 14:14:01
        LocalDateTime parse = LocalDateTime.parse("2019-01-26T10:15:30");// 日期字符串转为日期
        System.out.println(parse);      // 2019-01-26T10:15:30

        String format4 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E"));
        LocalDateTime parse2 = LocalDateTime.parse(format4,DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E"));
        System.out.println(parse2);     // 2019-01-26T14:25:30
    }
}