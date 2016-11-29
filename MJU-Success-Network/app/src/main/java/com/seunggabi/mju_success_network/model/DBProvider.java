package com.seunggabi.mju_success_network.model;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.seunggabi.mju_success_network.model.dao.AttendDAO;
import com.seunggabi.mju_success_network.model.dao.DeptDAO;
import com.seunggabi.mju_success_network.model.dao.FileDAO;
import com.seunggabi.mju_success_network.model.dao.GroupDAO;
import com.seunggabi.mju_success_network.model.dao.JoinDAO;
import com.seunggabi.mju_success_network.model.dao.LogDAO;
import com.seunggabi.mju_success_network.model.dao.NoticeDAO;
import com.seunggabi.mju_success_network.model.dao.PosDAO;
import com.seunggabi.mju_success_network.model.dao.ScheduleDAO;
import com.seunggabi.mju_success_network.model.dao.UserDAO;

/**
 * Created by sohee on 2016-11-01.
 */

public class DBProvider extends ContentProvider {
    public DBProvider() {}

    public static final String ID = "_id";
    private static final UriMatcher uriMatcher;
    private SQLiteDatabase mDB = null;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DBConfiguration.SRC, AttendDAO.getInstance().getTable(), 1);
        uriMatcher.addURI(DBConfiguration.SRC, DeptDAO.getInstance().getTable(), 2);
        uriMatcher.addURI(DBConfiguration.SRC, FileDAO.getInstance().getTable(), 3);
        uriMatcher.addURI(DBConfiguration.SRC, GroupDAO.getInstance().getTable(), 4);
        uriMatcher.addURI(DBConfiguration.SRC, JoinDAO.getInstance().getTable(), 5);
        uriMatcher.addURI(DBConfiguration.SRC, LogDAO.getInstance().getTable(), 6);
        uriMatcher.addURI(DBConfiguration.SRC, PosDAO.getInstance().getTable(), 7);
        uriMatcher.addURI(DBConfiguration.SRC, ScheduleDAO.getInstance().getTable(), 8);
        uriMatcher.addURI(DBConfiguration.SRC, UserDAO.getInstance().getTable(), 9);
        uriMatcher.addURI(DBConfiguration.SRC, NoticeDAO.getInstance().getTable(), 10);
    }

    @Override
    public boolean onCreate() {
        DBHelper mDBHelper = new DBHelper(getContext());
        mDB = mDBHelper.getWritableDatabase();
        mDBHelper.onCreate(mDB);

        return (mDB == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String tableName = getTableName(uri);
        qb.setTables(tableName);

        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) orderBy = ID;
        else orderBy = sortOrder;

        Cursor c = qb.query(mDB, projection, selection, selectionArgs, null, null, orderBy);

        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        return mDB.delete(tableName, selection, null);
    }

    @Override
    public String getType(Uri uri) {
        return "type";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = getTableName(uri);
        mDB.insert(tableName, null, values);
        return uri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        return mDB.update(tableName, values, selection, null);
    }

    public String getTableName(Uri uri) {
        String tableName = null;
        switch(uriMatcher.match(uri)) {
            case 1: tableName = AttendDAO.getInstance().getTable(); break;
            case 2: tableName = DeptDAO.getInstance().getTable();   break;
            case 3: tableName = FileDAO.getInstance().getTable();   break;
            case 4: tableName = GroupDAO.getInstance().getTable();  break;
            case 5: tableName = JoinDAO.getInstance().getTable();   break;
            case 6: tableName = LogDAO.getInstance().getTable();    break;
            case 7: tableName = PosDAO.getInstance().getTable();    break;
            case 8: tableName = ScheduleDAO.getInstance().getTable();   break;
            case 9: tableName = UserDAO.getInstance().getTable();    break;
            default: tableName = "";
        }
        return tableName;
    }
}
