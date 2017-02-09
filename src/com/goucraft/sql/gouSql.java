package com.goucraft.sql;

import code.nextgen.sqlibrary.database.DatabaseConnection;
import code.nextgen.sqlibrary.database.MySQLConnection;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/2/6 0006.
 */
public class gouSql {

    public static void startDatabaseConnection() {
        DatabaseConnection connection = new MySQLConnection("127.0.0.1", 3306, "gou", "root", "root");
        try {
            connection.openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        new gouPlayerFactory(connection);
        gouPlayerFactory.getInstance().buildSchema();
    }


}
