package com.test.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestArray {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("刘雯");
        list.add("杨紫");
        list.add("胡歌");
        String [] strArr =  list.toArray( new String[]{});
        System.out.println(Arrays.toString(strArr));
    }

}
