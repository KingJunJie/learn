package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ISayHelloService sayHelloService = new SayHelloServiceImpl();//实例化
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
            rpcProxyServer.publisher(sayHelloService,8888);
        System.out.println("Hello World!");
    }
}
