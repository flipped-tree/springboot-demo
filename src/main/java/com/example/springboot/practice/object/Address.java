package com.example.springboot.practice.object;

import java.io.Serializable;

/**
 * @author xingce
 * @date 2020-04-30 17:52
 */
public class Address implements Serializable {
    private String province;
    private String city;

    public void setAddress(String province,String city){
        this.setProvince(province);
        this.setCity(city);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address() {

    }

    public Address(String province, String city) {
        this.province = province;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
