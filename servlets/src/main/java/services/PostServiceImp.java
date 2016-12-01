package services;

import dao.*;
import model.Post;
import model.User;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 20.11.16 21:02.
 */


public class PostServiceImp implements PostService {

    PostDao postDao = new PostDaoImp();
    UserDao userDao = new UserDaoImp();
    CommentDao commentDao = new CommentDaoImp();

    @Override
    public void savePost(Post post) {
        postDao.savePost(post);
    }

    @Override
    public void killPost(Post post) { postDao.killPost(post); }

    @Override
    public List<Post> getPostOfUser(User user) {
        List<Post> posts = postDao.getPostByUserID(user.getUserId());
        for (Post post : posts) {
            post.setUser(userDao.getUserByUserId(post.getUserID()));
            post.setComments(commentDao.getCommentByPostId(post.getPostId()));
        }
        return posts;
    }

    @Override
    public List<Post> getPostOfFriends(User user) {
        List<Post> posts = postDao.getPostsOfFriends(user.getUserId());
        for (Post post : posts) {
            post.setUser(userDao.getUserByUserId(post.getUserID()));
        }
        return posts;
    }

    @Override
    public Post getPostById(int postId) {
        Post post = postDao.getPostByPostId(postId);

        post.setComments(commentDao.getCommentByPostId(postId));
        post.setUser(userDao.getUserByUserId(post.getUserID()));

        return post;
    }
}
