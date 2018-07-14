package interview.mobilecounter.strategy;

import java.util.Calendar;
import java.util.Date;

/*
 * 由于电信和银行实际环境的复杂性和产品一旦上线运行后对错误的容忍度几乎为0，所以，电信和银行的项目在做完后都不能直接上线测试运行，
 * 而是要通过编写非常完善的模拟程序来进行测试，确保万无一失后再实际上线运行，所以，为电信和银行项目编写模拟程序和比对检查程序运行的结果就非常重要了。
 */
public class MainClass {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// 此处MobileCorporation一创建的时候,就会产生15个普通用户和5个vip用户,构造函数中
		MobileCorporation corp = new MobileCorporation();
		//设置要模拟的起始月份
		Date month = new Date(114,0,1);	
		System.out.println("程序开始模拟从2014年1月1日开始，连续15个月的运行情况.");		
		//总共模拟15个连续的月份
		for(int i=0;i<15/*3*/;i++){
			corp.simulationBusiness(month);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(month);
			calendar.add(Calendar.MONTH, 1);
			month = calendar.getTime();
		}
	}
}
