package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class JDKTest {

    public static void main(String[] args) {

    }


    public void oneTest() {
        Person person = new Person();
        person.setId(1);
        person.setName("嘿嘿");
        System.out.println();

    }
}

class Person implements Serializable {
    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}