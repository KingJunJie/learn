package com.singleton.hungry;

/**
 * 饿汉式
 * 优点：线程安全
 * 缺点：资源消耗大，占用内存
 * Created by Administrator on 2020/3/4.
 */
public class HungrySingleton {

    private static final HungrySingleton instance;

    static {
        instance = new HungrySingleton();
    }

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance(){
        return instance;
    }

}
