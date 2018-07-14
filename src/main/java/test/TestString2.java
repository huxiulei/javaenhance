package test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class TestString2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String a = "ab";
        String bb = "b";
//		String b = "a" + "b";  //true
//		String b = "a" + bb;   //false
//		System.out.println((a == b));
//		String idCard = "411503199002102719";
//		String pwd = (idCard.substring(idCard.length()-6)).toUpperCase();
//		System.out.println(pwd);


//		String str = "";
//		String abc = str;
//		str = "xxxx";
//		System.out.println(abc);
//		String str = null;
//		System.out.println(String.valueOf(str));
//		if(StringUtils.isNotBlank(str)){
//			System.out.println("111111");
//		}else{
//			System.out.println("为空");
//		}

        /**
         * 我们期望的结果是将数组中的元素通过Arrays.asList转换到集合类当中,
         * 但事与愿违,我们只将数组本身增加了进入,并没有将数组内的值分拆分开来,
         * 此时若然对集合List增加了泛型就会在编译期间给出错误的提示,
         * 或将数组本身改变成Integer就可以解决问题
         */
//        int[] nums = new int[] { 1, 2, 3, 4, 5 };
//        List list = Arrays.asList(nums);
//        System.out.println(list.size());
        // 此时输出的size为1

        System.out.println(10.00-9.60);

    }



    /**
     Random r = new Random(50);
     如果这样了,那么每次产生的随机数就一样了   因为随机种子固定了
     */
    @Test
    public void test1(){
        Random r = new Random();
        for(int i=1;i<4;i++){
            System.out.println("第"+i+"次" + r.nextInt());

        }
    }

    @Test
    public void test3(){
        String str = "好是好!";
        String result = StringUtils.removeStartIgnoreCase(str, "好");
        System.out.println(result);
    }
    @Test
    public void test4(){
        String str = "好是好!";
        byte[] b;
        try {
            b = str.getBytes("UTF-8");
            System.out.println(new String(b,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test5() throws Exception{
        //FileCopyUtils.copy(new File("d:\\old.txt"), new File("d:\\new1.txt"));
//		System.out.println(String.valueOf(Character.toChars(0x1F6B2)));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        System.out.println("哈: " + calendar.getTime());
        System.out.println("sdfs : " + DateUtil.parseDate(calendar.getTime()));
    }


    @Test
    public void test6(){
        List<String> listArr = new ArrayList<String>();
        listArr.add("tom");
        listArr.add("jim");
        listArr.add("lucc");
        listArr.add("gg");
        listArr.add("1212");
        listArr.add("caland");
        listArr.add("ona");
        int index = 1;
        for(String s:listArr){
            if(index == 6) break;
            System.out.println((index++) + ":" + s);
        }

        //System.out.println(index);
    }
}
