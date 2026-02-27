package controller;

import dao.TaskDAO;
import model.Task;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        TaskDAO dao = new TaskDAO();

        int total = dao.countAll(user.getId(), user.getRole());
        int pending = dao.countByStatus(user.getId(), user.getRole(), "Pending");
        int inProgress = dao.countByStatus(user.getId(), user.getRole(), "In Progress");
        int completed = dao.countByStatus(user.getId(), user.getRole(), "Completed");

        request.setAttribute("total", total);
        request.setAttribute("pending", pending);
        request.setAttribute("inProgress", inProgress);
        request.setAttribute("completed", completed);

        request.getRequestDispatcher("statistics.jsp")
                .forward(request, response);
    }
}
