package com.seunggabi.mju_success_network.model;

/**
 * Created by sohee on 2016-11-01.
 */

public class DBConfiguration {
    public DBConfiguration(){};

    public static final String DATABASE_NAME = "seunggabi";
    public static final String SRC = "com.seunggabi.mju_success_network";
    public static final String URI = "content://"+ SRC;
    public static final String DATABASE_FILE_NAME = DATABASE_NAME + ".db";
}
