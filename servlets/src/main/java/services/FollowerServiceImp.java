package services;

import dao.UserDaoImp;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 04.12.16 12:16.
 */


public class FollowerServiceImp implements FollowerService {

    @Override
    public List<User> following(List<Integer> list) {
        List<User> following = new ArrayList<>();
        for(Integer i : list) {
            following.add(new UserDaoImp().getUserByUserId(i));
        }
        return following;
    }
}
