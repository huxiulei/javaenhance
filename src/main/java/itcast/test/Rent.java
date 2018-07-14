package itcast.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Rent {
    // 此处省略成员变量和构造方法的定义
    public void coputeRent(Date startTime, Date currentMonth) {
        // 首先应该想到去找开源的日期运算类
        Calendar start = Calendar.getInstance();
        start.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(currentMonth);
        // 将日期设置为当月的最后一天
        end.set(Calendar.DAY_OF_MONTH, 1);
        end.add(Calendar.MONTH, 1);
        end.add(Calendar.DAY_OF_MONTH, -1);
        int days = end.get(Calendar.DAY_OF_MONTH);
        System.out.println("end.get(Calendar.MONTH)="+end.get(Calendar.MONTH));
        System.out.println("start.get(Calendar.MONTH)="+start.get(Calendar.MONTH));
        System.out.println("days ------> " + days);

        // 下面这个判断表示: 如果没有满一个月的话 就计算出具体有多少天
        if (end.get(Calendar.MONTH) == start.get(Calendar.MONTH)) {
            days -= start.get(Calendar.DAY_OF_MONTH) + 1;
            System.out.println("days = " + days);
        }
    }


    public static void main(String[] args) throws Exception{
        Rent rent = new Rent();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2013-8-10");
        Date d2 = sdf.parse("2013-4-3");
        rent.coputeRent(d1,d2);
    }
}
