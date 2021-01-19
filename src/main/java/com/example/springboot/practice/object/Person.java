package com.example.springboot.practice.object;

import java.io.*;

/**
 * @author xingce
 * @date 2020-04-30 17:51
 */
public class Person implements Cloneable, Serializable {

    private String name;
    private int age;
    private Address address;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.address = new Address();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setAddress(String province, String city) {
        address.setAddress(province, city);
    }

    public void display(String name) {
        System.out.println(name + ":" + "name=" + name + ", age=" + age + "," + address);
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

    public Object deepClone() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInput ois = new ObjectInputStream(bis);

        return ois.readObject();
    }
}
