package model;

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
}
