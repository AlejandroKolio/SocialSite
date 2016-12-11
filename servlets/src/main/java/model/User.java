package model;

import dao.PostDao;
import dao.PostDaoImp;
import dao.UserDaoImp;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import services.PostService;
import services.PostServiceImp;

import java.io.Serializable;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

@Data
public class User implements Comparable<User> {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String avatar;
    private int posts;

    public int getPosts() {
        return new PostDaoImp().postCounter(getUserId());
    }

    @Override
    public int compareTo(User user) {
        if(this.getPosts() >= user.getPosts()) {
            return -1;
        }
        if(this.getPosts() <= user.getPosts()) {
            return 1;
        }
        else return 0;
    }
}
