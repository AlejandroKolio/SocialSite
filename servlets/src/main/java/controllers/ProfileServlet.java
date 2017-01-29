package controllers;

import dao.PostDao;
import dao.PostDaoImp;
import dao.UserDao;
import dao.UserDaoImp;
import model.Post;
import model.User;
import services.PostService;
import services.PostServiceImp;
import services.UserService;
import services.UserServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Aleksandr_Shakhov on 13.11.16 19:45.
 */

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile", "/editprofile"})
public class ProfileServlet extends HttpServlet {

    LocalTime time = LocalTime.now();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PostDao postDao = new PostDaoImp();
        PostService postService = new PostServiceImp();
        UserService userService = new UserServiceImp();

        User user = (User)request.getSession().getAttribute("User");
        String avatar = userService.getAvatar(user);
        List<Post> postsOfFriends = postDao.getPostsOfFriends(user.getUserId());
        List<Post> latestPosts    = postService.getLatestPosts(user.getUserId());

        request.setAttribute("avatar", avatar);
        request.setAttribute("postOfFriends", postsOfFriends);
        request.setAttribute("latestPosts", latestPosts);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        UserDao userDao = new UserDaoImp();
        User user = (User) request.getSession().getAttribute("User");

        String message = null;

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(!firstName.equals(null) && !firstName.equals("")
                && !lastName.equals(null) && !lastName.equals("")
                && !email.equals(null) && !email.equals("")
                && !password.equals(null) && !password.equals("")) {

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);

            userDao.updateProfile(user.getUserId(), user);

            message = "Your personal information has been updated";
            request.getSession().setAttribute("message", message);
            doGet(request, response);
        }
    }

    // validate first name
    private static boolean validateFirstName( String firstName ) {
        return firstName.matches( "[A-Z][a-zA-Z]*" );
    }

    // validate last name
    private static boolean validateLastName( String lastName ) {
        return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
    }

    //validate email
    private static boolean validateEmail(String email) {
        String EMAIL_PATTERN = "^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";
        Matcher check = Pattern.compile(EMAIL_PATTERN).matcher(email);
        return check.matches();
    }

    //validate password
    private static boolean validatePassword(String pass) {
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        Matcher check = Pattern.compile(PASSWORD_PATTERN).matcher(pass);
        return check.matches();
    }
}
