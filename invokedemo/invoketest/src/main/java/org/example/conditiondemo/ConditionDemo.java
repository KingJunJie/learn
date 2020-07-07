package org.example.conditiondemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    public static void main(String[] args) {
        ConditionUtil conditionUtil = new ConditionUtil();
        ConditionUtil conditionUtil1 = new ConditionUtil();
        ConditionUtil conditionUtil2 = new ConditionUtil();

        new Thread(() -> {
            conditionUtil.bDemo();
        }, "").start();
        new Thread(() -> {
            conditionUtil.cDemo();
        }, "").start();
        new Thread(() -> {
            conditionUtil.dDemo();
        }, "").start();
        new Thread(() -> {
            conditionUtil.aDemo();
        }, "").start();
    }
}

class ConditionUtil {
    int num = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    Condition condition4 = lock.newCondition();
    public void dDemo() {
        lock.lock();
        try {
            while (num != 4) {
                System.out.println("这个是：D");
                condition4.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>DDD");
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void cDemo() {
        lock.lock();
        try {
            while (num != 3) {
                System.out.println("这个是：C");
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>CCC");
            num = 4;
            condition4.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void bDemo() {
        lock.lock();
        try {
            while (num != 2) {
                System.out.println("这个是：B");
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>BBB");
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void aDemo() {
        lock.lock();
        //业务判断-》执行-》通知
        try {
            while (num != 1) {
                //等待
                System.out.println("这个是：A");
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>AAA");
            num = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }






}
