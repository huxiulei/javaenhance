package interview.mobilecounter.strategy;

import java.util.Date;
import java.util.Random;

public class VIPCustomer extends Customer {

	public VIPCustomer(String name,Date joinTime){
		super(name,joinTime,1);
		packStrategy = new PackStrategy(1,0,new Rent(200,RentUnit.DAY));
		actionRecords.add(new ActionRecord(name, DateUtil.formatDateToDay(joinTime)+"入网"));
	}

	public void orderPack1(Date month){
/*		if(packStrategy.getOrderedPhonePack()==null || packStrategy.getOrderedPhonePack().getPackType() != 1)
		{		*/
			packStrategy.orderRent(month,new Rent(10000,RentUnit.MONTH));
			packStrategy.orderPhonePack(month,1);
			packStrategy.orderMessagePack(month,1);
			packStrategy.orderDataPack(month,1);
			System.out.println(name+ "订购了套餐1" + "（从" + DateUtil.formatDateToMonth(month) + "开始）" );					
			actionRecords.add(new ActionRecord("订购套餐1",""));	
		//}
	}
	
	public void orderPack2(Date month){
/*		if(packStrategy.getOrderedPhonePack() ==null || packStrategy.getOrderedPhonePack().getPackType() != 2)
		{*/
			packStrategy.orderRent(month,new Rent(20000,RentUnit.MONTH));
			packStrategy.orderPhonePack(month,2);
			packStrategy.orderMessagePack(month,2);
			packStrategy.orderDataPack(month,2);
			System.out.println(name+ "订购了套餐2" + "（从" + DateUtil.formatDateToMonth(month) + "开始）" );					
			actionRecords.add(new ActionRecord("订购套餐2",""));	
		//}
	}
	
	public void randomCancelPack(Date orderedMonth){
		/*
		if(packStrategy.getOrderedPhonePack() ==null ||
		 	packStrategy.getOrderedPhonePack().getPackType() == 0){
		System.out.println(name + "试图退订根本就没有订过的套餐"); return; }
		 */
		packStrategy.orderRent(orderedMonth, new Rent(200, RentUnit.DAY));
		packStrategy.orderPhonePack(orderedMonth, 0);
		packStrategy.orderMessagePack(orderedMonth, 0);
		packStrategy.orderDataPack(orderedMonth, 0);
		System.out.println(name + "退订了" + "套餐" + "（从"
				+ DateUtil.formatDateToMonth(orderedMonth) + "开始）");
		actionRecords.add(new ActionRecord("退定套餐", ""));					
	}
	
	public void randomOrderPack(Date month){
		//如果以前订购过某套餐，现在仍然可以重新订购该套餐
		int randType = (new Random().nextInt(10))%2;
		if(randType == 0){
			orderPack1(month);		
		}else if(randType == 1){
			orderPack2(month);		
		}
	}	
}
