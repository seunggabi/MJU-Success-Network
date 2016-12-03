package com.seunggabi.mju_success_network.view.schedule;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by seunggabi on 2016-11-23.
 */

public class ScheduleData implements Serializable {
    private int s_id;
    private int u_id;
    private int g_id;
    private String s_name;
    private String s_content;
    private Date s_time;
    private Date s_datetime;
    private float s_gps_logitude;
    private float s_gps_latitude;
    private String s_gps_location;
    private String s_gps_name;

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

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_content() {
        return s_content;
    }

    public void setS_content(String s_content) {
        this.s_content = s_content;
    }

    public Date getS_time() {
        return s_time;
    }

    public void setS_time(Date s_time) {
        this.s_time = s_time;
    }

    public Date getS_datetime() {
        return s_datetime;
    }

    public void setS_datetime(Date s_datetime) {
        this.s_datetime = s_datetime;
    }

    public float getS_gps_logitude() {
        return s_gps_logitude;
    }

    public void setS_gps_logitude(float s_gps_logitude) {
        this.s_gps_logitude = s_gps_logitude;
    }

    public float getS_gps_latitude() {
        return s_gps_latitude;
    }

    public void setS_gps_latitude(float s_gps_latitude) {
        this.s_gps_latitude = s_gps_latitude;
    }

    public String getS_gps_location() {
        return s_gps_location;
    }

    public void setS_gps_location(String s_gps_location) {
        this.s_gps_location = s_gps_location;
    }

    public String getS_gps_name() {
        return s_gps_name;
    }

    public void setS_gps_name(String s_gps_name) {
        this.s_gps_name = s_gps_name;
    }

    public ScheduleData(){}

    public ScheduleData(int s_id, int u_id, int g_id, String s_name, String s_content, Date s_time, Date s_datetime, float s_gps_logitude, float s_gps_latitude, String s_gps_location, String s_gps_name) {

        this.s_id = s_id;
        this.u_id = u_id;
        this.g_id = g_id;
        this.s_name = s_name;
        this.s_content = s_content;
        this.s_time = s_time;
        this.s_datetime = s_datetime;
        this.s_gps_logitude = s_gps_logitude;
        this.s_gps_latitude = s_gps_latitude;
        this.s_gps_location = s_gps_location;
        this.s_gps_name = s_gps_name;
    }
}
