package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author huxl
 * @date Jul 6, 201010:50:29 AM
 */
public class DateUtil {
    public static String strToDateYMDHMS(Date date,boolean isStart){
        try{
            if(isStart)
            {
                return parseDate(date)+" 00:00:00";
            }
            else
            {
                return parseDate(date)+" 23:59:59";
            }
        }catch(Exception pe){
            return null;
        }
    }
    // 默认转换出为2005-09-08类型的当前日
    public static String getToday() {
        Date dt = new java.util.Date();
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dt);
    }

    public static String parseDates(Date dt) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }

    public static String parseDates2(Date dt) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(dt);
    }

    public static String parseDates3(Date dt) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        return sdf.format(dt);
    }

    public static String parseDate(Date dt) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dt);
    }
    public static String parseDate4(String str) throws Exception{
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("yyyyMMddHHmm");
        Date dt = sdf.parse(str);

        return sdf2.format(dt);
    }
    public static String parseDate8(Date dt) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
        return sdf.format(dt);
    }

    public static Date strintToDate(String ds) throws ParseException {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(ds);
        return d;
    }

    public static Date strintToDate9(String ds) throws ParseException {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        Date d = sdf.parse(ds);
        return d;
    }

    public static Date strintToDate8(String ds) throws ParseException {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
        Date d = sdf.parse(ds);
        return d;
    }

    public static Date strintTotime() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(DateUtil.getTime("HH:mm:ss"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    public static Date strintToDatetime(String ds) throws ParseException {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy/MM/dd HH:mm:ss");
        Date d = sdf.parse(ds);
        return d;
    }

    public static Date nowDatetime() throws ParseException {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(getNow());
        return d;
    }

    /**
     * 日期字符串重新格式化
     * @param ds
     * @param format
     * @return
     * @throws ParseException
     */
    public static String strSwapFormat(String ds,String oldFormat,String newFormat) throws ParseException {
        SimpleDateFormat osdf = new java.text.SimpleDateFormat(oldFormat);
        Date d = osdf.parse(ds);
        SimpleDateFormat nsdf = new java.text.SimpleDateFormat(newFormat);
        return nsdf.format(d);
    }
    /**
     * 相对今天向后推dayAfter天的时间
     * @param d
     * @param dayAfter
     * @return
     * @throws ParseException
     */
    public static String getDayAfter(Date d, int dayAfter)
            throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, dayAfter);
        Date dt = new Date(c.get(Calendar.YEAR) - 1900, c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        return DateUtil.parseDate(dt);
    }

    /**
     *  相对今天向后推dayAfter分钟的时间字符串
     * @param d
     * @param dayAfter
     * @return
     * @throws ParseException
     */
    public static String getminuteAfter(Date d, int dayAfter)
            throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MINUTE, dayAfter);
        Date dt = new Date(c.get(Calendar.YEAR) - 1900, c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c
                .get(Calendar.MINUTE), c.get(Calendar.SECOND));
        return DateUtil.parseDates(dt);
    }

    /**
     * 相对今天向后推dayAfter分钟的时间
     * @param d
     * @param dayAfter
     * @return
     * @throws ParseException
     */
    public static Date getminuteAfters(Date d, int dayAfter)
            throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MINUTE, dayAfter);
        Date dt = new Date(c.get(Calendar.YEAR) - 1900, c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c
                .get(Calendar.MINUTE), c.get(Calendar.SECOND));
        return dt;
    }

    /**
     * 相对今天向后推dayAfter个月的时间
     * @param d
     * @param monthAfter
     * @return
     */
    public static String getDayAfterMonth(Date d, int monthAfter) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MONTH, monthAfter);
        Date dt = new Date(c.get(Calendar.YEAR) - 1900, c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        return DateUtil.parseDate(dt);
    }

    /**
     * 获取本月月初时间
     * @param d
     * @return
     */
    public static String getFirstDayOfMonth(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date dt = new Date(c.get(Calendar.YEAR) - 1900, c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        return DateUtil.parseDate(dt);
    }


    /**
     * 获取本月月末时间
     * add by zhoujunwei  2011 7 22
     * @param d
     * @return
     */
    public static String getEndDayOfMonth(Date d) {
        Calendar localTime=Calendar.getInstance();
        String strY = null;
        String strZ = null;
        boolean leap = false;
        int x = localTime.get(Calendar.YEAR);
        int y = localTime.get(Calendar.MONTH) + 1;
        if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
            strZ = "31";
        }
        if (y == 4 || y == 6 || y == 9 || y == 11) {
            strZ = "30";
        }
        if (y == 2) {
            leap = leapYear(x);
            if (leap) {
                strZ = "29";
            }
            else {
                strZ = "28";
            }
        }
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-" + strZ;

    }
    // 根据传入的type进行转换日期，type必须遵循Date转换的规则
    public static String getTime(String type) {
        Date dt = new java.util.Date();
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(type);
        return sdf.format(dt);
    }

    public static int getDay(String d) {
        int day = 1;
        if (d.length() >= 10) {
            day = Integer.parseInt(d.substring(8, 10));
        }
        return day;
    }

    public static int getMonth(String d) {
        int m = 1;
        if (d.length() >= 10) {
            m = Integer.parseInt(d.substring(5, 7));
        }
        return m;
    }

    public static int getYear(String d) {
        int y = 1;
        if (d.length() >= 10) {
            y = Integer.parseInt(d.substring(0, 4));
        }
        return y;
    }

    // 根据传入的两个日期计算相差天数
    public static int getDayBetween(Calendar c1, Calendar c2) {
        int iReturn = 0;
        if (c1 != null && c2 != null) {
            iReturn = (int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / (24 * 60 * 60 * 1000));
        }
        return iReturn;
    }

    // 根据传入的两个日期计算相差年数
    public static int getYearBetween(Calendar c1, Calendar c2) {
        int iReturn = 0;
        if (c1 != null && c2 != null) {
            iReturn = (int) (getDayBetween(c1, c2) / 365);
        }
        return iReturn;
    }

    //根据传入的两个时间段 计算相差的分钟数
    public static int getMin(String str1 ,String str2){
        try {
            Date d1 = strintToDate9(str1);
            Date d2 = strintToDate9(str2);
            Long hour = (d2.getTime() - d1.getTime())/60/1000;
            return hour>0?hour.intValue():0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 把2005-02-5这样的日期格式转化为Calendar类型
     *
     * @param stringTo
     * @return
     * @throws ParseException
     */
    public static Calendar stringToCalendar(String stringTo)
            throws ParseException {
        Calendar c = Calendar.getInstance();
        if (stringTo != null) {
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(stringTo);
            c.setTime(d);
        }
        return c;
    }

    public static String getNextMonday(String s) throws ParseException {
        Calendar c = DateUtil.stringToCalendar(s);
        return (DateUtil.getDayAfter(DateUtil.strintToDate(s), 7 - c
                .get(Calendar.DAY_OF_WEEK) + 2));
    }

    public static String getNextSunday(String s) throws ParseException {
        Calendar c = DateUtil.stringToCalendar(s);
        return (DateUtil.getDayAfter(DateUtil.strintToDate(s), 7 - c
                .get(Calendar.DAY_OF_WEEK) + 2 + 7));
    }

    public static String getThisSaturday(String s) throws ParseException {
        Calendar c = DateUtil.stringToCalendar(s);
        return (DateUtil.getDayAfter(DateUtil.strintToDate(s), 7 - c
                .get(Calendar.DAY_OF_WEEK)));
    }

    public static String getWeekOfYear(String s) throws ParseException {
        Calendar c = DateUtil.stringToCalendar(s);
        return String.valueOf(c.get(Calendar.WEEK_OF_YEAR));

    }

    public static String getNow() {
        return DateUtil.getTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getNow14() {
        return DateUtil.getTime("yyyyMMdd HH:mm:ss");
    }
    public static String getNow15() {
        return DateUtil.getTime("yyyyMMddHHmmss");
    }
    public static String getNowTime() {
        return DateUtil.getTime("HH:mm:ss");
    }

    /**
     * 功能：判断输入年份是否为闰年<br>
     * add by zhoujunwei
     * @param year
     * @return 是：true  否：false
     * @author pure
     */
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) leap = true;
                else leap = false;
            }
            else leap = true;
        }
        else leap = false;
        return leap;
    }

    /**
     * 获取当前日期的下一天 add by huxl
     * @return
     */
    public static String getNextDay(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        return sdf.format(date);
    }
    /**
     * 获取当前日期的下i天 add by huxl
     * @return
     */
    public static String getNextDayWithSpecified(int i){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, i);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 判断给定的日期是否是双休日    是的话 返回true,不是的话 返回false
     * 对于 2月份的判断 貌似有些问题    如 : 2014-2-30 返回true    2月份是木有30号的 不过这个参数在传入的时候就控制了 不影响判断
     * @param sDate
     * @return
     */
    public static boolean isWeekDay(String sDate) {
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");// 日期格式化辅助类
        Date d;
        try {
            d = df.parse(sDate);// 格式化日期
            // 判断是不是双休日
            if (d.getDay() == 0 || d.getDay() == 6){
                //System.out.println("日期:[" + sDate + "] 是双休日");
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) throws Exception
    {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	        Calendar c = Calendar.getInstance();
//
//	        c = Calendar.getInstance();
//	        c.set(Calendar.DAY_OF_WEEK, 1);//本周第一天，以星期日开始
//	        System.out.println(sdf.format(c.getTime()));


        Date nowTime = new Date();
//			Date expDate = DateUtil.strintToDatetime("2013/12/21 14:43:36");
//			System.out.println(nowTime.before(expDate));
//			String sDate = DateUtil.getNextDayWithSpecified(3);
        System.out.println(DateUtil.isWeekDay("2014-2-28"));

        String s = "8:00";
        String s2 = "9:30";
        int hour = getMin(s,s2);
        System.out.println(hour);
    }
}
