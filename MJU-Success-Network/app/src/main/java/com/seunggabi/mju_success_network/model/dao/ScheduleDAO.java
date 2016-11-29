package com.seunggabi.mju_success_network.model.dao;

/**
 * Created by sohee on 2016-11-14.
 */

public class ScheduleDAO extends DAO {
    protected static ScheduleDAO dao;

    private ScheduleDAO() {
        super("schedule");
        this.create = "CREATE TABLE IF NOT EXISTS `"+ table +"` ("
                + "s_id INTEGER PRIMARY KEY"
                + ", s_name VARCHAR(255) NOT NULL"
                + ", s_intro TEXT  NOT NULL"
                + ", s_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"
                + ", s_datetime DATETIME NOT NULL"
                + ", s_gps_logitude FLOAT (10, 6) NOT NULL"
                + ", s_gps_latitude FLOAT (10, 6) NOT NULL"
                + ", s_gps_location VARCHAR(255) NOT NULL"
                + ", g_id INTEGER NOT NULL"
                + ", u_id INTEGER NOT NULL"

                + ", UNIQUE(g_id, u_id, s_time)"
                + ");";
    }

    public static ScheduleDAO getInstance() {
        if (dao == null) {
            try {
                dao = new ScheduleDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
