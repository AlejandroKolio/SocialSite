package dao;

/**
 * Created by Aleksandr_Shakhov on 15.11.16 18:21.
 */


public interface FollowerDao {
    void follow(int userID, int followerId);
    void unFollow(int userId, int followerId);
    boolean isFollower(int userId, int followerId);
    int followerCounter(int userdId);
}
