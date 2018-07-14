package interview.mobilecounter.strategy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class ConfigManager {
	private static Properties config = new Properties();
	static{
		InputStream ips = ConfigManager.class.getResourceAsStream("/conf.properties");
		try {
			config.load(ips);
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	private static String makePrefix(int customerType,int packType,int businessType){
		// 此处类型少的话  可以这么用三元运算符来判断,但是如果有很多类型的话,可以使用数组(ppt上)
		String customerTitle = customerType==0?"common":"vip";
		String packTitle = packType==0?"normal":("pack"+packType);
		String businessTitle = businessType==0?"phone":businessType==1?"message":"data";
		return customerTitle + "." + packTitle + "." + businessTitle;
	}
	
	private static int getNumber(String key){
		String value = config.getProperty(key);
		try{
			return Integer.parseInt(value);
		}catch(Exception e){
			return 0;
		}		
	}
	
	public static int getPrice(int customerType,int packType,int businessType){
		return getNumber(makePrefix(customerType,packType,businessType)+".price");
	}
	
	public static int getFree(int customerType,int packType,int businessType){
		return getNumber(makePrefix(customerType,packType,businessType)+".free");
	}
	
	public static int getRent(int customerType,int packType,int businessType){
		return getNumber(makePrefix(customerType,packType,businessType)+".rent");
	}
	
	public static int getNewCustomerFree(int customerType,int businessType){
		String[] businesses = {"phone","message","data"};
		return getNumber((customerType==0?"common":"vip")+".new." + businesses[businessType] + ".free");
	}
}
