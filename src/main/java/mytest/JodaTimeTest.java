package mytest;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class JodaTimeTest {
    public static void main(String[] args) {

        //时间类得作成
        test1();

        //获取年月日点分秒
        test2();

        //星期的特殊处理
        test3();

        //与JDK日期对象的转换
        test4();

        //日期前后推算
        test5();

        //取特殊日期
        test6();

        //时区
        test7();

        //计算区间
        test8();

        //日期比较
        test9();

        //格式化输出
        test10();

    }

    private static void test1() {

        //方法一：取系统点间
        DateTime dt1 = new DateTime();

        //方法二：通过java.util.Date对象生成
        DateTime dt2 = new DateTime(new Date());

        //方法三：指定年月日点分秒生成(参数依次是：年,月,日,时,分,秒,毫秒)
        DateTime dt3 = new DateTime(2012, 5, 20, 13, 14, 0, 0);

        //方法四：ISO8601形式生成
        DateTime dt4 = new DateTime("2012-05-20");
        DateTime dt5 = new DateTime("2012-05-20T13:14:00");

        //只需要年月日的时候
        LocalDate localDate = new LocalDate(2009, 9, 6);// September 6, 2009

        //只需要时分秒毫秒的时候
        LocalTime localTime = new LocalTime(13, 30, 26, 0);// 1:30:26PM

        System.out.println(dt1.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println(dt2.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println(dt3.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println(dt4.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println(dt5.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println(localDate.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println(localTime.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println();
    }

    private static void test2() {
        DateTime dt = new DateTime();
        //年
        int year = dt.getYear();
        //月
        int month = dt.getMonthOfYear();
        //日
        int day = dt.getDayOfMonth();
        //星期
        int week = dt.getDayOfWeek();
        //点
        int hour = dt.getHourOfDay();
        //分
        int min = dt.getMinuteOfHour();
        //秒
        int sec = dt.getSecondOfMinute();
        //毫秒
        int msec = dt.getMillisOfSecond();

        System.out.print(year + "年");
        System.out.print(month + "月");
        System.out.print(day + "日");
        System.out.print("(星期" + week + ") ");
        System.out.print(hour + "点");
        System.out.print(min + "分");
        System.out.print(sec + "秒");
        System.out.println(msec + "毫秒");
        System.out.println();
    }


    private static void test3() {
        DateTime dt = new DateTime();

        //星期
        switch(dt.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                System.out.println("星期日");
                break;
            case DateTimeConstants.MONDAY:
                System.out.println("星期一");
                break;
            case DateTimeConstants.TUESDAY:
                System.out.println("星期二");
                break;
            case DateTimeConstants.WEDNESDAY:
                System.out.println("星期三");
                break;
            case DateTimeConstants.THURSDAY:
                System.out.println("星期四");
                break;
            case DateTimeConstants.FRIDAY:
                System.out.println("星期五");
                break;
            case DateTimeConstants.SATURDAY:
                System.out.println("星期六");
                break;
        }
        System.out.println();
    }


    private static void test4() {
        DateTime dt = new DateTime();

        //转换成java.util.Date对象
        Date d1 = new Date(dt.getMillis());
        Date d2 = dt.toDate();

        //转换成java.util.Calendar对象
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(dt.getMillis());
        Calendar c2 = dt.toCalendar(Locale.getDefault());

        System.out.println(d1.toString());
        System.out.println(d2.toString());
        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println();
    }

    private static void test5() {
        DateTime dt = new DateTime();

        //昨天
        DateTime yesterday = dt.minusDays(1);
        //明天
        DateTime tomorrow = dt.plusDays(1);
        //1个月前
        DateTime before1month = dt.minusMonths(1);
        //3个月后
        DateTime after3month = dt.plusMonths(3);
        //2年前
        DateTime before2year = dt.minusYears(2);
        //5年后
        DateTime after5year = dt.plusYears(5);

        System.out.println("昨天：" + yesterday.toString("yyyy年MM月dd日"));
        System.out.println("明天：" + tomorrow.toString("yyyy年MM月dd日"));
        System.out.println("1个月前：" + before1month.toString("yyyy年MM月dd日"));
        System.out.println("3个月后：" + after3month.toString("yyyy年MM月dd日"));
        System.out.println("2年前：" + before2year.toString("yyyy年MM月dd日"));
        System.out.println("5年后：" + after5year.toString("yyyy年MM月dd日"));
        System.out.println();
    }

    private static void test6() {
        DateTime dt = new DateTime();

        //月末日期
        DateTime lastday = dt.dayOfMonth().withMaximumValue();

        //90天后那周的周一
        DateTime firstday = dt.plusDays(90).dayOfWeek().withMinimumValue();

        System.out.println("本月最大日期：" + lastday.toString("yyyy年MM月dd日"));
        System.out.println("90天后那周的周一：" + firstday.toString("yyyy年MM月dd日"));
        System.out.println();
    }

    private static void test7() {
        //默认设置为日本时间
        DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));
        DateTime dt1 = new DateTime();

        //伦敦时间
        DateTime dt2 = new DateTime(DateTimeZone.forID("Europe/London"));

        System.out.println("日本时间：" + dt1.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println("伦敦时间：" + dt2.toString("yyyy年MM月dd日HH点mm分ss秒"));
        System.out.println();
    }

    private static void test8() {
        DateTime begin = new DateTime("2012-02-01");
        DateTime end = new DateTime("2012-05-01");

        //计算区间毫秒数
        Duration d = new Duration(begin, end);
        long time = d.getMillis();

        //计算区间天数
        Period p = new Period(begin, end, PeriodType.days());
        int days = p.getDays();

        //计算特定日期是否在该区间内
        Interval i = new Interval(begin, end);
        boolean contained = i.contains(new DateTime("2012-03-01"));

        System.out.print("2012-02-01 和 2012-05-01 之间相差：");
        System.out.print(time + "毫秒 ");
        System.out.println(days + "天 ");
        System.out.println("2012-03-01是否在2012-02-01 和 2012-05-01 之间：" + contained);
        System.out.println();
    }

    private static void test9() {
        DateTime d1 = new DateTime("2012-02-01");
        DateTime d2 = new DateTime("2012-05-01");

        //和系统时间比
        boolean b1 = d1.isAfterNow();
        boolean b2 = d1.isBeforeNow();
        boolean b3 = d1.isEqualNow();

        //和其他日期比
        boolean f1 = d1.isAfter(d2);
        boolean f2 = d1.isBefore(d2);
        boolean f3 = d1.isEqual(d2);

        System.out.print(b1 + "\t");
        System.out.print(b2 + "\t");
        System.out.println(b3);

        System.out.print(f1 + "\t");
        System.out.print(f2 + "\t");
        System.out.println(f3);
        System.out.println();
    }

    private static void test10() {
        DateTime dateTime = new DateTime();

        String s1 = dateTime.toString("yyyy/MM/dd hh:mm:ss.SSSa");
        String s2 = dateTime.toString("yyyy-MM-dd HH:mm:ss");
        String s3 = dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa");
        String s4 = dateTime.toString("yyyy/MM/dd HH:mm ZZZZ");
        String s5 = dateTime.toString("yyyy/MM/dd HH:mm Z");

        System.out.println(s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5);
        System.out.println();
    }
}
