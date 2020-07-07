package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {

    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Socket newSocket() {
        try {
            Socket socket = new Socket(host, port);
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object send(RpcRequest request) {
        Socket socket = newSocket();
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            //写入那些信息到服务端
            outputStream.writeObject(request);
            outputStream.flush();

            //拿到服务端返回的数据
            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
