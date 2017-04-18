package com.zykj.yixiu.bean;

/**
 * Created by zykj on 2017/4/18.
 */

public class HouseBean {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "HouseBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
