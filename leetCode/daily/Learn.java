package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/*
 * @Author: rooterShip
 * @Date: 2023-01-13 17:24:19
 * @LastEditors: rooterShip
 * @LastEditTime: 2023-09-10 10:46:20
 */
public class Learn {
    public static void main(String[] args) {
        int[] a = new int[10];
        System.out.println(Arrays.toString(a));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        int[] B = new int[11];
        System.out.println(Arrays.toString(B));
        System.out.println(list.toString());
        Iterator<Integer> it = list.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        String aa = "123";
        String d = "123";
        String c = new String("123");
        //当Java程序直接使用 "hello" 的字符串直接量时，JVM将会使用常量池来管理这个字符串；
        //当使用 new String("hello") 时，JVM会先使用常量池来管理 "hello" 直接量，
        //再调用String类的构造器来创建一个新的String对象，新创建的String对象被保存在堆内存中。
        System.out.println(aa==d); //true
        System.out.println(aa==c); //false
        System.out.println(d==c); //false
    }
}