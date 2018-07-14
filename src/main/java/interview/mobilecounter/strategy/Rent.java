package interview.mobilecounter.strategy;

import java.util.Calendar;
import java.util.Date;

public class Rent {
	private int price;
	private RentUnit unit = RentUnit.MONTH;
	
	public Rent(int price,RentUnit unit){
		this.price = price;
		this.unit = unit;
	}
	
	public int coputeRent(Date startTime,Date currentMonth){
		//首先应该想到去找开源的日期运算类		
		if(unit == RentUnit.DAY){
			Calendar start = Calendar.getInstance();
			start.setTime(startTime);

			Calendar end = Calendar.getInstance();
			end.setTime(currentMonth);
			//将日期设置为当月的最后一天
			end.set(Calendar.DAY_OF_MONTH, 1);
			end.add(Calendar.MONTH, 1);
			end.add(Calendar.DAY_OF_MONTH, -1);
			
			int days =  end.get(Calendar.DAY_OF_MONTH) ;
			if(end.get(Calendar.MONTH) == start.get(Calendar.MONTH)){
				days -= start.get(Calendar.DAY_OF_MONTH) + 1;				
			}

			return  price*days;
		}
		else{
			return price;
		}
	}	
}
