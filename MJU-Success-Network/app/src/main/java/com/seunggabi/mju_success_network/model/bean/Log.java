package com.seunggabi.mju_success_network.model.bean;

import java.util.Date;

/**
 * Created by sohee on 2016-11-14.
 */

public class Log {
    private int id;
    private String content;
    private Date time;
    private int g_id;
    private int u_id;

    public Log(int id, String content, Date time, int g_id, int u_id) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.g_id = g_id;
        this.u_id = u_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
