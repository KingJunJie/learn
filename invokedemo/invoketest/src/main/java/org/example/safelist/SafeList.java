package org.example.safelist;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试多线程哪种list集合更安全
 */
public class SafeList {

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
        List<String> list1 = new Vector<>();
        List<String> list2 = new CopyOnWriteArrayList<>();
        List<String> list3 = Collections.synchronizedList(new ArrayList<>());
//        listDemo(list);
        for(int i = 0; i<10;i++){
            new Thread(()->{
                list1.add(UUID.randomUUID().toString().substring(0,5));
                list2.add(UUID.randomUUID().toString().substring(0,5));
                list3.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(Thread.currentThread().getName() + "====》" + list1);
                System.out.println(Thread.currentThread().getName() + "====》" + list2);
                System.out.println(Thread.currentThread().getName() + "====》" + list3);
            }).start();

        }
    }

    public static void listDemo(List<String> list){
        for(int i = 0; i<10;i++){
            new Thread(()->{
                list.add("s123");
            });

        }
    }
}
