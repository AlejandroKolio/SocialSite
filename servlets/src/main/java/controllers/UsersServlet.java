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

@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(UsersServlet.class);

    private UserService service = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() == null) {
            List<User> users = service.getUsers();

            logger.info(users);
            req.setAttribute("users", users);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/users.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            String[] tokens = req.getPathInfo().split("/");
            try {
                if (tokens != null && tokens.length >= 2) {

                    int userId = Integer.parseInt(tokens[1]);

                    RequestDispatcher rd = req.getRequestDispatcher("/users.jsp");
                    rd.include(req, resp);
                } else {
                    // TODO: 18.11.16
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
