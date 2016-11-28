package com.seunggabi.mju_success_network.model.dao;

/**
 * Created by sohee on 2016-11-20.
 */

public class NoticeDAO extends DAO {
    protected static NoticeDAO dao;

    private NoticeDAO() {
        super("notice");
        this.create = "CREATE TABLE IF NOT EXISTS `"+ table +"` ( "
                + "p_id INTEGER PRIMARY KEY"
                + ", p_name VARCHAR(255) NOT NULL"
                + ");";
    }

    public static NoticeDAO getInstance() {
        if (dao == null) {
            try {
                dao = new NoticeDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
