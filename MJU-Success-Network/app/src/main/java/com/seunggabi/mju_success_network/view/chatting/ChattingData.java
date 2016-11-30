package com.seunggabi.mju_success_network.view.chatting;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by seunggabi on 2016-11-23.
 */

public class ChattingData implements Serializable {
    private int l_id;
    private String l_content;
    private Date l_time;
    private int u_id;
    private String u_name;

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public String getL_content() {
        return l_content;
    }

    public void setL_content(String l_content) {
        this.l_content = l_content;
    }

    public Date getL_time() {
        return l_time;
    }

    public void setL_time(Date l_time) {
        this.l_time = l_time;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public ChattingData(){}

    public ChattingData(int l_id, String l_content, Date l_time, int u_id, String u_name) {

        this.l_id = l_id;
        this.l_content = l_content;
        this.l_time = l_time;
        this.u_id = u_id;
        this.u_name = u_name;
    }
}
