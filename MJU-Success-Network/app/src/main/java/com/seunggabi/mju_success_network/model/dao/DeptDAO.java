package com.seunggabi.mju_success_network.model.dao;

import android.database.Cursor;
import android.net.Uri;

import com.seunggabi.mju_success_network.model.DBConfiguration;
import com.seunggabi.mju_success_network.model.bean.Dept;

import java.util.ArrayList;

/**
 * Created by sohee on 2016-11-14.
 */

public class DeptDAO extends DAO {
    protected static DeptDAO dao;

    private DeptDAO() {
        super("dept");
        this.create = "CREATE TABLE IF NOT EXISTS `" +table+ "` ("
                + "d_id INTEGER PRIMARY KEY"
                + ", d_name VARCHAR(255) NOT NULL"
                + ");";
        this.insert = "INSERT INTO `" +table+ "` VALUES (1, '경력개발팀');"
                + " INSERT INTO `" +table+ "` VALUES (2,'학과사무실');"
                + " INSERT INTO `" +table+ "` VALUES (3,'컴퓨터공학과');"
                + " INSERT INTO `" +table+ "` VALUES (4,'정보통신공학과');"
                + " INSERT INTO `" +table+ "` VALUES (5,'기타');";
    }

    public static DeptDAO getInstance() {
        if (dao == null) {
            try {
                dao = new DeptDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }

    public ArrayList<Dept> getList() {
        ArrayList<Dept> list = new ArrayList<Dept>();

        Cursor c = cr.query(Uri.parse(DBConfiguration.URI+"/"+table),
                new String[] { "d_id", "d_name"},
                null, null,
                "d_id ASC");
        while(c.moveToNext()) {
            Dept d = new Dept(c.getInt(0), c.getString(1));
            list.add(d);
        }

        return list;
    }
}
