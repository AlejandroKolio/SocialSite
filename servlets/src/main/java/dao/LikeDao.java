package dao;

import model.Comment;
import model.Like;
import model.Post;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 06.12.16 20:44.
 */


public interface LikeDao {
    void like(Post post, int followerId);
    void dislike(Post post, int followerId);
    List<Like> getLikes(int postId);
    List<Like> getDislikes(int postId);
    int getCountLikes(int postId);
    int getCountDislikes(int postId);
    List<Like> getLikeByUserID(int userId);
}
