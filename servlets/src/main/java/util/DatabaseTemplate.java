package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DatabaseConnectionPool.getConnection;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

public class DatabaseTemplate {

    private static Connection connection = null;

    public static void execute(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static <E> List<E> executeQueryForObject(ObjectRowMapper<E> objectRowMapper, String query, Object... objects) {

        try (PreparedStatement preparedStatement = createPreparedStatement(query, objects);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<E> listOfE = new ArrayList<>();
            while (resultSet.next()) {
                listOfE.add(objectRowMapper.mapRowToObject(resultSet));
            }
            return listOfE;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public static void executeInsertQuery(String query, Object... parameters) {
        try (PreparedStatement preparedStatement = createPreparedStatement(query, parameters)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
    }

    public static boolean executeIsFollower(String query, int one_id, int two_id) {
        try (PreparedStatement preparedStatement = createPreparedStatement(query, one_id, two_id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
    }

    private static PreparedStatement createPreparedStatement(String query, Object... parameters) {
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;

            for (Object parameter : parameters) {
                if (parameter instanceof String) {
                    preparedStatement.setString(i, (String) parameter);
                } else if (parameter instanceof Integer) {
                    preparedStatement.setInt(i, (Integer) parameter);
                } else if (parameter instanceof Long) {
                    preparedStatement.setLong(i, (Long) parameter);
                } else if (parameter instanceof Float) {
                    preparedStatement.setFloat(i, (Float) parameter);
                } else if (parameter instanceof Double) {
                    preparedStatement.setDouble(i, (Double) parameter);
                } else if (parameter instanceof Date) {
                    preparedStatement.setDate(i, (Date) parameter);
                } else if (parameter instanceof Blob) {
                    preparedStatement.setBlob(i, (Blob) parameter);
                }
                i++;
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void closeConnection(Connection conToClose) {
        try {
            conToClose.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}