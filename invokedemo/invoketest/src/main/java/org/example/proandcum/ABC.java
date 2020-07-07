package org.example.proandcum;


public class ABC {

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}

class Data { //判断等待，业务，通知
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            //释放锁，等待
            wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "-->" + number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            //释放锁，等待
            wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "-->" + number);
        this.notifyAll();
    }

}
