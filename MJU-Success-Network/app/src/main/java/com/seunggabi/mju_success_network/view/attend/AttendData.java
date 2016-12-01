package com.seunggabi.mju_success_network.view.attend;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by seunggabi on 2016-11-23.
 */

public class AttendData implements Serializable {
    private int a_id;
    private int s_id;
    private int u_id;
    private String u_name;
    private Date a_time;

    public AttendData() {}

    public AttendData(int a_id, int s_id, int u_id, String u_name, Date a_time) {
        this.a_id = a_id;
        this.s_id = s_id;
        this.u_id = u_id;
        this.u_name = u_name;
        this.a_time = a_time;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
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

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public Date getA_time() {
        return a_time;
    }

    public void setA_time(Date a_time) {
        this.a_time = a_time;
    }
}
