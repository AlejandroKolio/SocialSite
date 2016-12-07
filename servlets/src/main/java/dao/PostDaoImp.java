package dao;

import model.Post;
import model.User;
import util.DatabaseTemplate;
import util.ObjectRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 20.11.16 21:14.
 */


public class PostDaoImp implements PostDao {

    private class PostRowMapper implements ObjectRowMapper<Post> {

        @Override
        public Post mapRowToObject(ResultSet resultSet) throws SQLException {

            Post post = new Post();

            post.setPostId(resultSet.getInt("post_id"));
            post.setBody(resultSet.getString("body"));
            post.setDate(resultSet.getTimestamp("post_time"));
            post.setUserId(resultSet.getInt("user_id"));
            post.setPicture(resultSet.getString("picture"));

            return post;
        }
    }

    private final String POST_INSERT      = "INSERT INTO post(user_id,body) VALUES(?,?)";
    private final String POST_KILL        = "DELETE FROM post WHERE post_id = ? and user_id = ?;";
    private final String GET_USER_POST    = "SELECT post_id, user_id, body, post_time, picture FROM mini_soc_network.post WHERE user_id=? ORDER BY post_time DESC;";
    private final String GET_POST_BY_ID   = "SELECT * FROM post WHERE post_id=?";
    private final String GET_FRIENDS_POST = "SELECT * FROM post WHERE user_id IN (SELECT follower_id from follower WHERE user_id=?) ORDER BY post_time DESC";
    private final String GET_LATEST_POSTS = "SELECT t1.* FROM post t1 WHERE user_id IN (SELECT follower_id FROM follower WHERE user_id = ?) " +
                                            "AND t1.post_time = (SELECT MAX(t2.post_time) " +
                                            "FROM post t2 WHERE t2.user_id = t1.user_id) " +
                                            "ORDER BY post_time DESC;";
    private final String POST_PICTURE     = "UPDATE post SET picture = ? WHERE post_id = ?;";
    private final String HAS_PICTURE      = "SELECT picture FROM post WHERE post_id = ?;";
    private final String GET_ALL_POSTS    = "SELECT * FROM post";

    @Override
    public void savePost(Post post) {
        DatabaseTemplate.executeInsertQuery(POST_INSERT, post.getUser().getUserId(), post.getBody());
    }

    @Override
    public void killPost(Post post) {
        DatabaseTemplate.executeInsertQuery(POST_KILL, post.getPostId(), post.getUserId());
    }

    @Override
    public List<Post> getPostByUserID(int userId) {
        return DatabaseTemplate.executeQueryForObject(new PostRowMapper(), GET_USER_POST, userId);
    }

    @Override
    public Post getPostByPostId(int postId) {
        List<Post> posts = DatabaseTemplate.executeQueryForObject(new PostRowMapper(), GET_POST_BY_ID, postId);
        if (posts != null) {
            return posts.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Post> getPostsOfFriends(int userId) {
        return DatabaseTemplate.executeQueryForObject(new PostRowMapper(), GET_FRIENDS_POST, userId);
    }

    @Override
    public List<Post> getLatestFriendsPosts(int userId) {
        return DatabaseTemplate.executeQueryForObject(new PostRowMapper(), GET_LATEST_POSTS, userId);
    }

    @Override
    public void doPicture(String path, int postId) {
        DatabaseTemplate.executeInsertQuery(POST_PICTURE, path, postId);
    }

    @Override
    public int hasPicture(int postId) {
        List<Post> pictures = DatabaseTemplate.executeQueryForObject(new PostRowMapper(), HAS_PICTURE, postId);
        if(pictures != null) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int postCounter(int userId) {
        String counter = "SELECT post_id FROM post WHERE user_id = ?;";
        return DatabaseTemplate.executeCount(counter, userId);
    }

    @Override
    public List<Post> getPosts() {
        return DatabaseTemplate.executeQueryForObject(new PostRowMapper(), GET_ALL_POSTS);
    }
}
