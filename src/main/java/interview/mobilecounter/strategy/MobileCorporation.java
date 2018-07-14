package interview.mobilecounter.strategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MobileCorporation {
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public MobileCorporation(){
		for(int i=1;i<=15;i++){
			customers.add(
					new CommonCustomer(i+"号普通客户",new Date(114,10,1)) // 创建普通用户 以及入网时间
			);
		}
		
		for(int i=1;i<=5;i++){
			customers.add(			
					new VIPCustomer(i+"号VIP客户",new Date(114,10,1))  // 创建VIP用户 以及入网时间
			);
		}
		System.out.println("程序创建了运营商已有的5个VIP用户和15个普通用户，并设置他们的入网日期为2014年10月1日.");
	}
	
	//模拟某个月的业务活动
	public void simulationBusiness(Date month){
		// 模拟开始前,要清空用户之前月份的所有记录
		for(Customer customer : customers){
			customer.monthBegin();
		}
		
		System.out.println("--------being simulating " + DateUtil.formatDateToMonth(month) + "--------------");	
		for(int i=0;i<500/*30*/;i++){
			// 随机的去做一件事情,做什么方法内部自己决定
			randDoOneThing(month);
		}
		
		System.out.println(DateUtil.formatDateToMonth(month)+"的计费汇总清单：");
		//汇总所有人的账单 ,汇总所有账单的方法 应该在用户类中
		for(int i=0;i<customers.size();i++){
			customers.get(i).countMonthMoney(month);
		}

	}
	/**
	 * 随机调用下面的某一个方法
	 * */
	private void randDoOneThing(Date month){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(month);
		calendar.add(Calendar.MONTH, 1);
		Date monthOfOrderPack = calendar.getTime();			
		/*让orderPack、cancelPack、joinNewCustomer的出现概率是其他操作的1/20。*/			
		int rand = new Random().nextInt(63);
		// callPhone(),sendMessage(),transferData()三个方法, 不区分用户类型
		if(rand>=0 && rand<20){
			callPhone();
		}
		else if(rand>=20 && rand<40){			
			sendMessage();
		}
		else if(rand>=40 && rand<60){			
			transferData();
		}else{
			switch(rand){
				case 60:		
					orderPack(monthOfOrderPack);
					break;
				case 61:					
					cancelPack(monthOfOrderPack);
					break;
				case 62:
					joinNewCustomer(month);
					break;	
			}
		}
	}
	/**
	 * 随机选中一个用户，让其随机拨打的电话时长为1至10分钟不等
	 */
	private void callPhone(){
		int rand = new Random().nextInt(customers.size());
		Customer customer = customers.get(rand);
		int phoneTimes = new Random().nextInt(10) + 1; // 随机打电话的时长
		customer.callPhone(phoneTimes);
		System.out.println(customer + "打了" + phoneTimes + "分钟电话");
	}
	
	/**
	 * 随机选中一个用户，让其随机发送的短信数目为1至10条不等
	 */		
	private void sendMessage(){
		int rand = new Random().nextInt(customers.size());
		Customer customer = customers.get(rand);
		int messageNumbers = new Random().nextInt(10) + 1;
		customer.sendMessage(messageNumbers);
		System.out.println(customer + "发了" + messageNumbers + "条短信");
	}
	/**
	 * 随机选中一个用户，让其随机获取的数据流量为50K，100K，200K，500K，1M
	 */	
	private void transferData(){
		int rand = new Random().nextInt(customers.size());
		Customer customer = customers.get(rand);
		
		int [] dataSize = new int[]{50,100,200,500,1000};
		int randSizeKey = new Random().nextInt(5);
		customer.transferData(dataSize[randSizeKey]);
		System.out.println(customer + "传送了" + dataSize[randSizeKey] + "k数据");		
	}
	/**
	 * 随机选中一个用户，为其随机订购一款套餐
	 * 
	 */	
	private void orderPack(Date month){
		int rand = new Random().nextInt(customers.size());
		customers.get(rand).randomOrderPack(month);
	}
	/**
	 * 随机选中一个用户，并将其已经有的套餐取消
	 */
	private void cancelPack(Date month){
		int rand = new Random().nextInt(customers.size());
		customers.get(rand).randomCancelPack(month);
	}
	
	private void joinNewCustomer(Date month){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(month);
		int maxDay = calendar.getMaximum(Calendar.DAY_OF_MONTH);
		int randDay = new Random().nextInt(maxDay) + 1;
		/*下面的复制过程很重要，不能直接修改date，当然最好是对Calendar直接操作
		 * 这里是为了演示要注意clone而保留的。
		 */ 
		Date joinTime = (Date)month.clone();
		joinTime.setDate(randDay);
		
		int randType = (new Random().nextInt(10))%2; // 定义入网用户的类型概率, VIP用户入网和普通用户入网概率一样
		Customer customer = null;
		if(randType == 0){
			int commonId = IdGenerator.getInstance().nextCommonId();
			customer = new CommonCustomer(commonId+"号普通客户",joinTime);
			customers.add(customer);
		}else{
			int vipId = IdGenerator.getInstance().nextVipId();	
			customer = new VIPCustomer(vipId+"号VIP客户",joinTime);
			customers.add(customer);
		}
		System.out.println(DateUtil.formatDateToDay(joinTime) + "新注册了" + customer);
	}
	
}
