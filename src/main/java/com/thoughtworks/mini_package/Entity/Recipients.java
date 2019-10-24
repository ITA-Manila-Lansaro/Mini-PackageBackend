package com.thoughtworks.mini_package.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipients {

    @Id
    @GeneratedValue(generator =  "uuid")
    @GenericGenerator(name = "uuid", strategy =  "uuid")
    private String recipientsNum;
    private String name;
    private String phone;
    private Double weight;

    public String getRecipientsNum() {
        return recipientsNum;
    }

    public void setRecipientsNum(String recipientsNum) {
        this.recipientsNum = recipientsNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
