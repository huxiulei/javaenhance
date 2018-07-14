package itcast.day3.aopframework;

import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

public class AopFrameworkTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		InputStream ips = AopFrameworkTest.class.getResourceAsStream("config.properties");
		Properties p = new Properties();
		p.load(ips);
		Object bean = new BeanFactory(ips).getBean("xxx");
		System.out.println(bean.getClass().getName());
		((Collection)bean).clear();
	}

}
