package services;

import model.Post;
import model.User;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 20.11.16 20:59.
 */


public interface PostService {
    void savePost(Post post);
    void killPost(Post post);
    List<Post> getPostOfUser(User user);
    List<Post> getPostOfFriends(User user);
    List<Post> getLatestPosts(int userId);
    Post getPostById(int postId);
    int hasPicture(int postId);
}
