package com.seunggabi.mju_success_network.model.dao;

import android.content.ContentResolver;

/**
 * Created by sohee on 2016-11-14.
 */

public class DAO {
    protected ContentResolver cr;
    protected String table = "";
    protected String create = "";
    protected String insert = "";

    public String getCreate() {
        return create;
    }

    public String getDrop() {
        return "DROP TABLE IF EXISTS `" + table + "`";
    }

    public String getInsert() {
        return insert;
    }

    public String getTable() {
        return table;
    }

    public ContentResolver getCr() {
        return cr;
    }

    public DAO(String table) {
        this.table = table;
    }

    public void setTABLE(String table) {
        this.table = table;
    }

    public void setCr(ContentResolver cr) {
        this.cr = cr;
    }
}
