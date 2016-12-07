package model;

import dao.PostDao;
import dao.PostDaoImp;
import dao.UserDaoImp;
import lombok.Data;

/**
 * Created by Aleksandr_Shakhov on 06.12.16 20:42.
 */

@Data
public class Like {
    private int followerId;
    private int userId;
    private int postId;
    private int likeType;
    private User user;

    public User getUser() {
        Post post = new PostDaoImp().getPostByPostId(postId);
        int userId = post.getUserId();
        return user = new UserDaoImp().getUserByUserId(userId);
    }
}
