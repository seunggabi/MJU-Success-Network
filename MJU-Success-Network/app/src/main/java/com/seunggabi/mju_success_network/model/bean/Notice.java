package com.seunggabi.mju_success_network.model.bean;

import java.util.Date;

/**
 * Created by sohee on 2016-11-20.
 */

public class Notice {
    private int id;
    private Date time;
    private String content;
    private int u_id;

    public Notice(int id, Date time, String content, int u_id) {
        this.id = id;
        this.time = time;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
}
