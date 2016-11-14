package model;

import java.sql.Timestamp;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

public class Comment {
    int commentId;
    int userId;
    int postId;
    String commentBody;
    Timestamp time;

    public int getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", postId=" + postId +
                ", commentBody='" + commentBody + '\'' +
                ", time=" + time +
                '}';
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
