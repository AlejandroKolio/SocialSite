package services;

import dao.CommentDao;
import dao.CommentDaoImp;
import model.Comment;
import model.Post;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 20.11.16 21:12.
 */


public class CommentServiceImp implements CommentService {

    CommentDao commentDao = new CommentDaoImp();

    @Override
    public void saveComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> getCommentOfPost(Post post) {
        return commentDao.getCommentByPostId(post.getPostId());
    }
}
