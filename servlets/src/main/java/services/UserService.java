package services;

import model.User;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:38.
 */


public interface UserService {
    void saveUser(User user);
    boolean verifyNewUser(User user);
    User verifyUser(User user);
    List<User> getUsers();
    String getAvatar(User user);
}
