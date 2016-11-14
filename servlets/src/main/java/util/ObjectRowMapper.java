package util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

public interface ObjectRowMapper<E> {
    E mapRowToObject(ResultSet resultSet) throws SQLException;
}
