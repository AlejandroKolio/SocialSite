package dao;

import model.Post;
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
            post.setUserID(resultSet.getInt("user_id"));

            return post;
        }
    }

    private final String POST_INSERT      = "INSERT INTO post(user_id,body) VALUES(?,?)";
    private final String GET_USER_POST    = "SELECT * FROM post WHERE user_id=?";
    private final String GET_POST_BY_ID   = "SELECT * FROM post WHERE post_id=?";
    private final String GET_FRIENDS_POST = "SELECT * FROM post WHERE user_id IN (SELECT follower_id from follower WHERE user_id=?)";

    @Override
    public void savePost(Post post) {
        DatabaseTemplate.executeInsertQuery(POST_INSERT, post.getUser().getUserId(), post.getBody());
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
}
