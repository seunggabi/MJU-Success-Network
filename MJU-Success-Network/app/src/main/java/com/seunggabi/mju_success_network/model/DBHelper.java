package com.seunggabi.mju_success_network.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sohee on 2016-11-01.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DBConfiguration.DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        DBTool.getInstance().dropTables(db);
        DBTool.getInstance().createTables(db);
        DBTool.getInstance().insertTables(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DBTool.getInstance().dropTables(db);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DBTool.getInstance().dropTables(db);
        onCreate(db);
    }
}
