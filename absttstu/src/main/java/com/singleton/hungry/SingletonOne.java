package com.singleton.hungry;

/**
 * Created by Administrator on 2020/3/4.
 */
public class SingletonOne {

    private SingletonOne(){}

    public static SingletonOne getInstance(){
        return new SingletonOne();
    }
}
