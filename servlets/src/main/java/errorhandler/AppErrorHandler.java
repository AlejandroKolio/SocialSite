package errorhandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Aleksandr_Shakhov on 01.11.16 07:37.
 */

@WebServlet("/AppErrorHandler")
public class AppErrorHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Analyze the servlet exception
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

        if (servletName == null) {
            servletName = "Unknown";
        }

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {
            out.write("<html><head>" +
                    "<link href=\"/css/vendor/all.css\" rel=\"stylesheet\">" +
                    "<link href=\"/css/app/app.css\" rel=\"stylesheet\">" +
                    "<title align=\"center\">Exception/Error Details</title>" +
                    "</head><body class=\"login\"");
            if (statusCode != 500) {
                out.write("<strong align = center><h3>Error Details</h3></strong><br>");
                out.write("<strong align = center>Status Code</strong>:" + statusCode + "<br>");
                out.write("<strong align = center>Requested URI</strong>:" + requestUri);
                out.write("</body></html>");
            } else {
                out.write("<strong align = center><h3>Exception Details</h3></strong><br>");
                out.write("<strong style=\"color: papayawhip\" align = center><h4>Not authorised access</h4></strong>");
                out.write("<strong align = center>Servlet Name:" + servletName + "</strong><br>");
                out.write("<strong align = center>Exception Name:" + throwable.getClass().getName() + "</strong><br>");
                out.write("<strong align = center>Requested URI:" + requestUri + " cannot be accessed</strong><br>");
                out.write("<strong align = center>Exception Message:" + throwable.getMessage() + "</strong><br>");
                out.write("</body></html>");
            }
            out.write("<p><a style=\"color: papayawhip\" href=\"/profile\">Main Page</a></p>");
            out.write("<p><a style=\"color: papayawhip\" href=\"/login\">Login</a></p>");
            out.write("</body></html>");
        } finally {
            out.close();
        }
    }
}