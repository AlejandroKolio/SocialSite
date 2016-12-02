package dao;

import model.User;
import util.DatabaseTemplate;
import util.ObjectRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:44.
 */

public class UserDaoImp implements UserDao {

    private class UserRowMapper implements ObjectRowMapper<User> {

        @Override
        public User mapRowToObject(ResultSet resultSet) throws SQLException {
            User user = new User();

            user.setUserId(resultSet.getInt("user_id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setAvatar(resultSet.getString("avatar"));

            return user;
        }
    }

    private final String INSERT_USER = "INSERT INTO user(first_name, last_name, email, password) VALUES (?,?,?,?)";
    private final String GET_USER_BY_NAME = "SELECT * FROM user WHERE name = ?;";
    private final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?;";
    private final String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?;";
    private final String GET_ALL_USERS = "SELECT * FROM user";
    private final String IS_REGISTERED = "SELECT * FROM user WHERE user_id = ?;";
    private final String UPDATE_AVATAR = "UPDATE user SET avatar = ? WHERE user_id = ?;";


    @Override
    public User getUserByEmail(String email) {

        List<User> userList = DatabaseTemplate.executeQueryForObject(new UserRowMapper(), GET_USER_BY_EMAIL, email);

        if (userList.size() != 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        DatabaseTemplate.executeInsertQuery(INSERT_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword());

    }

    @Override
    public List<User> getUserByName(String userName) {
        return DatabaseTemplate.executeQueryForObject(new UserRowMapper(), GET_USER_BY_NAME, userName);
    }

    @Override
    public List<User> getUsers() {
        return DatabaseTemplate.executeQueryForObject(new UserRowMapper(), GET_ALL_USERS);
    }

    @Override
    public User getUserByUserId(int userId) {
        List<User> userList = DatabaseTemplate.executeQueryForObject(new UserRowMapper(), GET_USER_BY_ID, userId);

        if (userList.size() != 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User isRegisteredId(int userId) {
        List<User> user = DatabaseTemplate.executeQueryForObject(new UserRowMapper(), IS_REGISTERED, userId);
        if (user.size() != 0) {
            return user.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void updateAvatar(String path, int userId) {
        DatabaseTemplate.executeInsertQuery(UPDATE_AVATAR, path, userId);
    }
}
