package mytest;

public class People extends Entity<People> {
	public static void main(String[] args) {
		System.out.println("--> " + GenericsUtils.getSuperClassGenricType(People.class));
	}
}
