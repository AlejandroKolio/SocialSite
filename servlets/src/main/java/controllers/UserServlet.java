package controllers;

import dao.FollowerDao;
import dao.FollowerDaoImp;
import dao.UserDao;
import dao.UserDaoImp;
import model.User;
import org.apache.log4j.Logger;
import services.UserService;
import services.UserServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Aleksandr_Shakhov on 16.11.16 12:16.
 */

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(UserServlet.class);

    private UserService service = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] tokens = request.getPathInfo().split("/");

        try {
            if (tokens != null && tokens.length >= 2) {
                int userId = Integer.parseInt(tokens[1]);

                UserDao userDao = new UserDaoImp();
                FollowerDao followerDao = new FollowerDaoImp();

                if(userDao.isRegisteredId(userId) != null) {

                    User user = userDao.getUserByUserId(userId);

                    String userName = user.getFirstName() + " " + user.getLastName();

                    request.setAttribute("userId", userId);
                    request.setAttribute("userName", userName);
                    request.setAttribute("followerDao", followerDao);

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
            } else {
                /*NOP*/
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
