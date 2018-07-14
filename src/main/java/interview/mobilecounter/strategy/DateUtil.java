package interview.mobilecounter.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String formatDateToMonth(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		String result = sdf.format(date);
		return result;
	}
	
	public static String formatDateToDay(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String result = sdf.format(date);
		return result;
	}	
}
