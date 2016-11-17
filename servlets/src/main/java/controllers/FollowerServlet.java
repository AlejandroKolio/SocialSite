package controllers;

import dao.FollowerDao;
import dao.FollowerDaoImp;

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
 * Created by Aleksandr_Shakhov on 15.11.16 18:40.
 */

@WebServlet(urlPatterns = {"/follow/*", "/unfollow/*"})
public class FollowerServlet extends HttpServlet {

    private FollowerDao followerDao = new FollowerDaoImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int followerId = 0;
        if (request.getRequestURI().matches("/follow/\\d+;\\d+")) {

            String[] id = request.getRequestURI().split(";");
            int userId = Integer.parseInt(id[1]);

            followerId = getFollowerId(request);
            followerDao.follow(userId, followerId);
        }
        PrintWriter out = response.getWriter();
        out.write("<br><br><p>You are now following this guy</p>");
        response.sendRedirect("/posts");
    }

    private int getFollowerId(HttpServletRequest req) {
        String[] urlTokens = req.getPathInfo().split("/");

        if (urlTokens != null && urlTokens.length >= 2) {
            try {
                int followerId = Integer.parseInt(urlTokens[1]);

                return followerId;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
