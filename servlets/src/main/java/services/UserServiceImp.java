package services;

import dao.UserDao;
import dao.UserDaoImp;
import model.User;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */


public class UserServiceImp implements UserService {

    private UserDao userDao;

    public UserServiceImp() {
        userDao = new UserDaoImp();
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public boolean verifyNewUser(User user) {
        return this.userDao.getUserByEmail(user.getEmail()) == null;
    }

    @Override
    public User verifyUser(User user) {
        User verifiedUser = userDao.getUserByEmail(user.getEmail());
        if (verifiedUser != null && verifiedUser.getPassword().equals(user.getPassword())) {
            return verifiedUser;
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
