package com.seunggabi.mju_success_network.model.bean;

/**
 * Created by sohee on 2016-11-14.
 */

public class Schedule {
    private int id;
    private String name;
    private String intro;
    private String time;
    private String datetime;
    private String gps_logitude;
    private String gps_latitude;
    private String gps_location;
    private String gps_name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGps_name(String gps_name) {
        this.gps_name = gps_name;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setGps_logitude(String gps_logitude) {
        this.gps_logitude = gps_logitude;
    }

    public void setGps_latitude(String gps_latitude) {
        this.gps_latitude = gps_latitude;
    }

    public void setGps_location(String gps_location) {
        this.gps_location = gps_location;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntro() {
        return intro;
    }

    public String getTime() {
        return time;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getGps_logitude() {
        return gps_logitude;
    }

    public String getGps_latitude() {
        return gps_latitude;
    }

    public String getGps_location() {
        return gps_location;
    }

    public String getGps_name() {
        return gps_name;
    }

    public Schedule(String gps_latitude, int id, String name, String intro, String time, String datetime, String gps_logitude, String gps_location, String gps_name) {
        this.gps_latitude = gps_latitude;
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.time = time;
        this.datetime = datetime;
        this.gps_logitude = gps_logitude;
        this.gps_location = gps_location;
        this.gps_name = gps_name;
    }
}
