package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParames(args);
        rpcRequest.setTypes(method.getParameterTypes());

        //简历网络通信细节
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        Object send = rpcNetTransport.send(rpcRequest);
        return send;
//        System.out.println("come in proxy");
//        return "Hello Proxy";
    }
}
