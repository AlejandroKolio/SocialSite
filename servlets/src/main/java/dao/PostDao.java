package dao;

import model.Post;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 20.11.16 21:14.
 */


public interface PostDao {
    void savePost(Post post);
    List<Post> getPostByUserID(int userId);
    List<Post> getPostsOfFriends(int userId);
    Post getPostByPostId(int postId);
}
