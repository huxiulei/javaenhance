package interview.mobilecounter.strategy;

import java.util.Date;

public class OrderedStrategyHolder<T> {
	private Date orderedMonth;
	private T computeStrategy;   // 普通用户 VIP用户 通用
	
	public T order(Date orderedMonth,T computeStrategy){
		T oldComputeStrategy = null;
		if(this.orderedMonth!=null && this.orderedMonth.before(orderedMonth)){
			oldComputeStrategy = this.computeStrategy;
		}
		this.orderedMonth = orderedMonth;
		this.computeStrategy = computeStrategy;

		return oldComputeStrategy;
	}
	
	public T getValidComputeStrategy(Date month){
		
		// 此处就是用来判断 计算费用的时候  到底用哪个计算策略      
		if(this.orderedMonth!=null && !month.before(orderedMonth)){
			return computeStrategy;
		}
		return null;
	}
}
