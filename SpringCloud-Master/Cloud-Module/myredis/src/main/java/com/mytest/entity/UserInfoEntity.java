package com.mytest.entity;

public class UserInfoEntity {

    private String id;

    private String name;

    private int age;

    private int address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfoEntity [address=" + address + ", age=" + age + ", id=" + id + ", name=" + name + "]";
    }
}
