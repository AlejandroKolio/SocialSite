package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:42.
 */

@Setter
@Getter
@Data
public class User implements Serializable {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
