package com.sjtu.domain;

public class Person {
    private String name;
    private String pid;

    public Person() {
    }

    public Person(String name, String id) {
        this.name = name;
        this.pid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
