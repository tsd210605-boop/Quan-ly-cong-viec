package controller;

import dao.TaskDAO;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/update-task")
public class UpdateTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        String deadlineStr = request.getParameter("deadline");
        java.sql.Date deadline = null;
        if (deadlineStr != null && !deadlineStr.isEmpty()) {
            deadline = java.sql.Date.valueOf(deadlineStr);
        }

        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setDeadline(deadline);

        TaskDAO dao = new TaskDAO();
        dao.updateTask(task);

        response.sendRedirect(request.getContextPath() + "/tasks-page");
    }
}
