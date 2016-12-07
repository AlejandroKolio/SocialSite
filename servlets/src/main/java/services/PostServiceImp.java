package services;

import dao.*;
import javafx.collections.transformation.SortedList;
import model.Post;
import model.User;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void killPost(Post post) {
        postDao.killPost(post);
    }

    @Override
    public List<Post> getPostOfUser(User user) {
        List<Post> posts = postDao.getPostByUserID(user.getUserId());
        for (Post post : posts) {
            post.setUser(userDao.getUserByUserId(post.getUserId()));
            post.setComments(commentDao.getCommentByPostId(post.getPostId()));
        }
        return posts;
    }

    @Override
    public List<Post> getPostOfFriends(User user) {
        List<Post> posts = postDao.getPostsOfFriends(user.getUserId());
        for (Post post : posts) {
            post.setUser(userDao.getUserByUserId(post.getUserId()));
        }
        return posts;
    }

    @Override
    public List<Post> getLatestPosts(int userId) {
        return postDao.getLatestFriendsPosts(userId);
    }

    @Override
    public Post getPostById(int postId) {
        Post post = postDao.getPostByPostId(postId);

        post.setComments(commentDao.getCommentByPostId(postId));
        post.setUser(userDao.getUserByUserId(post.getUserId()));

        return post;
    }

    @Override
    public int hasPicture(int postId) {
        return postDao.hasPicture(postId);
    }
}
