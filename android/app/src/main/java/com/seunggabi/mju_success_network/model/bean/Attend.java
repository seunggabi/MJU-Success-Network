package com.seunggabi.mju_success_network.model.bean;

/**
 * Created by sohee on 2016-11-14.
 */

public class Attend {
    private int id;
    private String time;
    private int s_id;
    private int u_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getId() {

        return id;
    }

    public String getTime() {
        return time;
    }

    public int getS_id() {
        return s_id;
    }

    public int getU_id() {
        return u_id;
    }

    public Attend(int id, String time, int s_id, int u_id) {

        this.id = id;
        this.time = time;
        this.s_id = s_id;
        this.u_id = u_id;
    }
}
