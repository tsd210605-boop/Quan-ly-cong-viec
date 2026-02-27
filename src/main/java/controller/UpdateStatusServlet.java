package controller;

import dao.TaskDAO;
import model.User;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/update-status")
public class UpdateStatusServlet extends HttpServlet {

    private TaskDAO dao = new TaskDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.sendRedirect("dashboard");
            return;
        }

        int id = Integer.parseInt(idParam);

        Task task = dao.getTaskById(id);

        if (task == null) {
            response.sendRedirect("dashboard");
            return;
        }

        String currentStatus = task.getStatus();
        String newStatus;

        // Logic chuyển trạng thái
        switch (currentStatus) {
            case "PENDING":
                newStatus = "IN_PROGRESS";
                break;
            case "IN_PROGRESS":
                newStatus = "DONE";
                break;
            default:
                newStatus = "PENDING";
        }

        dao.updateStatus(id, newStatus);

        response.sendRedirect("dashboard");
    }
}
