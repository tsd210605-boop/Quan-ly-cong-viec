package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // Cho phép các resource công khai
        if (uri.endsWith("index.jsp")
                || uri.endsWith("/login")
                || uri.endsWith("/logout")
                || uri.endsWith("/add-task")
                || uri.endsWith("/edit-task")
                || uri.contains("/css/")
                || uri.contains("/js/")) {

            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            if (!resp.isCommitted()) {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
            return;
        }

        chain.doFilter(request, response);
    }
}
