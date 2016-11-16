package dao;

import util.DatabaseTemplate;

/**
 * Created by Aleksandr_Shakhov on 15.11.16 18:23.
 */


public class FollowerDaoImp implements FollowerDao {

    @Override
    public void follow(int userID, int followerId) {
        String follow = "INSERT INTO follower (user_id, follower_id) VALUES (?, ?);";
        DatabaseTemplate.executeInsertQuery(follow, userID, followerId);
    }

    @Override
    public void unFollow(int userId, int followerId) {
        String unFollow = "DELETE FROM follower WHERE user_id = ? AND follower_id = ?;";
        DatabaseTemplate.executeInsertQuery(unFollow, userId, followerId);
    }
}
