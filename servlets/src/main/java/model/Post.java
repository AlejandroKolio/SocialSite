package model;

import dao.LikeDaoImp;
import dao.UserDaoImp;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import services.PostService;
import services.PostServiceImp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

@Data
public class Post implements Serializable {
    private int postId;
    private int userId;
    private String body;
    private Timestamp date;
    private String picture;
    private List<Comment> comments;
    private int likes;
    private int dislikes;
    private User user;
    private User friend;

    public User getFriend(){
        return new UserDaoImp().getUserByUserId(userId);
    }

    public int getLikes() {
        return new LikeDaoImp().getLikesByPostId(postId).size();
    }

    public int getDislikes() {
        return new LikeDaoImp().getDislikesByPostId(postId).size();
    }

    public String getDate() {
        return new SimpleDateFormat("MMMM d, yyyy hh:mm a", Locale.ENGLISH).format(date);
    }

}
