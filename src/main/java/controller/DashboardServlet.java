package controller;

import dao.TaskDAO;
import model.Task;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard-old")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }
String keyword = request.getParameter("keyword");
String status = request.getParameter("status");
String userFilter = request.getParameter("userId");

TaskDAO dao = new TaskDAO();

List<Task> tasks = dao.searchTasks(
        user.getId(),
        user.getRole(),
        keyword,
        status,
        userFilter
);

request.setAttribute("tasks", tasks);
request.setAttribute("keyword", keyword);
request.setAttribute("statusFilter", status);
request.setAttribute("userFilter", userFilter);
request.setAttribute("now", new java.sql.Date(System.currentTimeMillis()));

request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
