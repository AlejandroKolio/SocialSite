package services;

import model.User;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 04.12.16 12:15.
 */


public interface FollowerService {
    List<User> following(List<Integer> list);
}
