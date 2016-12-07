package dao;

import controllers.RegistrationServlet;
import model.Comment;
import model.Like;
import model.Post;
import model.User;
import util.DatabaseTemplate;
import util.ObjectRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Aleksandr_Shakhov on 06.12.16 20:45.
 */


public class LikeDaoImp implements LikeDao {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LikeDaoImp.class);

    private final String INSERT_LIKE    = "INSERT INTO likes(follower_id, user_id, post_id, like_type) values(?,?,?,1)";
    private final String INSERT_DISLIKE = "INSERT INTO likes(follower_id, user_id, post_id, like_type) values(?,?,?,0)";
    private final String GET_LIKES      = "SELECT * FROM likes WHERE post_id = ? AND like_type = 1;";
    private final String GET_DISLIKE    = "SELECT * FROM likes WHERE post_id = ? AND like_type = 0;";
    private final String COUNT_LIKES    = "SELECT post_id, COUNT(*) AS likesQuantity FROM likes WHERE like_type = 1 GROUP BY post_id ORDER BY likesQuantity DESC;";
    private final String COUNT_DISLIKES = "SELECT COUNT(like_type) FROM likes WHERE like_type = 0 AND post_id = ?;";
    private final String GET_LIKE_POST  = "SELECT * FROM likes WHERE user_id=?;";

    private class LikeRowMapper implements ObjectRowMapper<Like> {

        @Override
        public Like mapRowToObject(ResultSet resultSet) throws SQLException {
            Like like = new Like();
            like.setFollowerId(resultSet.getInt("follower_id"));
            like.setUserId(resultSet.getInt("user_id"));
            like.setPostId(resultSet.getInt("post_id"));
            like.setLikeType(resultSet.getInt("like_type"));
            return like;
        }
    }

    @Override
    public void like(Post post, int followerId) {
        try {
            DatabaseTemplate.executeInsertQuery(INSERT_LIKE, followerId, post.getUserId(), post.getPostId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.trace("Duplicate like entry");
        }
    }

    @Override
    public void dislike(Post post, int followerId) {
        try {
            DatabaseTemplate.executeInsertQuery(INSERT_DISLIKE, followerId, post.getUserId(), post.getPostId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.trace("Duplicate dislike entry");
        }
    }

    @Override
    public List<Like> getLikesByPostId(int postId) {
        return DatabaseTemplate.executeQueryForObject(new LikeRowMapper(), GET_LIKES, postId);
    }

    @Override
    public List<Like> getDislikesByPostId(int postId) {
        return DatabaseTemplate.executeQueryForObject(new LikeRowMapper(), GET_DISLIKE, postId);
    }

    @Override
    public Map<Post,Integer> getAllCountLikes() {
        Map<Integer, Integer> map = DatabaseTemplate.executeLikesCounter(COUNT_LIKES);
        Map<Post, Integer> result = new HashMap<>();
        PostDao postDao = new PostDaoImp();
        for(Map.Entry<Integer, Integer> m : map.entrySet()) {
            Post post = postDao.getPostByPostId(m.getKey());
            int like = m.getValue();
            result.put(post, like);
        }
        return result;
    }

    @Override
    public List<Like> getAllCountDislikes() {
        return DatabaseTemplate.executeQueryForObject(new LikeRowMapper(), COUNT_DISLIKES);
    }
}
