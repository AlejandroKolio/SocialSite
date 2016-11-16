package model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

@Data
public class Post {
    private int postId;
    private int userID;
    private String body;
    private Timestamp date;
    private List<Comment> comments;
    private User user;
}
