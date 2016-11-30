package com.seunggabi.mju_success_network.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sohee on 2016-11-14.
 */

public class Schedule implements Serializable {
    private int id;
    private int u_id;
    private int g_id;
    private String name;
    private String content;
    private Date time;
    private Date datetime;
    private String gps_logitude;
    private String gps_latitude;
    private String gps_location;
    private String gps_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getGps_logitude() {
        return gps_logitude;
    }

    public void setGps_logitude(String gps_logitude) {
        this.gps_logitude = gps_logitude;
    }

    public String getGps_latitude() {
        return gps_latitude;
    }

    public void setGps_latitude(String gps_latitude) {
        this.gps_latitude = gps_latitude;
    }

    public String getGps_location() {
        return gps_location;
    }

    public void setGps_location(String gps_location) {
        this.gps_location = gps_location;
    }

    public String getGps_name() {
        return gps_name;
    }

    public void setGps_name(String gps_name) {
        this.gps_name = gps_name;
    }

    public Schedule(int id, int u_id, int g_id, String name, String content, Date time, Date datetime, String gps_logitude, String gps_latitude, String gps_location, String gps_name) {

        this.id = id;
        this.u_id = u_id;
        this.g_id = g_id;
        this.name = name;
        this.content = content;
        this.time = time;
        this.datetime = datetime;
        this.gps_logitude = gps_logitude;
        this.gps_latitude = gps_latitude;
        this.gps_location = gps_location;
        this.gps_name = gps_name;
    }

    public Schedule(){}
}
