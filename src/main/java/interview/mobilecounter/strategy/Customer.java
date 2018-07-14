package interview.mobilecounter.strategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Customer {
	protected String name;
	//用户入网的时间
	private Date joinTime; // 构造方法的时候 存入
	private int customerType = 0; // 默认普通用户
	protected List<ActionRecord> actionRecords = new ArrayList<ActionRecord>();
	
	//积累的结果只表示当月的所有通话记录，不代表所有历史记录
	private ArrayList phoneRecords = new ArrayList();
	//积累的结果只表示当月的所有短信记录，不代表所有历史记录
	private ArrayList messageRecords = new ArrayList();
	//积累的结果只表示当月的所有数据传送记录，不代表所有历史记录
	private ArrayList dataRecords = new ArrayList();
	protected PackStrategy packStrategy;
	
	/* 每个月开始的时候,就清空之前的所有记录,重新开始  */
	public void monthBegin(){
		phoneRecords.clear();
		messageRecords.clear();
		dataRecords.clear();
		actionRecords.clear();
	}
	
	public Customer(String name,Date joinTime,int customerType){
		this.name = name;
		this.joinTime = joinTime;
		this.customerType = customerType;
	}

	public String toString(){
		return name;
	}
	
	public void callPhone(int times){
		phoneRecords.add(times);
		actionRecords.add(new ActionRecord("打电话",times + "分钟"));
	}
	public void sendMessage(int numbers){
		messageRecords.add(numbers);
		actionRecords.add(new ActionRecord("发短信",numbers + "条"));		
	}
	public  void transferData(int size){
		dataRecords.add(size);
		actionRecords.add(new ActionRecord("传数据",size + "k"));			
	}

	/**
	 * 
	 * @param currentMonth 正在被计费处理的当月的日期，
	 * 注意：日字段设置为1，以便于方法内部计算是否是新用户
	 */	
	public int countMonthMoney(Date currentMonth){
		
		// 比如计算 2014.3月份的账单,如果用户的入网时间是三月份,则会有优惠
		// !joinTime.before(currentMonth)   意思就是 : 入网月份不在当前计算费用的月份之前
		boolean newcome = !joinTime.before(currentMonth);//joinTime.after(currentMonth);
		
		int totalPhone = gatherRecords(phoneRecords); // 其实就是让list中的数据累加
		int totalMessage = gatherRecords(messageRecords);
		int totalData = gatherRecords(dataRecords);
		
		int freePhone = 0;
		int freeMessage = 0;
		int freeData = 0;
		// 如果是新入网用户 则有优惠,计算费用的时候 就要用总的减去优惠的
		if(newcome){
			freePhone = ConfigManager.getNewCustomerFree(customerType,0);
			freeMessage = ConfigManager.getNewCustomerFree(customerType,1);
			freeData = ConfigManager.getNewCustomerFree(customerType,2);	
		}
		
		int chargePhone = totalPhone>freePhone?totalPhone-freePhone:0;
		int chargeMessage = totalMessage>freeMessage?totalMessage-freeMessage:0;
		int chargeData = totalData>freeData?totalData-freeData:0;				
		
		//汇总打印:包括姓名，入网日期，统计月份，通话清单，费用清单，总费用。
		System.out.println(name + "," + DateUtil.formatDateToDay(joinTime) + "入网.");
		System.out.println(" 操作清单如下-----");	
			for(int i=0;i<actionRecords.size();i++){
				System.out.println("  " + actionRecords.get(i));
			}
		System.out.println(" 统计清单如下-----");
		System.out.println("  通话:" + phoneRecords + "分钟:短信" + messageRecords + "条:数据" + dataRecords + "k");
		System.out.println("  通话累计:" + totalPhone + "分钟，减除新开户" + freePhone + "分钟，实际收费" + chargePhone + "分钟");
		System.out.println("  短信累计:" + totalMessage + "条，减除新开户" + freeMessage + "条，实际收费" + chargeMessage + "条");
		System.out.println("  数据累计:" + totalData + "k，减除新开户" + freeData + "k，实际收费" + chargeData + "k");

		
		// 得到有效的套餐计算策略
		ComputeStrategy phoneStrategy = packStrategy.getValidPhonePack(currentMonth);
		ComputeStrategy messageStrategy = packStrategy.getValidMessagePack(currentMonth);
		ComputeStrategy dataStrategy = packStrategy.getValidDataPack(currentMonth);
		
		int sum = 0;
		//VIP用户才有月租金或基本费
		Rent rent = packStrategy.getValidRent(currentMonth);
		// 如果rent不为null 说明是 VIP用户 计算月租费,但是公式统一, 此时计算出来的短信/话费/数据套餐月租均为0
		if(rent != null){
			int rentMoney = rent.coputeRent(joinTime,currentMonth);
			sum +=  rentMoney;
			System.out.println("  月租费或基本费：" + rentMoney + "厘钱");			
		}
		
		sum += phoneStrategy.computeMoney(chargePhone); // 如果是VIP的话  为 0
		sum += messageStrategy.computeMoney(chargeMessage);
		sum += dataStrategy.computeMoney(chargeData/10);
		System.out.println("  总计：" + sum/1000f + "元钱");
		return sum;
	}
	
	private int gatherRecords(ArrayList records){
		int sum = 0;
		for(int i=0;i<records.size();i++){
			sum +=  (Integer)(records.get(i));
		}
		return sum;
	}
	
	public abstract void randomCancelPack(Date month);	
	public abstract void randomOrderPack(Date month);	

}
