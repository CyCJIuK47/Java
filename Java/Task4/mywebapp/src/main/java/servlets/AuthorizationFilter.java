package servlets;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // deny access to any page if user is not logged in, except login page and login servlet
        if (req.getRequestURI().equals("/mywebapp/") ||
                req.getRequestURI().startsWith("/mywebapp/login") ||
                req.getSession().getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            resp.sendError(404);
        }
    }

    @Override
    public void destroy() {

    }
}
