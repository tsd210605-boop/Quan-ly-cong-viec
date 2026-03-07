package controller;

import dao.TaskDAO;
import model.User;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/edit-task")
public class EditTaskServlet extends HttpServlet {

    private TaskDAO dao = new TaskDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        //int taskId = Integer.parseInt(request.getParameter("id"));
        String idParam = request.getParameter("id");
        if(idParam == null || idParam.isEmpty()) {
            response.sendRedirect("dashboard");
            return;
        }
        int taskId = Integer.parseInt(idParam);
        Task task = dao.getTaskById(taskId);

        if (task == null) {
            response.sendRedirect("dashboard");
            return;
        }

        // 🔒 kiểm tra quyền
        if (!user.getRole().equals("ADMIN") && task.getUserId() != user.getId()) {
            response.sendRedirect("dashboard");
            return;
        }

        request.setAttribute("task", task);
        request.getRequestDispatcher("edit-task.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect("index.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        String idParam = request.getParameter("id");
       // int id = Integer.parseInt(idParam);
        if(idParam == null || idParam.isEmpty()){
            response.sendRedirect("dashboard");
            return;
        }

        int id = Integer.parseInt(idParam);
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Task task = dao.getTaskById(id);

        if (task == null) {
            response.sendRedirect("dashboard");
            return;
        }

        // kiểm tra quyền lần nữa
        if (!user.getRole().equals("ADMIN") && task.getUserId() != user.getId()) {
            response.sendRedirect("dashboard");
            return;
        }

        task.setTitle(title);
        task.setDescription(description);

        dao.updateTask(task);

        response.sendRedirect("dashboard");
    }
}
