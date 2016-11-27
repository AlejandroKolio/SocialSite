package controllers;

import model.Comment;
import model.Post;
import model.User;
import org.apache.log4j.Logger;
import services.CommentService;
import services.CommentServiceImp;
import services.PostService;
import services.PostServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 17.11.16 21:28.
 */

@WebServlet(urlPatterns = {"/posts", "/post/"})
public class PostServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(PostServlet.class);

    private PostService postService = new PostServiceImp();
    private CommentService commentService = new CommentServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo() == null) {

            User user = (User) request.getSession().getAttribute("User");
            List<Post> posts = postService.getPostOfUser(user);

            logger.info(posts);
            request.setAttribute("posts", posts);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/posts.jsp");
            requestDispatcher.forward(request, response);
        } else {
            //
        }
    }

    private void savePost(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("User");
        Post post = new Post();
        post.setBody(request.getParameter("post-text"));
        post.setUser(user);
        postService.savePost(post);
    }

    private void saveComment(HttpServletRequest req) {

        User user = (User) req.getSession().getAttribute("User");

        int postId = getPostId(req);

        Comment comment = new Comment();

        comment.setCommentBody(req.getParameter("comment-text"));
        comment.setUserId(user.getUserId());
        comment.setPostId(postId);

        commentService.saveComment(comment);
    }

    private int getPostId(HttpServletRequest req) {
        String[] urlTokens = req.getPathInfo().split("/");
        if (urlTokens != null && urlTokens.length >= 2) {

            int postId = Integer.parseInt(urlTokens[1]);
            return postId;
        } else {
            return -1;
        }
    }

    private void showFollowersPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("User");

        List<Post> posts = postService.getPostOfFriends(user);

        request.setAttribute("posts", posts);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/posts.jsp");
        requestDispatcher.forward(request, response);
    }
}
