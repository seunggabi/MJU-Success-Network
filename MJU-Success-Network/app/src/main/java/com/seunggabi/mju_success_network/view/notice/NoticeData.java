package com.seunggabi.mju_success_network.view.notice;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by seunggabi on 2016-11-23.
 */

public class NoticeData implements Serializable {
    private int n_id;
    private String n_content;
    private Date n_time;
    private int m_id;
    private String m_name;

    public NoticeData() {}

    public NoticeData(int n_id, String n_content, Date n_time, int m_id, String m_name) {
        this.n_id = n_id;
        this.n_content = n_content;
        this.n_time = n_time;
        this.m_id = m_id;
        this.m_name = m_name;
    }

    public int getN_id() {
        return n_id;
    }

    public void setN_id(int n_id) {
        this.n_id = n_id;
    }

    public String getN_content() {
        return n_content;
    }

    public void setN_content(String n_content) {
        this.n_content = n_content;
    }

    public Date getN_time() {
        return n_time;
    }

    public void setN_time(Date n_time) {
        this.n_time = n_time;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }
}
