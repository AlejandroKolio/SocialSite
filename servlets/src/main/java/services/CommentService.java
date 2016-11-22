package services;

import model.Comment;
import model.Post;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 20.11.16 21:07.
 */


public interface CommentService {
    void saveComment(Comment comment);
    List<Comment> getCommentOfPost(Post post);
}
