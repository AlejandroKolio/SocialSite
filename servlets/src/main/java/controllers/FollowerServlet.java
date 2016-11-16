package controllers;

import dao.FollowerDao;
import dao.FollowerDaoImp;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Aleksandr_Shakhov on 15.11.16 18:40.
 */

@WebServlet(name = "FollowerServlet", urlPatterns = {"/follow/*"})
public class FollowerServlet extends HttpServlet {

    private FollowerDao followerDao = new FollowerDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().matches("/follow/\\d+")) {
            int followerId = getFollowerId(req);
            followerDao.follow(((User) req.getSession().getAttribute("authenticatedUser")).getUserId(), followerId);
        }
        PrintWriter out = resp.getWriter();
        out.print("<p>You are now following this guy</p>");
        resp.sendRedirect("/users/*");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().matches("/follow/\\d+")) {
            int followerId = getFollowerId(req);
            followerDao.unFollow(((User) req.getSession().getAttribute("authenticatedUser")).getUserId(), followerId);
        }
        resp.sendRedirect("/users/*");
    }

    private int getFollowerId(HttpServletRequest req) {

        String[] urlTokens = req.getPathInfo().split("/");

        if (urlTokens != null && urlTokens.length >= 2) {
            try {
                int followerId = Integer.parseInt(urlTokens[1]);
                return followerId;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("No ID found");
            }
        }
        return -1;
    }
}
