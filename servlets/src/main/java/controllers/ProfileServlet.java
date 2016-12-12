package controllers;

import dao.PostDao;
import dao.PostDaoImp;
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
import java.time.LocalTime;
import java.util.List;


/**
 * Created by Aleksandr_Shakhov on 13.11.16 19:45.
 */

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
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

}
