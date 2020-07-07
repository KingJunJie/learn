package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {

    public void publisher(Object service, int port) {
        ExecutorService executorService = Executors.newCachedThreadPool();//线程池

        ServerSocket serverSocket = null;
        try {
            //nio -> new io
            //Selector 多路复用器  ==》一个线程管理n个连接  epoll
            serverSocket = new ServerSocket(port);//监听指定端口  会出现连接阻塞
            while (true) {
                Socket socket = serverSocket.accept();//服务器执行
                //socket.getPort();//客户端信息
                executorService.submit(new ProcessorHandler(socket,service));

                /*下面会出现io阻塞：也就是下面输入输出中，不能跳出循环，导致其他客户端给不能进入循环而无法执行accept*/
                InputStream inputStream = socket.getInputStream();//输入流
                OutputStream outputStream = socket.getOutputStream();//输出流
                //阻塞通信[B IO]->
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
