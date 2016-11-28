package com.seunggabi.mju_success_network.model.bean;

/**
 * Created by sohee on 2016-11-14.
 */

public class Pos {
    private int id;
    private String name;

    public Pos(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
}
