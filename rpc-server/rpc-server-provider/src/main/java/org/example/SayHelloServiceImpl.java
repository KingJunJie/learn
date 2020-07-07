package org.example;

public class SayHelloServiceImpl implements ISayHelloService {

    @Override
    public String sayHello(String txt) {
        System.out.println("request data:" + txt);
        return "欢迎：" + txt;
    }
}
