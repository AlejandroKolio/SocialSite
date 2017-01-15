package controllers;

import dao.*;
import model.Post;
import model.User;
import org.apache.log4j.Logger;
import services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Created by Aleksandr_Shakhov on 16.11.16 12:16.
 */

@WebServlet(urlPatterns = {"/user/*"})
public class UserServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(UserServlet.class);

    private UserService service = new UserServiceImp();
    private PostService postService = new PostServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] tokens = request.getPathInfo().split("/");

        try {
            if (tokens != null && tokens.length >= 2) {
                int userId = Integer.parseInt(tokens[1]);

                PostDao postDao = new PostDaoImp();
                UserDao userDao = new UserDaoImp();
                FollowerDao followerDao = new FollowerDaoImp();

                if (userDao.isRegisteredId(userId) != null) {

                    User sessionUser = (User)request.getSession().getAttribute("User");
                    String userSessionAvatar = sessionUser.getAvatar();

                    User user = userDao.getUserByUserId(userId);
                    String avatar = service.getAvatar(user);

                    List<Post> posts = postService.getPostOfUser(user);

                    String userName = user.getFirstName() + " " + user.getLastName();
                    int followerCounter = followerDao.followerCounter(userId);
                    int postCounter = postDao.postCounter(userId);

                    request.setAttribute("user", user);
                    request.setAttribute("userId", userId);
                    request.setAttribute("userName", userName);
                    request.setAttribute("followerDao", followerDao);
                    request.setAttribute("avatar", avatar);
                    request.setAttribute("userSessionAvatar", userSessionAvatar);
                    request.setAttribute("posts", posts);
                    request.setAttribute("followerCounter", followerCounter);
                    request.setAttribute("postCounter", postCounter);

                    RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
                    rd.forward(request, response);
                } else {
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();

                    out.write("<html><head>" +
                            "<link href=\"/css/vendor/all.css\" rel=\"stylesheet\">" +
                            "<link href=\"/css/app/app.css\" rel=\"stylesheet\">" +
                            "<title align=\"center\">No such ID</title>" +
                            "</head><body class=\"login\"");
                    out.write("<strong style=\"color: papayawhip\" align = center>" +
                            "<h5>User's page deleted or not registered yet</h5></strong><br>");
                    out.write("<img align=\"center\" src=\"/images/people/userpic-default.png\" alt=\"people\" class=\"img-circle\"/>");
                    out.write("<p><a style=\"color: papayawhip\" href=\"/users\">Go back to community</a></p>");
                    out.write("</body></html>");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
