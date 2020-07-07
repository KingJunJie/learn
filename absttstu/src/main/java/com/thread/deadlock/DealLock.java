package com.thread.deadlock;

/**
 * 死锁
 */
public class DealLock {

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DealLock().dealLock();
    }
    private void dealLock(){
        Thread thread01 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("第一个线程结束了");
                    }
                }
            }
        });
        Thread thread02 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println("第二个线程结束了");
                    }
                }
            }
        });
        thread01.start();
        thread02.start();
    }
}
