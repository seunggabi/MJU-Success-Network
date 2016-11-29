package com.seunggabi.mju_success_network.model.dao;

/**
 * Created by sohee on 2016-11-14.
 */

public class FileDAO extends DAO {
    protected static FileDAO dao;

    private FileDAO() {
        super("file");
        this.create = "CREATE TABLE IF NOT EXISTS `"+ table +"` ("
                + "f_id INTEGER PRIMARY KEY"
                + ", f_name VARCHAR(255) NOT NULL"
                + ", f_name_hash VARCHAR(255) NOT NULL"
                + ", f_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"
                + ", g_id INTEGER NOT NULL"
                + ", u_id INTEGER NOT NULL"

                + ", UNIQUE(g_id, u_id, f_time)"
                + ");";
    }

    public static FileDAO getInstance() {
        if (dao == null) {
            try {
                dao = new FileDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
