package itcast.test;

/**
 * FIXME 对小数的判断不能用==，只能是判断一个小数是否在某个公差范围内，就象要描述一直铅笔的粗细一样，只能是φ3.5±0.0001
 * 浮点数只能比较大小，不能比较相等
 * 用BigDecimal或者double来处理货币和小数运算
 * @author hu.xl
 */
public class TestFloat {

    public static void main(String[] args) {
        System.out.println(1.000f);
        System.out.println(1.00000001f);
        System.out.println(1.000f == 1.00000001f);
        System.out.println(1.0000001f);
        System.out.println(1.000f == 1.0000001f);
    }

}
