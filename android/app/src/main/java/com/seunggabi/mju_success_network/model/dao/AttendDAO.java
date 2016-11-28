package com.seunggabi.mju_success_network.model.dao;

/**
 * Created by sohee on 2016-11-14.
 */

public class AttendDAO extends DAO {
    protected static AttendDAO dao;

    private AttendDAO() {
        super("attend");
        this.create = "CREATE TABLE IF NOT EXISTS `"+ table +"` ("
                + "a_id INTEGER PRIMARY KEY"
                + ", a_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"
                + ", s_id INTEGER NOT NULL"
                + ", u_id INTEGER NOT NULL"

                + ", UNIQUE(s_id, u_id, a_time)"
                + ");";
    }

    public static AttendDAO getInstance() {
        if (dao == null) {
            try {
                dao = new AttendDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
