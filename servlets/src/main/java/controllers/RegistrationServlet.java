package controllers;

import model.User;
import org.apache.log4j.Logger;
import services.UserService;
import services.UserServiceImp;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String errorMsg = null;

        if (firstName == null || firstName.equals("") || !validateFirstName(firstName))
            errorMsg = "Your name can't be null or empty or does not meet the requirements.";
        if (lastName == null || lastName.equals("") || !validateLastName(lastName))
            errorMsg = "Your surname can't be null or empty does not meet the requirements.";
        if (email == null || email.equals("") || !validateEmail(email))
            errorMsg = "Email ID can't be null or empty or does not exist.";
        if (password == null || password.equals("") || !validatePassword(password)) {
            errorMsg = "Password might be null or empty." + '\n' +
                        "Or Password does not meet the requirements:" + '\n' +
                        "1. Make sure you have 1 capital letter" + '\n' +
                        "2. Use special characters: @#%$ for security purposes" + '\n' +
                        "3. It should not be shorter then 6 and longer then 20 characters.";
        }

        User user = new User();

        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));

        boolean isNewUser = service.verifyNewUser(user);

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<br><br><br><p align = center><font color=fffaf0 size=3 style=align-self: auto>" + errorMsg + "</font></p>");
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
