package com;

/**
 * 测试javap命令 javap -v Test  javap -s Test
 * @author King
 * @email wangjjy@yonyou.com
 * @since 2020/7/7 10:21
 */
public class JavapTest {

    public static void main(String[] args) {
        String a = "ja" + "va";
        String b = "java";
        String c = new String("java");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

}
