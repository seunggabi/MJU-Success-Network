package com.seunggabi.mju_success_network.view.chatting;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by seunggabi on 2016-11-23.
 */

//채팅방 사용자 정보 데이터
public class ChattingUserData implements Serializable {
    private int j_id;
    private int u_id;
    private int g_id;
    private String u_name;
    private char j_status;
    private Date j_time;

    public ChattingUserData(int j_id, int u_id, int g_id, char j_status, String j_time) {
        this.j_id = j_id;
        this.u_id = u_id;
        this.g_id = g_id;
        this.j_status = j_status;

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = transFormat.parse(j_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.j_time = date;
    }

    public ChattingUserData() {

    }

    public void setJ_time(String j_time) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = transFormat.parse(j_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.j_time = date;
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

    public char getJ_status() {
        return j_status;
    }

    public void setJ_status(char j_status) {
        this.j_status = j_status;
    }

    public Date getJ_time() {
        return j_time;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public int getJ_id() {
        return j_id;
    }

    public void setJ_id(int j_id) {
        this.j_id = j_id;
    }
}
