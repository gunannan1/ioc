package com.icbc.entity;

public class Father {
    private String name;
    private Son son;

    public Father(){};

    public Father(Son son,String name){
        this.son=son;
        this.name=name;
    }

    public void say(){
        System.out.println("My name is "+name +" and my son's name is "+son.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }
}
