任何接口，只包含唯一一个抽象方法，那么它就是一个函数式接口

###JUC
并发编程本质：充分利用CPU资源

####线程状态：
    Thread.State:
        //新生
        NEW
        //运行
        RUNNABLE
        //阻塞
        BLOCKED
        //等待，死死的等下去
        WAITING
        //超时等待   等一会就超时了
        TIME_WAITING
        //中止
        TIMEINATED

**生产者和消费者**
        
        就是用if判断的话，唤醒后线程会从wait之后的代码开始运行，
        但是不会重新判断if条件，直接继续运行if代码块之后的代码，
        而如果使用while的话，也会从wait之后的代码运行，但是唤醒
        后会重新判断循环条件，如果不成立再执行while代码块之后的
        代码块，成立的话继续wait。与CPU调度也有关系。
        
**wait/sleep区别：**

        1、类不同：
               wait-》object
               sleep-》Thread
        2、锁的释放：
              wait会释放锁，(需等待其他线程notify/notifyAll来唤醒)
              sleep抱着锁睡，不会释放。
        3、使用的范围不通：
              wait：同步代码块中
              sleep：任何地方
        4、是否需要捕获异常：
              wait不需要捕获
              sleep需要
**notify**

        notify()方法会唤醒一个正在等待的线程，如果有多个线程则只会唤醒其中一个
        notifyALL()会唤醒所有等待的线程，唤醒后获取锁是随机的，取决于CPU调度
              
**Lock锁和Synchorized却区别**
        
        1、Synchorized内置的关键字
           Lock是一个java类
        2、Synchorized无法判断获取锁的状态
           Lock可以判断
        3、Synchorized会自动释放锁
           Lock所以必须要手动释放锁，如果不释放就会死锁
        4、Synchorized：线程1（获取锁，阻塞），线程2死死的等待
           Lock锁不一定会等待下去
        5、Synchorized：可重入锁，不可中断的，非公平锁
           Lock：可重入锁，可以判断锁，非公平（可以设置公平与否）
        6、Synchorized：适合锁少量代码块
           Lock：适合锁大量同步代码块


####JUC☞Condition
       精准唤醒
       可以指定线程唤醒：A执行完唤醒B，B结束唤醒C
       
####八锁问题
        Synchorized 锁的对象是方法的调用者！（一个对象一把锁）
                    也就是说多个方法在一个类，谁先拿到谁先执行。
                    而普通方法则不受锁的影响
         
         1、一个对象有多个Synchorized,先被调用的方法先执行
         2、一个类中，普通方法（不带锁的），不受锁的影响，锁等待时普通方法先执行，若加锁的方法没有设置sleep则按顺序执行。
         3、若两个对象，则没有sleep或者等待的那一方先执行
         4、静态锁 锁的是Class模板，一个对象调用内部的两个静态Synchorized方法，谁先调用谁先执行
         5、一个类有多个静态Synchorized，该类在有多个对象时候也同样是谁先的调用谁先被执行，因为被static修饰，类加载即被创建。
         6、若一个类中有静态同步方法和普通同步方法，两个线程去调用的时候，属于两个锁，一个锁模板，一个锁对象，互不影响。
         
         注：new this 是创建一个对象，而static则是类加载的时候创建了模板
         
####JUC的包
        CopyOnWriteArraySet
        CopyOnWriteArrayList
        Vector是增删改查方法都加了synchronized，保证同步，但是每个方法执行的时候都要去获得锁，性能就会大大下降，
        而CopyOnWriteArrayList 只是在增删改上加锁，但是读不加锁，在读方面的性能就好于Vector，CopyOnWriteArrayList支持读多写少的并发情况
        读写分离，写时复制出一个新的数组，完成插入、修改或者移除操作后将新数组赋值给array。
        
        ConcurrentModificationException线程并发异常
 
 **HashSet底层**
        
        底层是HashMap
        public HashSet(){
            map = new HashMap<>();
        }
        //haseSet的add方法，就是

```run()同步执行，start()异步执行，调用了start之后，线程开始执行，然后会调用run()```

####Callcble
        1、有返回值
        2、可以抛出异常
        3、方法不同，run()/call()
        细节：
            1、有缓存
            2、等待拿出来结果，所以会阻塞（new两个Thread会打印，只返回一个结果，因为另一个去缓存里取了）
                详细看CallableDemo01.java
        线程唯一启动方式start()。也就是new Thread()或者实现Runnable。
        而Runnable是FutureTask的父类，又因为FutureTask里面构造器有：FutureTask(Callable<V> callable) 
        所以，可以通过FutureTask启动Callable