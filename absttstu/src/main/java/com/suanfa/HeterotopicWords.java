package com.suanfa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 异位词
 */
public class HeterotopicWords {

    public static void main(String[] args) {
        String var1 = "ananan";
        String var2 = "nanana";
        heterotopicWords(var1, var2);
    }

    public static void heterotopicWords(String var1, String var2) {

        char[] chars1 = var1.toCharArray();
        char[] chars2 = var2.toCharArray();

        char[] name_var1 = new char[26];
        int[] num_var1 = new int[26];

        char[] name_var2 = new char[26];
        int[] num_var2 = new int[26];

        getArray(chars1, name_var1, num_var1);
        getArray(chars2, name_var2, num_var2);


        //todo
        //两数组已经返回，接下是就是对比数组见是否元素都互相包含即可判定是否为异位词
    }

    public static void getArray(char[] chars1, char[] name_var1, int[] num_var1) {
        for (char value : chars1) {
            //存字母
            name_var1[0] = chars1[0];
            //存个数:num_var1
            //获取存储字母数组长度
            int e = 0;
            for (char c : name_var1) {
                if (c == '\0') {
                    break;
                }
                e++;
            }
            //循环name，看是否有重复
            for (int x = 0; x < e; x++) {
                //重复则记录个数，不重复则存入name数组中
                if (name_var1[x] == value) {
                    num_var1[x] += 1;
                } else if (name_var1[x] != '\u0000') {
                    name_var1[e] = value;
                    num_var1[e] = 1;
                    break;
                }
            }
        }
    }
}

