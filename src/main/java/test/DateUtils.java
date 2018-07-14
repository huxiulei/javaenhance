package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    /**
     *
     * @Title: getWeekDay
     * @Description: 基姆拉尔森计算公式 W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
     *               在公式中d表示日期中的日数，m表示月份数，y表示年数。 注意：在公式中有个与其他公式不同的地方：
     *               把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。
     * @param: @param dateStr
     * @param: @return
     * @return: String
     * @throws
     * @author yangwu
     */
    public static String getWeekDay(String dateStr) {
        if (null != dateStr && !"".equals(dateStr)) {
            int year = Integer.parseInt(dateStr.substring(0, 4));
            int month = Integer.parseInt(dateStr.substring(4, 6));
            int day = Integer.parseInt(dateStr.substring(6, 8));
            if (1 == month) {
                month = 13;
                year = year - 1;
            }
            if (2 == month) {
                month = 14;
                year = year - 1;
            }
            int week = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4
                    - year / 100 + year / 400) % 7;
            String weekStr = "";
            switch (week) {
                case 0:
                    weekStr = "星期一";
                    break;
                case 1:
                    weekStr = "星期二";
                    break;
                case 2:
                    weekStr = "星期三";
                    break;
                case 3:
                    weekStr = "星期四";
                    break;
                case 4:
                    weekStr = "星期五";
                    break;
                case 5:
                    weekStr = "星期六";
                    break;
                case 6:
                    weekStr = "星期日";
                    break;
            }
            if ("星期六".equals(weekStr) || "星期日".equals(weekStr))
                return weekStr;
        }

        return null;
    }

    /**
     *
     * @Title: getDays
     * @Description: 获取限制日期期间所有日期
     * @param: @param dateStr
     * @param: @param days
     * @param: @return
     * @param: @throws Exception
     * @return: List<String>
     * @throws
     * @author yangwu
     */
    public static List<String> getDays(String dateStr, int days)
            throws Exception {
        List<String> strList = null;
        if (null != dateStr && !"".equals(dateStr)) {
            int year = Integer.parseInt(dateStr.substring(0, 4));
            int month = Integer.parseInt(dateStr.substring(4, 6));
            int day = Integer.parseInt(dateStr.substring(6, 8));
            strList = new ArrayList<String>();
            if (dateStr.substring(4, 6).indexOf("02") == 1
                    && Integer.valueOf(dateStr.substring(4, 8)) > 229) {
                throw new Exception("输入日期有误!");
            }

            // 先添加当天日期
            strList.add(dateStr);
            boolean flag = false;
            for (int i = 1; i < days; i++) {
                String str = "";
                // 判断平闰年
                flag = checkPRyear(year);
                day = day + 1;

                if (flag) {// 闰年二月29天
                    // 判断是否是大月
                    flag = checkMaxMonth(month);
                    if (flag) {// 大月
                        if (String.valueOf(day).length() == 2 && day > 31) {
                            month = month + 1;
                            day = 1;
                            if (String.valueOf(month).length() == 2
                                    && month > 12) {
                                year = year + 1;
                                month = 1;
                            }
                        }

                    } else {
                        if (String.valueOf(day).length() == 2 && day > 30) {
                            month = month + 1;
                            day = 1;
                            if (String.valueOf(month).length() == 2
                                    && month > 12)
                                year = year + 1;
                        } else if (String.valueOf(day).length() == 2
                                && day > 29) {
                            month = month + 1;
                            day = 1;
                            if (String.valueOf(month).length() == 2
                                    && month > 12) {
                                year = year + 1;
                                month = 1;
                            }
                        }
                    }
                    str = String.valueOf(year) + String.format("%02d", month)
                            + String.format("%02d", day);

                } else {// 平年二月28天
                    flag = checkMaxMonth(month);
                    if (flag) {// 大月
                        if (String.valueOf(day).length() == 2 && day > 31) {
                            month = month + 1;
                            day = 1;
                            if (String.valueOf(month).length() == 2
                                    && month > 12) {
                                year = year + 1;
                                month = 1;
                            }
                        }
                    } else {
                        if (String.valueOf(day).length() == 2 && day > 30) {
                            month = month + 1;
                            day = 1;
                            if (String.valueOf(month).length() == 2
                                    && month > 12) {
                                year = year + 1;
                                month = 1;
                            }

                        } else if (String.valueOf(day).length() == 2
                                && day > 28) {
                            month = month + 1;
                            day = 1;
                            if (String.valueOf(month).length() == 2
                                    && month > 12) {
                                year = year + 1;
                                month = 1;
                            }
                        }
                    }
                    str = String.valueOf(year) + String.format("%02d", month)
                            + String.format("%02d", day);
                }

                strList.add(str);
            }
        }
        return strList;
    }

    public static String getWeekDay(List<String> strList) {
        if (null != strList && strList.size() > 0) {
            for (String dateStr : strList) {

                int year = Integer.parseInt(dateStr.substring(0, 4));
                int month = Integer.parseInt(dateStr.substring(4, 6));
                int day = Integer.parseInt(dateStr.substring(6, 8));
                if (1 == month) {
                    month = 13;
                    year = year - 1;
                }
                if (2 == month) {
                    month = 14;
                    year = year - 1;
                }
                int week = (day + 2 * month + 3 * (month + 1) / 5 + year + year
                        / 4 - year / 100 + year / 400) % 7;
                String weekStr = "";
                switch (week) {
                    case 0:
                        weekStr = "星期一";
                        break;
                    case 1:
                        weekStr = "星期二";
                        break;
                    case 2:
                        weekStr = "星期三";
                        break;
                    case 3:
                        weekStr = "星期四";
                        break;
                    case 4:
                        weekStr = "星期五";
                        break;
                    case 5:
                        weekStr = "星期六";
                        break;
                    case 6:
                        weekStr = "星期日";
                        break;
                }
                if ("星期六".equals(weekStr) || "星期日".equals(weekStr))
                    return weekStr;

            }
        }

        return null;
    }

    /**
     *
     * @Title: checkPRyear
     * @Description: 判断平闰年 1。能被4整除而不能被100整除。（如2004年就是闰年,1900年不是）
     *               2。能被400整除。（如2000年是闰年）
     * @param: @param year
     * @param: @return
     * @return: boolean
     * @throws
     * @author yangwu
     */
    public static boolean checkPRyear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
            return true;
        else
            return false;
    }

    /**
     *
     * @Title: checkMaxMonth
     * @Description: 判断是否是大月
     * @param: @param month
     * @param: @return
     * @return: boolean
     * @throws
     * @author yangwu
     */
    public static boolean checkMaxMonth(int month) {
        int[] months = { 1, 3, 5, 7, 8, 10, 12 };
        boolean flag = false;
        for (int i : months) {
            if (month == i) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = f.format(date);
        return sDate;
    }

    /**
     * 获取当年的第一天
     *
     * @param year
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @param year
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year
     *            年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year
     *            年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    // 判断给定日期是周几
    public static int getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }


    public static void main(String[] args) throws Exception {
        // System.out.println(Integer.parseInt("20140203".substring(4, 6)));
        // System.out.println(getWeekDay("20140213"));
        // System.out.println(checkPRyear(2014));
        // System.out.println(checkMaxMonth(9));
        /*
         * System.out.println(String.format("%02d", 12));
         * System.out.println(getDays("20140227", 5).size()); for (String string
         * : getDays("20140625", 6)) { System.out.println(string); }
         */

        System.out.println(getWeekOfDate(DateUtil.strintToDate("2014-07-06")));
    }
}
