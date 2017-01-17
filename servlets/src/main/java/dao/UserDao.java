package dao;

import model.User;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:43.
 */

public interface UserDao {
    User getUserByEmail(String email);
    void saveUser(User user);
    List<User> getUserByName(String userName);
    List<User> getUsers();
    User getUserByUserId(int userId);
    User isRegisteredId(int userId);
    void updateAvatar(String path, int userId);

    void updateFirstName(String firstName, int userId);
    void updateLastName(String lastName, int userId);
    void updatePassword(String pass, int userId);

    String getAvatar(int userId);
}
