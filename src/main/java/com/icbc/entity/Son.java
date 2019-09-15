package com.icbc.entity;

public class Son {

    private String name;

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
