package model;

import dao.UserDaoImp;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

@Data
public class Comment {
    private int commentId;
    private int userId;
    private int postId;
    private String commentBody;
    private Timestamp date;
    private User user;

    public User getUser(){
        return new UserDaoImp().getUserByUserId(userId);
    }
    public String getTime() {
        return new SimpleDateFormat("MMMM d, yyyy hh:mm a", Locale.ENGLISH).format(date);
    }
}
