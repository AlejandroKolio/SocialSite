package dao;

import model.Comment;
import model.User;
import util.DatabaseTemplate;
import util.ObjectRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 20.11.16 21:18.
 */


public class CommentDaoImp implements CommentDao {

    private final String COMMENT_INSERT         = "INSERT INTO post_comment(post_id,user_id,body) values(?,?,?)";
    private final String GET_COMMENT_BY_POST_ID = "SELECT * FROM post_comment WHERE post_id = ?";

    private class CommentRowMapper implements ObjectRowMapper<Comment> {

        @Override
        public Comment mapRowToObject(ResultSet resultSet) throws SQLException {

            Comment comment = new Comment();
            comment.setCommentId(resultSet.getInt("comment_id"));
            comment.setPostId(resultSet.getInt("post_id"));
            comment.setUserId(resultSet.getInt("user_id"));
            comment.setCommentBody(resultSet.getString("body"));
            comment.setDate(resultSet.getTimestamp("comment_time"));
            return comment;
        }
    }

    @Override
    public void saveComment(Comment comment) {
        DatabaseTemplate.executeInsertQuery(COMMENT_INSERT, comment.getPostId(), comment.getUserId(), comment.getCommentBody());
    }

    @Override
    public List<Comment> getCommentByPostId(int postId) {
        return DatabaseTemplate.executeQueryForObject(new CommentRowMapper(), GET_COMMENT_BY_POST_ID, postId);
    }
}
