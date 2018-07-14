package interview.mobilecounter.strategy;

public class ActionRecord {
	private String name;
	private String value;
	public ActionRecord(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String toString(){
		return name + ":" + value;
	}
}
