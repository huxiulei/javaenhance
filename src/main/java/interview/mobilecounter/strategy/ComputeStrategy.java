package interview.mobilecounter.strategy;

/**
 * 如果VIP客户和common客户的不同套餐的每种服务费的算法公式区别很大，
 * 而不是相同公式仅仅参数值不同的情况 那就需要为每种客户的每种套餐的每种服务方式各设计一个类。
 * 下面是用一个类中有两个属性分别记住它是哪种客户和收费策略，然后根据这两个属性在properties文件
 * 中去读取价格，甚至还可以再增加一个属性，来记住是哪种服务方式。
 * 这里为了演示每种服务分别对应一种计算机策略的效果，所以服务方式没有采用属性，而是采用子类方式来做。
 * @author IBM
 *
 */
public class ComputeStrategy {
	private int customerType;
	private int packType;
	private int businessType;
	private String businessName = "";
	
	public ComputeStrategy(int customerType, int packType, int businessType) {
		this.customerType = customerType;
		this.packType = packType;
		this.businessType = businessType;
		switch(businessType){
			case 0:businessName = "电话";break;
			case 1:businessName = "短信";break;
			case 2:businessName = "数据";break;	
		}		
	}
	
	public int computeMoney(int quantity){
		int price = ConfigManager.getPrice(customerType, packType,businessType);

		int freeQuantity = ConfigManager.getFree(customerType, packType,businessType);
		int chargeQuantity = quantity - freeQuantity;
		if(chargeQuantity < 0){
			chargeQuantity = 0;
		}
		// 如果是VIP用户  该值没有配置  则为 0
		int phoneBaseMoney = ConfigManager.getRent(customerType, packType,businessType);	
		System.out.print(businessName + "功能费：" + phoneBaseMoney + "厘钱,");				
		int fee = price * chargeQuantity;
		System.out.println(businessName + "计价费：" + quantity + "-" + freeQuantity + "=" + chargeQuantity + "," 
					+ chargeQuantity + "*" + price + "=" + fee +"厘钱");
		return phoneBaseMoney + fee;
	}
}
