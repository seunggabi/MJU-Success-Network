package com.seunggabi.mju_success_network.model.dao;

import android.database.Cursor;
import android.net.Uri;

import com.seunggabi.mju_success_network_fcm.model.DBConfiguration;
import com.seunggabi.mju_success_network_fcm.model.bean.Pos;

import java.util.ArrayList;

/**
 * Created by sohee on 2016-11-14.
 */

public class PosDAO extends DAO {
    protected static PosDAO dao;

    private PosDAO() {
        super("pos");
        this.create = "CREATE TABLE IF NOT EXISTS `"+ table +"` ( "
                + "p_id INTEGER PRIMARY KEY"
                + ", p_name VARCHAR(255) NOT NULL"
                + ");";
        this.insert = "INSERT INTO `"+ table +"` VALUES (1, '1학년');"
                + " INSERT INTO `"+ table +"` VALUES (2,'2학년');"
                + " INSERT INTO `"+ table +"` VALUES (3,'3학년');"
                + " INSERT INTO `"+ table +"` VALUES (4,'4학년');"
                + " INSERT INTO `"+ table +"` VALUES (5,'멘토');"
                + " INSERT INTO `"+ table +"` VALUES (6,'교수');"
                + " INSERT INTO `"+ table +"` VALUES (7,'경력개발팀');";
    }

    public static PosDAO getInstance() {
        if (dao == null) {
            try {
                dao = new PosDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }

    public ArrayList<Pos> getList() {
        ArrayList<Pos> list = new ArrayList<Pos>();

        Cursor c = cr.query(Uri.parse(DBConfiguration.URI+"/"+table),
                new String[] { "p_id", "p_name"},
                null, null,
                "p_id ASC");
        while(c.moveToNext()) {
            Pos p = new Pos(c.getInt(0), c.getString(1));
            list.add(p);
        }

        return list;
    }
}
