package test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestString {

	@Test
	public void main() {
		String a = "ab";
		String bb = "b";
		// String b = "a" + "b"; //true
		String b = "a" + bb; // false
		System.out.println((a == b));
		String idCard = "411503199002102719";
		String pwd = (idCard.substring(idCard.length() - 6)).toUpperCase();
		System.out.println(pwd);

		String str = "";
		String abc = str;
		str = "xxxx";
		System.out.println(abc);
		// String str = null;
		System.out.println(String.valueOf(str));
		if (StringUtils.isNotBlank(str)) {
			System.out.println("111111");
		} else {
			System.out.println("为空");
		}

		/**
		 * 我们期望的结果是将数组中的元素通过Arrays.asList转换到集合类当中,
		 * 但事与愿违,我们只将数组本身增加了进入,并没有将数组内的值分拆分开来, 此时若然对集合List增加了泛型就会在编译期间给出错误的提示,
		 * 或将数组本身改变成Integer就可以解决问题
		 */
		// int[] nums = new int[] { 1, 2, 3, 4, 5 };
		// List list = Arrays.asList(nums);
		// System.out.println(list.size());
		// 此时输出的size为1

		System.out.println(10.00 - 9.60);

	}

	/**
	 * Random r = new Random(50); 如果这样了,那么每次产生的随机数就一样了 因为随机种子固定了
	 */
	@Test
	public void test1() {
		Random r = new Random();
		for (int i = 1; i < 4; i++) {
			System.out.println("第" + i + "次" + r.nextInt());

		}
	}


	@Test
	public void test3() {
		String str = "好是好!";
		String result = StringUtils.removeStartIgnoreCase(str, "好");
		System.out.println(result);
	}

	@Test
	public void test4() {
		String str = "好是好!";
		byte[] b;
		try {
			b = str.getBytes("utf-8");
			System.out.println(new String(b));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	// 快速取出int数组的最大值
	@Test
	public void test5() {
		int[] data = { 71, 5, 7, 1, 4, 2, 8, 89 };
		int max = data[0];
		// 此处可以使用clone 不要直接对原数组进行排序,达到进来不改变员数组的目的
		Arrays.sort(data.clone());
		System.out.println(data[data.length - 1]);
		for (int i = 0; i < data.length; i++) {
			max = max > data[i] ? max : data[i];
		}
		System.out.println(max);
	}

	// 快速取出int数组的最大值,排除重复的 使用TreeSet实现
	// TreeSet 能自动排序去重
	@Test
	public void test6() {
		Integer[] data = { 71, 5, 7, 1, 4, 2, 8, 89, 71, 89 };
		int[] data2 = { 71, 5, 7, 1, 4, 2, 8, 89, 71, 89 };
		List s = Arrays.asList(data2); // 此处不能传入int[] 要转为Integer[] 包装类
		System.out.println(s.size());
		List<Integer> list = Arrays.asList(data);
		TreeSet<Integer> treeList = new TreeSet<Integer>(list);
		System.out.println(treeList);
		System.out.println(treeList.lower(treeList.last()));
	}

	@Test
	public void test7() {
		String s = "\u676d\u5dde\u5e02";
		System.out.println(s);
	}

	@Test
	public void test8() {
		int count = 5;
		count += count++;
		System.out.println(count);
	}

	@Test
	public void test9() {
		A: for (int a = 0; a < 100; a++) {
			B: for (int b = 0; b < 100; b++) {
				C: for (int c = 0; c < 100; c++) {
					System.out.println(a + "-" + b + "-" + c);
					break A;
				}
			}
		}
	}

	// 解决两个long类型相乘 溢出的问题
	@Test
	public void test10() {
		long a = Long.MAX_VALUE;
		long b = Long.MAX_VALUE;

		BigDecimal ba = new BigDecimal(String.valueOf(a));
		BigDecimal bb = new BigDecimal(String.valueOf(b));
		BigDecimal bc = ba.multiply(bb);
		System.out.println(String.valueOf(a));
		System.out.println(String.valueOf(b));
		System.out.println(bc);
	}

	@Test
	public void test11() {
		List<String> strs = ManagementFactory.getRuntimeMXBean()
				.getInputArguments();
		for (String str : strs) {
			System.out.println(str);
		}
	}

	@Test
	public void test12() {
		// 23-2-6-5
		// 1.如果当前浏览的id已经在浏览历史里了,我们要把移到最前面
		// 2.如果浏览历史里已经达到了10个产品了,我们需要把最选进入的元素删除
		String cookieValue = "1-2-3-4-5-6-7-8-9-10";
		Integer currentProductId = 16;
		LinkedList<Integer> productids = new LinkedList<Integer>();
		if (cookieValue != null && !"".equals(cookieValue.trim())) {
			String[] ids = cookieValue.split("-");
			for (String id : ids) {
				productids.offer(new Integer(id.trim()));
			}
			if (productids.contains(currentProductId))
				productids.remove(currentProductId);
			if (productids.size() >= 10)
				productids.poll();
		}
		productids.offer(currentProductId);
		StringBuffer out = new StringBuffer();
		for (Integer id : productids) {
			out.append(id).append('-');
		}
		out.deleteCharAt(out.length() - 1);
		System.out.println(out);
	}

	// 数字的格式化, 精度要求不是很高的话 可以使用
	@Test
	public void test13() {
		double tax = 0;
		final NumberFormat format = NumberFormat.getNumberInstance();
		format.setRoundingMode(RoundingMode.HALF_UP);
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		System.out.println(format.format(tax));
	}

	// 计算某个操作的时间差 要用nanoTime 而不是System.currentTimeMillis() 这个会出现负数
	@Test
	public void test14() {
		long startTime = System.nanoTime();
		// ... the code being measured ...
		long estimatedTime = System.nanoTime() - startTime;
		System.out.println("estimatedTime = " + estimatedTime);
	}

	@Test
	public void test15() {
		int i = 6, j = 8, k = 10, m = 7;
		// k = k++; 如果这样的话 下面判断用m>k 打印出来的是11
		if (!(i > j || m > k++)) {
			System.out.println("进入");
			k++;
		}
		System.out.println(k);
	}

	@Test
	public void test16() {
		System.out.println(Math.floor(10.2));
		Locale[] l = Locale.getAvailableLocales();
		// for(Locale ll : l){
		// System.out.println(ll.getCountry());
		// }
		Locale locale = new Locale("de", "CH");
		System.out.println("德文地区的ISO语言代码：" + locale.getLanguage());
		System.out.println("德文中的“德文”：" + locale.getDisplayLanguage(locale));
		System.out.println("德文（瑞士）的Locale对象按操作系统的" + "默认本地方式显示的名称为："
				+ locale.getDisplayName());
		System.out.println("德文（瑞士）的Locale对象按德文（瑞士）的" + "本地方式显示的信息为："
				+ locale.getDisplayName(locale));
		System.out.println(DateFormat.getDateTimeInstance().format(new Date()));

		DateFormat d = DateFormat.getDateInstance(DateFormat.MEDIUM,
				Locale.CHINESE);
		System.out.println(d.format(new Date()));
	}

	@Test
	public void test17() {
		System.out.println("使用默认的本地信息、默认时区和" + "默认的时间/日期模式格式化当前日期：");
		DateFormat df = DateFormat.getDateTimeInstance();
		Date date = new Date();
		System.out.println(df.format(date));
		System.out.println("===========================================");
		System.out.println("将格式化的日期结果追加到指定字符串的后面" + "并跟踪月份在结果字符串中的索引位置：");
		StringBuffer sb = new StringBuffer("it:");
		FieldPosition fp = new FieldPosition(DateFormat.MONTH_FIELD);
		String dateString = df.format(date, sb, fp).toString();
		System.out.println(dateString);
		System.out.println("月份部分的第一个字符在整个结果字符串中" + "的索引位置为："
				+ fp.getBeginIndex() + "；月份部分的最后一个字符后面的字符在整个字符串中的索引为："
				+ fp.getEndIndex());
		System.out.println("===========================================");
		df.setTimeZone(TimeZone.getTimeZone("Pacific/Noumea"));
		System.out.println("使用Pacific/Noumea时区格式化当前日期：" + df.format(date));
	}
	
	@Test
	public void test18() throws Exception{
		NumberFormat nf1 = NumberFormat.getInstance();
		System.out.println("使用默认的本地信息，只解析数值字符串12345.67"
							+ "的整数部分：");
		nf1.setParseIntegerOnly(true);
		System.out.println(nf1.parse("12345.67"));
		System.out.println("===========================================");
		System.out.println("解析包含非数字字符的数值字符串1234A56.7a："
							+ nf1.parse("1234A56.7a"));
		System.out.print("从字符串中的第三个字符开始解析数值字符串12345：");
		ParsePosition pp = new ParsePosition(2);
		System.out.println(nf1.parse("12345", pp));
		System.out.print("使用德文本地信息解析百分数字符串125,3%：");
		NumberFormat nf2 = NumberFormat.getPercentInstance(Locale.GERMAN);
		System.out.println(nf2.parse("125,3%"));
	}
	@Test
	public void test19() throws Exception{
		String pattern = "On {0}, {1} destroyed {2} houses and caused "
			+ "{3} of damage.";
		MessageFormat msgFmt = new MessageFormat(pattern);

		//准备参数数组
		String datetimeString = "Jul 3, 1998 12:30 PM";
		Date date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, 
			DateFormat.SHORT, Locale.US).parse(datetimeString);
		String event = "a hurricance";
		Object [] msgArgs = {date, event, new Integer(99), new Double(1E7)};

		//执行格式化操作
		String result = msgFmt.format(msgArgs);
		System.out.println(result);
	}
	
	
	@Test
	public void test20(){
//		Double d = 12312313.00d;
//		System.out.println(d.intValue());
		Pattern p = Pattern.compile("1|(0\\.[0-9]{1,3})");
		Matcher m = p.matcher("0.1111111");
		boolean b = m.matches();
		System.out.println(b);
	}
	@Test
	public void test21(){
		int count = Runtime.getRuntime().availableProcessors();
		System.out.println(count);
		Integer i = 10;
		Long s = i.longValue();
	}
	@Test
	public void test22(){
		Calendar c = Calendar.getInstance();
		System.out.println("你好\b");
		byte b = 127; // 如果128以上就编译不通过了  其超过了byte类型的取值范围
		int i = 255;
		b = (byte)i;
		System.out.println(b);
		System.out.println('a' + 1); // 'a'是一个char类型  所以与1相加的 结果 98
		System.out.println("" + 'a' + 1); // 'a'自动转为string  结果为 a1
		System.out.println('a' + 1 + ""); // 现变成98后,与字符串相加,转为字符串的 98输出
		
		System.out.println(Integer.toBinaryString(123)); // 转为2进制数
	}
	
	@Test
	public void test23(){ // 
		// 定义跳出标签, 用于跳出循环体
		st:while(true){
			st1:while(true){
				break st1;
			}
			break st;
		}
	}
	@Test
	public void test24(){ 
	       int ia[]=new int[]{1,2,3,4,5};
	       //int ib[]=new int[]{9,8,7,6,5,4,3}; 
	       int ib[]=new int[]{9,8,7};
	       
	       for(int j=0;j<ib.length;j++)
	           System.out.print(ib[j]);
				System.out.println();
				
	       System.arraycopy(ia,0,ib,0,3);
	       // 复制源数组(ia)中从下标0开始的3个元素到目的数组[1,2,3]，从下标0的位置开始存储[存储到ib]。
	       for(int i=0;i<ia.length;i++)
	           System.out.print(ia[i]);
			   System.out.println();
	       
	       for(int j=0;j<ib.length;j++)
	           System.out.print(ib[j]);
				System.out.println();
	}
	
	@Test
	public void test25(){
		// Arrays.equals方法的使用,比较两个数组
		int[] a1 = new int[]{1,2,3};
		int[] a2 = new int[]{1,2,3,4};
		boolean b = Arrays.equals(a1,a2);
		System.out.println(b);
	}
	
	@Test
	public void test26(){
		String str = "8:00";
		String str2 = "9:30";
		Long s = 90l;
		Long t = 60l;
		System.out.println(s == t);
		
	}
	
	@Test
	public void test27(){
		// 打印jvm虚拟机的所有参数信息
		Properties p = System.getProperties();
		//System.out.println(p);
		Enumeration s = p.propertyNames();
		while(s.hasMoreElements()){
			String key = (String) s.nextElement();
			System.out.println(key + "=" + p.getProperty(key));
		}
		
		// runtime使用
		Runtime r = Runtime.getRuntime();
		try {
			Process pro = r.exec("notepad D:\\workspace85\\BaBasport\\src\\junit\\test\\TestString.java");
			Thread.sleep(5000);
			//r.exit(0);
			pro.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test28(){
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DATE)+"日");
		c.add(Calendar.DAY_OF_YEAR, 200);
		System.out.println(c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DATE)+"日");
		System.out.println();
	}
	
	@Test
	public void test29(){
//		String str = "258";
//		System.out.println(str.split(",").length);
		int i = 45;
		int d = 60;
		double dd;
		dd = 150.0/60.0;
		System.out.println(dd);
	}
	@Test
	/**
	 * queue.element()    : 从队列的头部查找元素,如果队列为空的话  抛异常 java.util.NoSuchElementException
	 * queue.peek()       :从队列的头部查找元素,如果队列为空的话 返回null
	 * 
	 * queue.remove()     :从队列中删除第一个元素(head),如果为空的话  抛异常: java.util.NoSuchElementException
	 * queue.poll()       :从队列中删除第一个元素(head),如果为空的话  返回null
	 * 
	 * queue.add()        :在一个有大小限制的队列里,如果队列已满  使用add添加 会抛出uncheck异常
	 * queue.offer()      :在一个有大小限制的队列里,如果队列已满  会返回false
	 */
	public void test30(){
		Queue queue = new LinkedList();
//		queue.offer("One");
//		queue.offer("Two");
//		queue.offer("Three");
//		queue.offer("Four");
		System.out.println("Head of queue is: " + queue.poll());
		System.out.println(queue);
	}
	
	@Test
	public void test31(){
		Date now = new Date();
		long tmp = now.getTime() - 60000 * 4;
		Date compare = new Date(tmp);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(compare));
	}
	@Test
	public void test32(){
		System.out.println(6&3);   // 2
		System.out.println(6|3);   // 7
		System.out.println(6^3);   // 5
		
		/*
		3000米长的绳子，每天减一半，问，需要多少天，绳子会小于5米？
		思路：
		1,绳子长度在变化，定义变量记录绳子长度的变化，而且这个变量的初始化值时3000.
		  既然不考虑小数部分，所以可以定义int类型变量 
		2,多少天,不确定,是一个变量.
		3,每天减一半，这时对绳子长度的运算，只要可以减一半，就算一天。
			这就让我想到了一个小思想，就是传说中的计数器思想。
		4,天数的递增，减一半的动作在重复的执行，使用循环结构。
		5,直到小于5米，重复动作结束。这就是循环的条件。 
		*/
		int days = 0;
		for(int x=3000;x>=5;x=x/2){
			days++;
		}
		System.out.println("days = " + days);
	}
	
	/**
	 * default可以放到前面也可以放到后面, 最后的break可以省略
	 */
	@Test
	public void test33(){
		int week = 3;
		switch(week)
		{
			default:
				System.out.println("这里是默认");
				break;
			case 1:
				System.out.println(week+"对应的是星期一");
				break;
			case 2:
				System.out.println(week+"对应的是星期二");
			break; // 可以省略该break
		}
	}
	
	@Test
	public void test34(){
		int a=3,b=5;
		System.out.println("a="+a+",b="+b);
		a = a ^ b; //a = 3 ^ 5;
		b = a ^ b; //b = (3^5)^5; b = 3;
		a = a ^ b; //a = (3^5)^3; a = 5;
		System.out.println("a="+a+",b="+b);
		;
		;
	}
	@Test
	public void test35(){
/*		int x = 1;
		for(System.out.println("a");x<3; System.out.println("c"))
		{
			System.out.println("d");
			x++;
		}*/
		int count = 0;
		for(int i=1;i<=100;i++){
			if(i%6 == 0){
				count ++;
			}
		}
		System.out.println("for: " + count);
		
		int j = 1;
		int num = 0;
		while(j<=100){
			if(j%6 == 0){
				num ++;
			}
			j++;
		}
		System.out.println("while: " + num);
	}
	
	@Test
	public void test36(){
/*		int num = 9;
		for(int x=1; x<=num; x++)
		{
			for(int y=1; y<=x; y++)
			{
				System.out.print(y+"*"+x+"="+y*x+"\t");
			}
			System.out.println();
		}*/
		
		for(int x=1; x<=5; x++)
		{
			for(int y=1; y<x; y++)
			{
				System.out.print(" ");
			}
			for(int z=x; z<=5; z++)
			{
				System.out.print("* ");
			}
			System.out.println();
		}		
	}
	
	
	@Test
	public void test37(){
/*		xiaoqiang:for (int x=0; x<3 ;x++ )
		{
			wangcai:for (int y=0; y<4 ; y++)
			{
				System.out.println("x="+x);
				continue wangcai;
			}
		}*/
		System.out.println(17 >>> 4); // 1
	}
	
	
	@Test
	public void test38(){
		System.out.println("coming~~");
		int[] arr = {13,15,19,28,33,45,78,106};
		int max,min,mid;
		min = 0;
		max = arr.length-1;
		mid = (max+min)/2;
		int key = 17;
		while(arr[mid]!=key)
		{
			if(key>arr[mid])
				min = mid + 1;
			else if(key<arr[mid])
				max = mid - 1;
			// 不加下面这个判断 会死循环
			if(max < min){
				break;
			}
			mid = (max+min)/2;
		}
		System.out.println(mid);
	}
	
	
	
	
	@Test
	public void test39(){
		int num1 = (int) (Math.random() * 20);
		int num2 = (int) (Math.random() * 20);
		System.out.println("num1=" + num1 + " ,num2="+num2);
		int temp = 0;
		if(num2 > num1){
	        temp = num1;  
	        num1 = num2;  
	        num2 = temp;  
		}
		System.out.println("num1=" + num1 + " ,num2="+num2);
	}
}
