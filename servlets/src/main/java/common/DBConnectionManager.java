package common;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Aleksandr_Shakhov on 31.10.16 21:06.
 */


public class DBConnectionManager {

    private ConnectionPool connectionPool;

    public DBConnectionManager() throws ClassNotFoundException, SQLException {
        String FILE_PATH = "/Users/alexandershakhov/Developer/SocialNetwork/jdbc/src/main/resources/db.properties";
        this.connectionPool = ConnectionPool.create(FILE_PATH);
    }

    public Connection getConnection() {
        return connectionPool.get();
    }
}
