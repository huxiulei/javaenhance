package interview.mobilecounter.strategy;

public class IdGenerator {
	private IdGenerator(){
		
	}
	
	private static IdGenerator instance = new IdGenerator();
	public static IdGenerator getInstance(){
		return instance;
	}
	
	private int lastCommonId = 15;
	private int lastVipId = 5;
	
	public synchronized int nextCommonId(){
		return ++lastCommonId;
	}
	
	public synchronized int nextVipId(){
		return ++lastVipId;
	}
}
