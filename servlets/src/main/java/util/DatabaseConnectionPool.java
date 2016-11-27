package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

public class DatabaseConnectionPool {

    private static DataSource dataSource;
    private static final String DRIVER_NAME;
    private static final String URL;
    private static final String USER_NAME;
    private static final String PASSWORD;

    static {
        final ResourceBundle config = ResourceBundle.getBundle("db");
        DRIVER_NAME = config.getString("jdbc.driver");
        URL         = config.getString("jdbc.url.address");
        USER_NAME   = config.getString("db.user");
        PASSWORD    = config.getString("db.password");

        try {
            dataSource = setUpDataSource();
        }
        catch (PropertyVetoException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                dataSource.getConnection().close();
            } catch (SQLException e) {
                e.getErrorCode();
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static DataSource setUpDataSource() throws PropertyVetoException {

        ComboPooledDataSource cpds = new ComboPooledDataSource();

        cpds.setDriverClass(DRIVER_NAME);
        cpds.setJdbcUrl(URL);
        cpds.setUser(USER_NAME);
        cpds.setPassword(PASSWORD);

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(100);
        cpds.setAcquireIncrement(1);
        //cpds.setUnreturnedConnectionTimeout(10);

        return cpds;
    }
}