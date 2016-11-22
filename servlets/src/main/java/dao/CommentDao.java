package dao;

import model.Comment;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 20.11.16 21:17.
 */


public interface CommentDao {
    void saveComment(Comment comment);
    List<Comment> getCommentByPostId(int postId);
}
