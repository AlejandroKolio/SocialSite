package controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created by Aleksandr_Shakhov on 17.11.16 21:28.
 */

@WebServlet(urlPatterns = {"/posts/*", "/post"})
public class PostServlet extends HttpServlet {

}
