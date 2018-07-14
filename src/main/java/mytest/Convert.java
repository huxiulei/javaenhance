package mytest;

import java.util.Scanner;

public class Convert {
    /**
     * 程序的入口,用于测试的主
     */
    public static void main(String[] args) {
        Convert convert = new Convert();
        String str = convert.getOutput(convert.getInput());
        System.out.println(str);
    }

    /**
     * 对输入合法的8及8位以下的数字进行相应格式的转换
     * @param original
     *            原输入的数字
     * @return 转换后的汉字的字符串表示形式
     */
    public String getOutput(int original) {
        String result = "";
        if(original == 0){
            return "零";
        }

        // temp1用于保存输入的数字的前四位(若存在的情况下)
        int temp1 = original / 10000;
        // temp2用于保存输入数字的后四位
        int temp2 = original % 10000;

        if (temp1 != 0) {
            // 若前四位不为0, 处理前四位
            result = this.process(temp1);

            // 在前四位和后四位之间加一个　"万"　字
            result += "万";
            // 如果前四位的结尾为"0"(但同时后四位不能全为零),或后四位的开头为"0",则在前四位和后四位之间除加 "万" 字外再加入"零"
            if (temp2 / 1000 == 0 && temp2 != 0 || temp1 % 10 == 0
                    && temp2 != 0) {
                result += "零";
            }
        }

        // 将处理后的前四位和后四位相连接
        result += this.process(temp2);
        return result;
    }

    /**
     * 处理传入的四位数的阿拉伯数字,将其转换成对应的汉字表示形式
     * @param nub
     *            所传入的待处理的四位的阿拉伯数字
     * @return 处理后的四位阿拉伯数字的汉字表示形式
     */
    private String process(int nub) {
        //将0到9的数字的汉字对应形式放到一个字符型的数组中
        char[] cnNumber = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
        // 由于要频繁进行字符串的拼接操作，所以此处使用可变的字符串对象,StringBuffer
        StringBuffer sb = new StringBuffer("");
        // 获得千位数
        int n1 = nub / 1000;
        // 获得百数
        int n2 = nub % 1000 / 100;
        // 获得十位数
        int n3 = nub % 100 / 10;
        // 获得个位数
        int n4 = nub % 10;

        // 处理千位
        if (n1 != 0) {
            // 在千位上的数字不为零的情况下将其转换成相应的汉字表示形式,并在其后加入一个　"千"　字
            sb.append(cnNumber[n1]).append("仟");

            // 若千位数字以后的数字全为0则返回转换后的千位结束此方法
            if (n2 == 0 && n3 == 0 && n4 == 0) {
                return sb.toString();
            }

            // 在千位不为0的情况下直接将百位上的数字的汉字表示形式加到千位后面
            sb.append(cnNumber[n2]);
        }

        // 处理百位的其他情况
        if (n2 != 0) {
            // 若千位为零但百位上的数字不为零,则将百位上的数字的转换成相应的汉字表示形式
            if (n1 == 0) {
                sb.append(cnNumber[n2]);
            }
            // 如果百位上的数字不为零则在其相应的汉字表示形式外加上一个"百"字
            sb.append("佰");
        }
        // 若百位数字以后的数字全为0则返回转换后的百位以前的数字,结束此方法
        if (n3 == 0 && n4 == 0) {
            return sb.toString();
        }

        // 处理十位, 只处理在十位上的数字不为零的情况下
        if (n3 != 0) {
            // 十位不为1,或十位为1但百位不为0的情况下,将其对应的数字转换成相应的汉字表示形式并在其后加上"十"字
            // if(n3 != 1 || (n2!=0 && n3==1)){, 此条语句相当于下条语句
            if (n3 != 1 || n2 != 0) {
                sb.append(cnNumber[n3]).append("拾");
            } else {
                // 在十位上的数字为1并且百位上的数字的情况下直接将其转换成"十"字
                sb.append("拾");
            }
            // 在处理完十位上的数字后,如果个位上的数字为0则直接返回
            if (n4 == 0) {
                return sb.toString();
            }
        } else if (n2 != 0) {
            // 在十位上的数字为0但百位上的数字不为零的情况下,将十位上的数字0转换成汉字的"零"表示
            sb.append(cnNumber[n3]);
            // 上条语句也可写成sb.append("零");
        }

        // 处理个位, 在其不为零的情况下将其转换成相应的汉字表示形式
        if (n4 != 0) {
            sb.append(cnNumber[n4]);
        }

        return sb.toString();
    }

    /**
     * 从命令行获得一个整数,如果用户输入不合法或整数位数大于8位则程序直接退出
     *
     * @return
     */
    public int getInput() {
        int result = 0;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("请输入要转换的数字,一定要是8位以内的全数字");
            result = sc.nextInt();

            // 若输入的数字大于8位则直接退出程序
            if (result > 99999999) {
                System.out.println("您输入的数字位数大于8位");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("您出的格式错误,无法转换为数字!");
            System.exit(0);
        }
        return result;
    }
}
