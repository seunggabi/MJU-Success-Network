package com.seunggabi.mju_success_network.model;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;

import com.seunggabi.mju_success_network.model.dao.AttendDAO;
import com.seunggabi.mju_success_network.model.dao.DAO;
import com.seunggabi.mju_success_network.model.dao.DeptDAO;
import com.seunggabi.mju_success_network.model.dao.FileDAO;
import com.seunggabi.mju_success_network.model.dao.GroupDAO;
import com.seunggabi.mju_success_network.model.dao.JoinDAO;
import com.seunggabi.mju_success_network.model.dao.LogDAO;
import com.seunggabi.mju_success_network.model.dao.NoticeDAO;
import com.seunggabi.mju_success_network.model.dao.PosDAO;
import com.seunggabi.mju_success_network.model.dao.ScheduleDAO;
import com.seunggabi.mju_success_network.model.dao.UserDAO;

import java.util.ArrayList;

/**
 * Created by sohee on 2016-11-20.
 */

public class DBTool {
    private static DBTool tool;
    private ArrayList<DAO> daoList;

    private DBTool() {};

    public static DBTool getInstance() {
        if(tool == null) {
            tool = new DBTool();
            tool.daoList = new ArrayList<DAO>();
            tool.daoList.add(AttendDAO.getInstance());
            tool.daoList.add(DeptDAO.getInstance());
            tool.daoList.add(FileDAO.getInstance());
            tool.daoList.add(GroupDAO.getInstance());
            tool.daoList.add(JoinDAO.getInstance());
            tool.daoList.add(LogDAO.getInstance());
            tool.daoList.add(PosDAO.getInstance());
            tool.daoList.add(ScheduleDAO.getInstance());
            tool.daoList.add(UserDAO.getInstance());
            tool.daoList.add(NoticeDAO.getInstance());
        }
        return tool;
    }

    public void setContentResolver(ContentResolver cr) {
        for(DAO dao : daoList) {
            dao.setCr(cr);
        }
    }

    public void dropTables(SQLiteDatabase db) {
        for(DAO dao : daoList) {
            db.execSQL(dao.getDrop());
        }
    }

    public void createTables(SQLiteDatabase db) {
        for(DAO dao : daoList) {
            db.execSQL(dao.getCreate());
        }
    }

    public void insertTables(SQLiteDatabase db) {
        ArrayList<DAO> tableList = new ArrayList<DAO>();
        tableList.add(DeptDAO.getInstance());
        tableList.add(PosDAO.getInstance());

        for(DAO dao : tableList) {
            String[] queryList = dao.getInsert().split(";");
            for (String query : queryList)
                db.execSQL(query);
        }
    }
}
