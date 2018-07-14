package mytest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumTest {
    public static class StringNum implements Comparable{
        private String s;
        private Integer first;
        private Integer last;
        public StringNum (String s){
            this.s = s;
            String str[] = s.split("-");
            if(str.length>1){
                first = Integer.valueOf(str[0]);
                last = Integer.valueOf(str[1]);
            }else{
                first = Integer.valueOf(str[0]);
                last = 0;
            }

        }

        public int compareTo(Object o) {
            StringNum sn = (StringNum)o;
            if(first>sn.first){
                return 1;
            }else if(first.equals(sn.first)){
                return 0;
            }else{
                return -1;
            }
        }

    }
    public static void testUnit(List<StringNum> list){
        Collections.sort(list);
        for(int i=0;i<list.size();i++){
            StringNum s = list.get(i);
            //  System.out.println(s.s);
            if(s.last>0){
                for(int j=i+1;j<list.size();j++){
                    StringNum s1 = list.get(j);
                    if(s.last>=s1.first){
                        System.out.println(s.s+"和"+s1.s+"冲突！");
                    }
                }
            }
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<StringNum> list = new ArrayList<StringNum> ();
        list.add(new StringNum("1"));
        list.add(new StringNum("3"));
        list.add(new StringNum("5"));
        list.add(new StringNum("6-9"));
        list.add(new StringNum("8"));
        list.add(new StringNum("120-150"));
        list.add(new StringNum("130-180"));
        list.add(new StringNum("001-010"));
        list.add(new StringNum("0001-0010"));
        testUnit(list);


        int cpuCount = Runtime.getRuntime().availableProcessors();
        System.out.println("当前的CPU个数: " + cpuCount);
    }

}  