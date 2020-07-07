package org.example.safelist;

import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程下的map
 */
public class ConCurrentHashMapDemo {

    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
        Map<String,Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0, 5), "");
                System.out.println(map.keySet());
            }).start();
        }
    }

}
