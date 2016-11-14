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
        out.write("<html><head>" + "<title>Page 404</title>\n" +
                        "    <link href=\"css/vendor/all.css\" rel=\"stylesheet\">\n" +
                        "    <link href=\"css/app/app.css\" rel=\"stylesheet\">" +
                "<title>Exception/Error Details</title></head><body class=\"login\">");

        if (statusCode != 500) {
            out.write("<h3 align=\"center\">Error Details</h3>");
            out.write("<strong style=\"align-content: center\">Status Code</strong>:" + statusCode + "<br>");
            out.write("<strong style=\"align-content: center\">Requested URI</strong>:" + requestUri);
        } else {
            out.write("<h3>Exception Details</h3>");
            out.write("<ul><li>Servlet Name:" + servletName + "</li>");
            out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
            out.write("<li>Requested URI:" + requestUri + "</li>");
            out.write("<li>Exception Message:" + throwable.getMessage() + "</li>");
            out.write("</ul>");
        }

        out.write("<br><br>");
        out.write("<a href=\"login.html\">Login Page</a>");
        out.write("</body></html>");
    }
}