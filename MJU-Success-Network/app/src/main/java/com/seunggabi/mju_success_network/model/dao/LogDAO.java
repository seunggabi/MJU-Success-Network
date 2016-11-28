package com.seunggabi.mju_success_network.model.dao;

/**
 * Created by sohee on 2016-11-14.
 */

public class LogDAO extends DAO {
    protected static LogDAO dao;

    private LogDAO() {
        super("log");
        this.create = "CREATE TABLE IF NOT EXISTS `"+ table +"` ("
                + "l_id INTEGER PRIMARY KEY"
                + ", l_content TEXT NOT NULL"
                + ", l_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"
                + ", g_id INTEGER NOT NULL"
                + ", u_id INTEGER NOT NULL"

                + ", UNIQUE(g_id, u_id, l_time)"
                + ");";
    }

    public static LogDAO getInstance() {
        if (dao == null) {
            try {
                dao = new LogDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
