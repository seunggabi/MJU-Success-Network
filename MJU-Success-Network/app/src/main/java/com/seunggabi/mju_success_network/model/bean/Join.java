package com.seunggabi.mju_success_network.model.bean;

import java.util.Date;

/**
 * Created by sohee on 2016-11-14.
 */

public class Join {
    private int id;
    private Date time;
    private int g_id;
    private int u_id;
    private char status;
    private char alarm;

    public Join(int id, Date time, int g_id, int u_id, char status, char alarm) {
        this.id = id;
        this.time = time;
        this.g_id = g_id;
        this.u_id = u_id;
        this.status = status;
        this.alarm = alarm;
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

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getAlarm() {
        return alarm;
    }

    public void setAlarm(char alarm) {
        this.alarm = alarm;
    }
}