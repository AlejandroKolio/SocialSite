package controllers;

import dao.PostDao;
import dao.PostDaoImp;
import dao.UserDao;
import dao.UserDaoImp;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Aleksandr_Shakhov on 22.11.16 19:45.
 */

@WebServlet(name = "UploadServlet", urlPatterns = {"/upload/*", "/uploadpostpic/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,  // 2 MB
                 maxFileSize       = 1024 * 1024 * 5,  // 5 MB
                 maxRequestSize    = 1024 * 1024 * 10) // 10 MB
public class UploadServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = getUserId(request);
        UserDao userDao = new UserDaoImp();
        PostDao postDao = new PostDaoImp();

        if (request.getRequestURI().matches("/upload/\\d+")) {
            String commonFolder = "/usr/local/Cellar/tomcat/domains/SocialSite/uploads/avatar/";
            String personFolder = commonFolder + String.valueOf(id);
            // creates the save directory if it does not exists
            File common = new File(commonFolder);
            File personal = new File(personFolder);

            if (common.exists()) {
                if (!personal.exists()) {
                    personal.mkdir();
                }
            } else {
                common.mkdir();
            }
            System.out.println("Upload File Directory = " + personal.getAbsolutePath());
            //Get all the parts from request and write it to the file on server
            String fileName = "";
            for (Part part : request.getParts()) {
                fileName = setFileName(part);
                part.write(personal + File.separator + fileName);
            }

            userDao.updateAvatar(getPathForDB(personal.getAbsolutePath()) + fileName, id);

            User user = userDao.getUserByUserId(id);
            String avatar = userDao.getAvatar(user.getUserId());

            request.getSession().setAttribute("avatar", avatar);
            response.sendRedirect("/profile");

        } else if(request.getRequestURI().matches("/uploadpostpic/\\d+")) {
            String commonFolderPosts = "/usr/local/Cellar/tomcat/domains/SocialSite/uploads/posts/";
            String personalFolder = commonFolderPosts + String.valueOf(id);
            // creates the save directory if it does not exists
            File common = new File(commonFolderPosts);
            File personal = new File(personalFolder);

            if (common.exists()) {
                if (!personal.exists()) {
                    personal.mkdir();
                }
            } else {
                common.mkdir();
            }

            System.out.println("Upload File Directory = " + personal.getAbsolutePath());
            //Get all the parts from request and write it to the file on server
            String fileName = "";
            for (Part part : request.getParts()) {
                fileName = setFileName(part);
                part.write(personal + File.separator + fileName);
            }
            postDao.doPicture(getPathForDB(personal.getAbsolutePath()) + fileName, id);
            response.sendRedirect("/posts");
        }
    }

    private String setFileName(Part part) {
        UUID uuid = UUID.randomUUID();

        String contentDisposition = part.getHeader("content-disposition");
        System.out.println("content - disposition header= "+ contentDisposition);
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                String name = token.substring(token.indexOf("=") + 2, token.length() - 1);
                String[] result = name.split("\\.");

                result[0] = "picture_" + uuid + "." + result[1];
                return result[0];
            }
        }
        return "";
    }

    private int getUserId(HttpServletRequest req) {
        String[] urlTokens = req.getRequestURI().split("/");

        if(urlTokens != null && urlTokens.length >= 3) {
            try {
                int userId = Integer.parseInt(urlTokens[2]);
                return userId;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private String getPathForDB(String path) {
        StringBuilder sb = new StringBuilder();
        int begin = path.indexOf("/uploads");

        return sb.append(path.substring(begin, path.length())).append("/").toString();
    }

}
