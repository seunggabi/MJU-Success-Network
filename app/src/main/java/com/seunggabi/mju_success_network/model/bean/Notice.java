package com.seunggabi.mju_success_network.model.bean;

/**
 * Created by sohee on 2016-11-20.
 */

public class Notice {
    private int id;
    private String time;
    private String content;
    private int u_id;

    public Notice(int id, String time, String content, int u_id) {
        this.id = id;
        this.time = time;
        this.content = content;
        this.u_id = u_id;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getU_id() {
        return u_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
}
