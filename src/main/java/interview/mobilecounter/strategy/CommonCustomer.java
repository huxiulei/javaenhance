package interview.mobilecounter.strategy;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @ClassName:     CommonCustomer.java
 * @Description:   TODO 谁拥有数据,谁就具有对数据的操作权限  所以定套餐和取消套餐 都是在用户身上
 * @author         huxl
 * @version        V1.0  
 * @Date           2015年6月30日 上午11:21:15
 */
public class CommonCustomer extends Customer {

	public CommonCustomer(String name,Date joinTime){
		super(name,joinTime,0);
		packStrategy = new PackStrategy(0,0,null);
		actionRecords.add(new ActionRecord(name, DateUtil.formatDateToDay(joinTime)+"入网"));		
	}
	
	public void randomCancelPack(Date orderedMonth){
		int rand = new Random().nextInt(3);
		switch(rand){
		case 0:
			/*if(packStrategy.getOrderedPhonePack() == null || packStrategy.getOrderedPhonePack().getPackType() == 0){
				System.out.println(name + "试图退订根本就没有订过的电话套餐");			
				return;
			}*/			
			packStrategy.cancelPhonePack(orderedMonth);
			System.out.println(name + "退订了" + "电话套餐" + "（从" + DateUtil.formatDateToMonth(orderedMonth) + "开始）");	
			actionRecords.add(new ActionRecord("退订电话套餐",""));	
			break;
		case 1:
			/*if(packStrategy.getOrderedMessagePack() ==null || packStrategy.getOrderedMessagePack().getPackType() == 0){
				System.out.println(name + "试图退订根本就没有订过的短信套餐");			
				return;
			}*/			
			packStrategy.cancelMessagePack(orderedMonth);	
			System.out.println(name + "退订了" + "短信套餐" + "（从" + DateUtil.formatDateToMonth(orderedMonth) + "开始）");	
			actionRecords.add(new ActionRecord("退订短信套餐",""));
			break;
		case 2:
/*			if(packStrategy.getOrderedDataPack()==null || packStrategy.getOrderedDataPack().getPackType() == 0){
				System.out.println(name + "试图退订根本就没有订过的数据套餐");			
				return;
			}	*/		
			packStrategy.cancelDataPack(orderedMonth);	
			System.out.println(name + "退订了" + "数据套餐" + "（从" + DateUtil.formatDateToMonth(orderedMonth) + "开始）");	
			actionRecords.add(new ActionRecord("退订数据套餐",""));
			break;
		}
	}
	
	public void randomOrderPack(Date month){
		int rand = new Random().nextInt(3);
		switch(rand){
		case 0:
			//if(packStrategy.getOrderedPhonePack()==null || packStrategy.getOrderedPhonePack().getPackType() == 0){
				packStrategy.orderPhonePack(month,1);
				System.out.println(name + "订购了" + "电话套餐" + "（从" + DateUtil.formatDateToMonth(month) + "开始)");		
				actionRecords.add(new ActionRecord("定电话套餐",""));	
			//}
			break;
		case 1:
			//if(packStrategy.getOrderedMessagePack() == null || packStrategy.getOrderedMessagePack().getPackType() == 0){			
				packStrategy.orderMessagePack(month,1);	
				System.out.println(name + "订购了" + "短信套餐" + "（从" + DateUtil.formatDateToMonth(month) + "开始)");		
				actionRecords.add(new ActionRecord("定短信套餐",""));		
			//}
			break;
		case 2:
			//if(packStrategy.getOrderedDataPack() == null || packStrategy.getOrderedDataPack().getPackType() == 0){			
				packStrategy.orderDataPack(month,1);	
				System.out.println(name + "订购了" + "数据套餐" + "（从" + DateUtil.formatDateToMonth(month) + "开始)");	
				actionRecords.add(new ActionRecord("定数据套餐",""));	
			//}
			break;
		}
	}		
}
