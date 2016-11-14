package model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

public class Post {

    int postId;
    int userID;
    String body;
    Timestamp date;
    List<Comment> comments;
    User user;
    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userID=" + userID +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", comments=" + comments +
                ", user=" + user +
                '}';
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
