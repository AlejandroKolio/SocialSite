package services;

import model.Like;
import model.Post;

import java.util.List;


/**
 * Created by Aleksandr_Shakhov on 06.12.16 22:27.
 */


public interface LikeService {
    void likePost(Post post, int followerId);
    void dislikePost(Post post, int followerId);
}
