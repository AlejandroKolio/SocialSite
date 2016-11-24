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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Aleksandr_Shakhov on 12.11.16 22:34.
 */

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(LoginServlet.class);

    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/login.html");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String errorMsg = null;


        if (email == null || email.equals(""))
            errorMsg = "User Email can't be null or empty";
        if (password == null || password.equals(""))
            errorMsg = "Password can't be null or empty";

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<br><br><br><br><br><br><p align='center'><font color = fffaf0 size=3>" + errorMsg + "</font></p>");
            rd.include(request, response);
        } else {
            User user = new User();

            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));

            user = service.verifyUser(user);

            if (user != null) {
                logger.info("User found with details = " + user);

                HttpSession session = request.getSession();

                session.setAttribute("User", user);
                response.sendRedirect("/profile");
            } else {
                PrintWriter out = response.getWriter();
                out.println("<br><br><br><br><br><br><p align='center'><font color = fffaf0 size=3>No user found with given email id, please Sign Up first.</font>");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
                requestDispatcher.include(request, response);
            }
        }
    }
}