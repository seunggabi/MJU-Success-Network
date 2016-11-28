package com.seunggabi.mju_success_network.model.bean;

/**
 * Created by sohee on 2016-11-14.
 */

public class Dept {
    private int id;
    private String name;

    public Dept(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
