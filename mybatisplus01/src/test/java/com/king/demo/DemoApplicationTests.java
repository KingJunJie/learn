package com.king.demo;

import com.king.demo.mapper.UserMapper;
import com.king.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void getUserById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    void insertUser(){
        User user = new User();
        user.setName("狼");
        user.setAge(19);
        user.setEmail("112211@ybz.com");
        int insert = userMapper.insert(user);
        System.out.println("插入成功返回值" + insert);
    }

    @Test
    void updateUserById(){
        User user = userMapper.selectById(1265115570336587779L);
        user.setName("虎");
        user.setEmail("ceshi@ybz.com");
        int i = userMapper.updateById(user);
        System.out.println("更新是否成功:" + i);
    }

}
