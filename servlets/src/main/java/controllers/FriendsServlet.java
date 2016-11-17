package controllers;

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
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 14.11.16 20:51.
 */

@WebServlet(urlPatterns = "/friends/*")
public class FriendsServlet extends HttpServlet {
    // TODO: 17.11.16 This Servlet is in process. Still no clue how to process is better yet. 
/*
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(FriendsServlet.class);

    private UserService service = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() == null) {
            List<User> users = service.getUsers();
            req.setAttribute("users", users);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/friends.jsp");
            requestDispatcher.forward(req, resp);

        } else {
            String[] tokens = req.getPathInfo().split("/");
            try {
                if (tokens != null && tokens.length >= 2) {
                    int userId = Integer.parseInt(tokens[1]);

                    UserDao userDao = new UserDaoImp();
                    User user = userDao.getUserByUserId(userId);

                    HttpSession session = req.getSession();
                    session.setAttribute("User", user);
                    resp.sendRedirect("/users.jsp");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }*/
}