package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        ISayHelloService iSayHelloService = rpcProxyClient.clientProxy(
                ISayHelloService.class, "127.0.0.1", 8888);//代理

        String king = iSayHelloService.sayHello("King");
        System.out.println(king);
    }
}
