package dao;

import model.Comment;
import model.Like;
import model.Post;

import java.util.List;
import java.util.Map;

/**
 * Created by Aleksandr_Shakhov on 06.12.16 20:44.
 */


public interface LikeDao {
    void like(Post post, int followerId);
    void dislike(Post post, int followerId);
    List<Like> getLikesByPostId(int postId);
    List<Like> getDislikesByPostId(int postId);
    Map<Post,Integer> getAllCountLikes();
    List<Like> getAllCountDislikes();
}
