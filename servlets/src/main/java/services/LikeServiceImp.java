package services;

import dao.LikeDao;
import dao.LikeDaoImp;
import model.Post;

/**
 * Created by Aleksandr_Shakhov on 06.12.16 22:27.
 */


public class LikeServiceImp implements LikeService {

    private LikeDao likeDao = new LikeDaoImp();

    @Override
    public void likePost(Post post, int followerId) {
        likeDao.like(post, followerId);
    }

    @Override
    public void dislikePost(Post post, int followerId) {
        likeDao.dislike(post, followerId);
    }
}
