package com.seunggabi.mju_success_network.model.dao;

/**
 * Created by sohee on 2016-11-14.
 */

public class UserDAO extends DAO {
    protected static UserDAO dao;

    private UserDAO() {
        super("user");
        this.create = "CREATE TABLE IF NOT EXISTS `"+ table +"` ("
                + "u_id INTEGER PRIMARY KEY"
                + ", u_email VARCHAR(255)"
                + ", u_password VARCHAR(255)"
                + ", u_name VARCHAR(255)"
                + ", u_phone VARCHAR(255)"
                + ", u_intro TEXT"
                + ", u_level INTEGER"
                + ", token VARCHAR(200) NOT NULL"
                + ", d_id INTEGER"
                + ", p_id INTEGER"

                + ", UNIQUE(u_email)"
                + ", UNIQUE(token)"
                + ");";
    }

    public static UserDAO getInstance() {
        if (dao == null) {
            try {
                dao = new UserDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
