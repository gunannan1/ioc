package com.publicgroup.entity;


import com.publicgroup.annotation.Autowired;
import com.publicgroup.annotation.component;

@component
public class Father {
    @Autowired(value ="大王")
    private String name;

    @Autowired
    private Son son;

    @Autowired(value = "11")
    private Integer age;

    public Father(){};

    public Father(Son son,String name){
        this.son=son;
        this.name=name;
    }

    public void say(){
        System.out.println("My name is "+name +" and my son's name is "+son.getName()+"and my age is "+age);
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

    public void setAge(Integer age) {
        this.age = age;
    }
}
