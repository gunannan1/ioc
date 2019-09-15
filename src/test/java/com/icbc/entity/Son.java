package com.icbc.entity;

import com.icbc.annotation.Autowired;
import com.icbc.annotation.component;


@component
public class Son {

    @Autowired(value ="小王")
    private String name;

    public Son(){};

    public Son(String name){
        this.name=name;
    }

    public void say(){
        System.out.println("My name is "+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
