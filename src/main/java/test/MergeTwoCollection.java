package test;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoCollection {
    /**
     * 将B集合合并到A集合 如果B集合中有元素同A集合中某元素相同  则在后面加001,然后再在A,B集合查看是否相同，相同加001 ，直到不相同。
     * @param args
     */
    public static void main(String[] args) {
        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();

        listA.add("A");
        listA.add("A001");
        listA.add("A001001");

        listB.add("A001");
        listB.add("A001001");
        listB.add("A001001001");

        merge(listA, listB);

        System.out.println(listA);
    }

    private static void merge(List<String> listA, List<String> listB) {
        for(int i=0;i<listB.size();i++){
            String str=listB.get(i);
            while(true){
                if(listA.contains(str)){
                    String s=str+"001";
                    str=s;
                }else{
                    listA.add(str);
                    break;
                }
            }
        }
    }
}  