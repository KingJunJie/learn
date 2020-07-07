package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {

    Socket socket;
    Object object;

    public ProcessorHandler(Socket socket, Object object) {
        this.socket = socket;
        this.object = object;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());//表示拿到客户端提交过来的数据
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            System.out.println("收到客户端的请求" + rpcRequest.toString());
            Object invoke = invoke(rpcRequest);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(invoke);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = Class.forName(request.getClassName());
        Method method = clazz.getMethod(request.getMethodName(), request.getTypes());
        Object invoke = method.invoke(object, request.getParames());
        return invoke;
    }

}
