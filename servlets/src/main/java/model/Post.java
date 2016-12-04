package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import services.PostService;
import services.PostServiceImp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

@Data
public class Post implements Serializable {
    private int postId;
    private int userID;
    private String body;
    private Timestamp date;
    private String picture;
    private List<Comment> comments;
    private User user;
}
