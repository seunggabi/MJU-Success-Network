package com.seunggabi.mju_success_network.model.dao;

/**
 * Created by sohee on 2016-11-14.
 */

public class GroupDAO extends DAO {
    protected static GroupDAO dao;

    private GroupDAO() {
        super("group");
        this.create = "CREATE TABLE IF NOT EXISTS `"+ table +"` ("
                + "g_id INTEGER PRIMARY KEY"
                + ", g_name VARCHAR(255)"
                + ", g_tag TEXT"
                + ", g_intro TEXT"
                + ", g_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"
                + ", g_status CHAR(1) DEFAULT 'Y'"
                + ", g_hidden CHAR(1) DEFAULT 'Y'"
                + ", u_id INTEGER"
                + "); ";
    }

    public static GroupDAO getInstance() {
        if (dao == null) {
            try {
                dao = new GroupDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
