package controllers;

import dao.PostDao;
import dao.PostDaoImp;
import model.Post;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.logging.SimpleFormatter;


/**
 * Created by Aleksandr_Shakhov on 13.11.16 19:45.
 */

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    LocalTime time = LocalTime.now();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PostDao postDao = new PostDaoImp();

        User user = (User)request.getSession().getAttribute("User");
        String avatar = user.getAvatar();
        List<Post> postsOfFriends = postDao.getPostsOfFriends(user.getUserId());

        request.setAttribute("avatar", avatar);
        request.setAttribute("postOfFriends", postsOfFriends);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

}
