package org.example.callabledemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableImpl callable = new CallableImpl();
        FutureTask<String> task = new FutureTask<>(callable);

        new Thread(task,"A").start();
        new Thread(task,"B").start();
        String taskString = task.get();

        System.out.println(taskString);
    }
}

class CallableImpl implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "空的？";
    }
}

