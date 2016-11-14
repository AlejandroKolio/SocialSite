package controllers;

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
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Aleksandr_Shakhov on 13.11.16 19:29.
 */

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(RegistrationServlet.class);

    private UserService service = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();

        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));

        String errorMsg = null;

        if (firstName == null || firstName.equals(""))
            errorMsg = "Your name can't be null or empty.";
        if (lastName == null || lastName.equals(""))
            errorMsg = "Your surname can't be null or empty.";
        if (email == null || email.equals(""))
            errorMsg = "Email ID can't be null or empty.";
        if (password == null || password.equals(""))
            errorMsg = "Password can't be null or empty.";

        boolean isNewUser = service.verifyNewUser(user);

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<br><br><br><p align = center><font color=\"#ffe4b5\" size=\"3\" style=\"align-self: auto\">" + errorMsg + "</font></p>");
            rd.include(req, resp);
        } else {
            if (isNewUser) {
                logger.info("User registered with email = " + email);
                //forward to login page to login
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = resp.getWriter();
                out.println("<br><br><br><p align = center><font color=\"#ffe4b5\" size=\"3\" style=\"align-self: auto\">Registration successful, please login below.</font></p>");
                service.saveUser(user);
                rd.include(req, resp);
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.html");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
