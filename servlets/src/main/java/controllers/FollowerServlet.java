package controllers;

import dao.FollowerDao;
import dao.FollowerDaoImp;
import dao.UserDao;
import dao.UserDaoImp;
import model.User;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] token = request.getPathInfo().split("/");
        int userToFollowID = Integer.parseInt(token[1]);
        int followerId = getFollowerId(request);

        try {
            if (request.getRequestURI().matches("/follow/\\d+;\\d+")) {
                followerDao.follow(userToFollowID, followerId);
            } else if (request.getRequestURI().matches("/unfollow/\\d+;\\d+")) {
                followerDao.unFollow(userToFollowID, followerId);
            }

            response.sendRedirect("/users");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getFollowerId(HttpServletRequest req) {
        String[] urlTokens = req.getRequestURI().split(";");

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
