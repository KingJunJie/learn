package org.example.jucdemo;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread01 {

    public static void main(String[] args) throws InterruptedException {
        //获取CPU核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        TimeUnit.SECONDS.sleep(1);

        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "C").start();

    }

}

class Ticket {
    private Integer num = 100;

    Lock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        if (num > 0) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + "卖出票：" + (101 - num) +  "剩余票数：" + --num);
        }
        lock.unlock();
    }

}
