package filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Aleksandr_Shakhov on 01.11.16 07:44.
 */

/**
 * Filter implementation and we are validating user session here.
 */

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private Logger logger = Logger.getLogger(AuthenticationFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
        logger.info("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
        String requestedUri = ((HttpServletRequest) servletRequest).getRequestURI();

        if (session == null || session.getAttribute("authenticatedUser") == null) {
            if (requestedUri.contains("login") || requestedUri.contains("registration")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("/login");
            }
        } else if (requestedUri.contains("login") || requestedUri.contains("registration")) {
            ((HttpServletResponse) servletResponse).sendRedirect("/profile");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {
        //close any resources here
    }
}