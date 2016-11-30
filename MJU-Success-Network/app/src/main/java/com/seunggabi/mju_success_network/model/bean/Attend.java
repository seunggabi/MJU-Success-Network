package com.seunggabi.mju_success_network.model.bean;

import java.util.Date;

/**
 * Created by sohee on 2016-11-14.
 */

public class Attend {
    private int id;
    private Date time;
    private int s_id;
    private int u_id;

    public Attend(int id, Date time, int s_id, int u_id) {
        this.id = id;
        this.time = time;
        this.s_id = s_id;
        this.u_id = u_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
}
