package com.example.springboot.practice.object;

/**
 * @author xingce
 * @date 2020-04-30 18:32
 *
 * clone方法浅拷贝
 * 深拷贝 字节流方式
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Person p1 = new Person("zhangsan", 21);
        p1.setAddress("湖北省", "武汉市");

        Person p2 = (Person) p1.clone();
        System.out.println("p1.name" + p1.getName().hashCode());
        System.out.println("p2.name" + p2.getName().hashCode());

        p1.display("p1");
        p2.display("p2");
        p2.setAddress("湖北省", "荆州市");
        System.out.println("将复制之后的对象地址修改：");
        p1.display("p1");
        p2.display("p2");
    }

}
