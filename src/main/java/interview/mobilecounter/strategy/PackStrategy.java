package interview.mobilecounter.strategy;

import java.util.Date;

public class PackStrategy {
	private int customerType;
	private int packType = 0; // 默认没有订购任何
	private ComputeStrategy currentStrategies[] = new ComputeStrategy[3]; // 记录当前的套餐 依次为 话费/短信/数据
	private Rent rent;
	
	// 记录用户预定的套餐  依次为 话费/短信/数据
	private  OrderedStrategyHolder<ComputeStrategy> orderedStrategies[] = new OrderedStrategyHolder[]{
			  new OrderedStrategyHolder<ComputeStrategy>(),  
			  new OrderedStrategyHolder<ComputeStrategy>(),
			  new OrderedStrategyHolder<ComputeStrategy>(),
	};
	private OrderedStrategyHolder<Rent> orderedRent = new OrderedStrategyHolder<Rent>();
	
	public PackStrategy(int customerType,int packType,Rent rent){
		this.customerType = customerType;
		this.packType = packType;
		this.rent = rent;
		for(int i=0;i<3;i++){
			currentStrategies[i] = new ComputeStrategy(customerType, packType,i);
		}
		
	}
	
	public Rent getValidRent(Date month){
		Rent validRent = orderedRent.getValidComputeStrategy(month); 
		return validRent==null?rent:validRent;
	
	}	
	
	public void orderRent(Date orderedMonth,Rent rent){
		Rent oldRent = orderedRent.order(orderedMonth, rent);
		if(oldRent != null){
			this.rent = oldRent;
		}	
	}

	public void cancelRent(Date orderedMonth,Rent rent){
		orderRent(orderedMonth,null);
	}	
	
	public ComputeStrategy getValidPhonePack(Date month){
		ComputeStrategy computeStrategy = (ComputeStrategy)orderedStrategies[0].getValidComputeStrategy(month); 
		return computeStrategy==null?currentStrategies[0]:computeStrategy;
	}

	public ComputeStrategy getValidMessagePack(Date month){
		ComputeStrategy computeStrategy = (ComputeStrategy)orderedStrategies[1].getValidComputeStrategy(month); 
		return computeStrategy==null?currentStrategies[1]:computeStrategy;
	}

	public ComputeStrategy getValidDataPack(Date month){
		ComputeStrategy computeStrategy = (ComputeStrategy)orderedStrategies[2].getValidComputeStrategy(month); 
		return computeStrategy==null?currentStrategies[2]:computeStrategy;
	}	
	
	public void orderPhonePack(Date orderedMonth,int packType){
		ComputeStrategy oldComputeStrategy = orderedStrategies[0].order(orderedMonth, new ComputeStrategy(customerType, packType, 0));
		if(oldComputeStrategy != null){
			this.currentStrategies[0] = oldComputeStrategy;
		}

	}

	public void orderMessagePack(Date orderedMonth, int packType){	
		ComputeStrategy oldComputeStrategy = orderedStrategies[1].order(orderedMonth, new ComputeStrategy(customerType, packType, 1));
		if(oldComputeStrategy != null){
			this.currentStrategies[1] = oldComputeStrategy;
		}
	}

	public void orderDataPack(Date orderedMonth, int packType){
		ComputeStrategy oldComputeStrategy = orderedStrategies[2].order(orderedMonth, new ComputeStrategy(customerType, packType, 2));
		if(oldComputeStrategy != null){
			this.currentStrategies[2] = oldComputeStrategy;
		}
	}

	/* 取消套餐就是订购为0的套餐, 0代表无套餐    既基准资费 */
	public void cancelPhonePack(Date orderedMonth){
		orderPhonePack(orderedMonth, 0);
	}
	/* 取消套餐就是订购为0的套餐, 0代表无套餐  既基准资费 */
	public void cancelMessagePack(Date orderedMonth){
		orderMessagePack(orderedMonth, 0);	
	}
	/* 取消套餐就是订购为0的套餐, 0代表无套餐  既基准资费 */
	public void cancelDataPack(Date orderedMonth){
		orderDataPack(orderedMonth, 0);
	}
	
/*	public PhoneComputeStrategy getOrderedPhonePack(){
		PhoneComputeStrategy phoneHolderStrategy = (PhoneComputeStrategy)phoneOrderedStrategyHolder.getOrderedStrategy(); 
		return phoneHolderStrategy;
	}

	public MessageComputeStrategy getOrderedMessagePack(){
		MessageComputeStrategy messageHolderStrategy = (MessageComputeStrategy)messageOrderedStrategyHolder.getOrderedStrategy(); 
		return messageHolderStrategy;
	}
	
	public DataComputeStrategy getOrderedDataPack(){
		DataComputeStrategy dataHolderStrategy = (DataComputeStrategy)dataOrderedStrategyHolder.getOrderedStrategy(); 
		return dataHolderStrategy;
	}	*/
}
