package com.seunggabi.mju_success_network.model.bean;

import java.util.Date;

/**
 * Created by sohee on 2016-11-14.
 */

public class Group {
    private int id;
    private String name;
    private String tag;
    private String intro;
    private Date time;
    private char status;
    private char hidden;
    private int u_id;

    public Group(int id, String name, String tag, String intro, Date time, char status, char hidden, int u_id) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.intro = intro;
        this.time = time;
        this.status = status;
        this.hidden = hidden;
        this.u_id = u_id;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getHidden() {
        return hidden;
    }

    public void setHidden(char hidden) {
        this.hidden = hidden;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
}
