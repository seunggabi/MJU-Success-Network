package com.seunggabi.mju_success_network.view.group;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by seunggabi on 2016-11-23.
 */

public class GroupData implements Serializable {
    private int u_id;
    private int g_id;
    private int j_id;
    private String u_name;
    private String g_name;
    private String g_intro;
    private Date g_time;
    private String g_status;
    private String g_hidden;
    private String g_tag;
    private char j_alarm;

    public GroupData(int u_id, int g_id, String u_name, String g_name, String g_intro, String g_time, String g_status, String g_hidden, String g_tag, char j_alarm) {
        this.u_id = u_id;
        this.g_id = g_id;
        this.u_name = u_name;
        this.g_name = g_name;
        this.g_intro = g_intro;
        this.g_status = g_status;
        this.g_hidden = g_hidden;
        this.g_tag = g_tag;
        this.j_alarm = j_alarm;

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = transFormat.parse(g_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.g_time = date;
    }

    public GroupData() {

    }

    public void setG_time(String g_time) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = transFormat.parse(g_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.g_time = date;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_intro() {
        return g_intro;
    }

    public void setG_intro(String g_intro) {
        this.g_intro = g_intro;
    }

    public String getG_status() {
        return g_status;
    }

    public void setG_status(String g_status) {
        this.g_status = g_status;
    }

    public String getG_hidden() {
        return g_hidden;
    }

    public String getG_tag() {
        return g_tag;
    }

    public void setG_tag(String g_tag) {
        this.g_tag = g_tag;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setG_hidden(String g_hidden) {
        this.g_hidden = g_hidden;
    }

    public char getJ_alarm() {
        return j_alarm;
    }

    public void setJ_alarm(char j_alarm) {
        this.j_alarm = j_alarm;
    }
}
