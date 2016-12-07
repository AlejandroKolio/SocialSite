package dao;

import model.Post;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 20.11.16 21:14.
 */


public interface PostDao {
    void savePost(Post post);
    void killPost(Post post);
    List<Post> getPostByUserID(int userId);
    List<Post> getPostsOfFriends(int userId);
    List<Post> getLatestFriendsPosts(int userId);
    Post getPostByPostId(int postId);
    void doPicture(String path, int postId);
    int hasPicture(int postId);
    int postCounter(int userId);
}
