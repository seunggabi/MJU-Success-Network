package com.seunggabi.mju_success_network.model.dao;

/**
 * Created by sohee on 2016-11-14.
 */

public class JoinDAO extends DAO {
    protected static JoinDAO dao;

    private JoinDAO() {
        super("join");
        this.create = "CREATE TABLE IF NOT EXISTS `"+ table +"` ("
                + "j_id INTEGER PRIMARY KEY"
                + ", j_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"
                + ", g_id INTEGER NOT NULL"
                + ", u_id INTEGER NOT NULL"
                + ", j_hidden CHAR(1) DEFAULT 'Y'"
                + ", j_alarm CHAR(1) DEFAULT 'Y'"

                + ", UNIQUE(g_id, u_id)"
                + ");";
    }

    public static JoinDAO getInstance() {
        if (dao == null) {
            try {
                dao = new JoinDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
