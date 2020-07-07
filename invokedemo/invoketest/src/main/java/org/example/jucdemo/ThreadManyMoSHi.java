package org.example.jucdemo;

import java.util.concurrent.TimeUnit;

/**
 * 八锁问题
 */
public class ThreadManyMoSHi {

    public static void main(String[] args) {
        Person person = new Person();
        Person person1 = new Person();

        new Thread(()->{
            person.runCool();
        }).start();
        new Thread(()->{
            person1.eat();
        }).start();
        new Thread(()->{
            person1.play();
        }).start();
    }

}
class Person{

    //一个类中，一个锁，静态方法是放在方法栈中，普通带锁方法在堆中，所以互不干扰
    public static synchronized void runCool(){
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("跑");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void eat(){
        try {
            TimeUnit.SECONDS.sleep(0);
            System.out.println("吃");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //普通方法和锁无关，随时可以执行
    public void play(){
        System.out.println("玩");
    }

}