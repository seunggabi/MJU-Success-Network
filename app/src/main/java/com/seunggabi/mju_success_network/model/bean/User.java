package com.seunggabi.mju_success_network.model.bean;

/**
 * Created by sohee on 2016-11-14.
 */

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String intro;
    private int level;
    private int d_id;
    private int p_id;

    public User(int id, String email, String password, String name, String phone, String intro, int level, int d_id, int p_id) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.intro = intro;
        this.level = level;
        this.d_id = d_id;
        this.p_id = p_id;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getIntro() {
        return intro;
    }

    public int getLevel() {
        return level;
    }

    public int getD_id() {
        return d_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }
}
