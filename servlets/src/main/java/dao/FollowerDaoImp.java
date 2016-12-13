package dao;

import model.User;
import util.DatabaseTemplate;

import java.util.List;

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

    @Override
    public boolean isFollower(int userId, int followerId) {
        String isFollower = "SELECT user_id, follower_id FROM follower WHERE user_id = ? AND follower_id = ?;";
        return DatabaseTemplate.executeIsFollower(isFollower, userId, followerId);
    }

    @Override
    public int followerCounter(int userId) {
        String counter = "SELECT follower_id FROM follower WHERE user_id = ?;";
        return DatabaseTemplate.executeCount(counter, userId);
    }

    @Override
    public List<Integer> following(int userId) {
        String query = "SELECT follower_id FROM follower WHERE user_id = ?;";
        return DatabaseTemplate.executeFollowing(query, userId);
    }

    @Override
    public List<Integer> follower(int followerId) {
        String query = "SELECT user_id FROM follower WHERE follower_id = ?;";
        return DatabaseTemplate.executeFollower(query, followerId);
    }
}
