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
                User user = userDao.getUserByUserId(userId);

                HttpSession session = request.getSession();
                session.setAttribute("User", user);
                RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
