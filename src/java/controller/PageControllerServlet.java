package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import dao.TaskDAO;
import model.Task;
import model.User;

@WebServlet({
        "/dashboard",
        "/tasks-page",
        "/profile-page",
        "/edit-task-page"
})
public class PageControllerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Nếu chưa login -> quay lại login
        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String path = request.getServletPath();

        switch (path) {

            // ======================
            // TRANG CHỦ
            // ======================
            case "/dashboard":
                request.getRequestDispatcher("dashboard.jsp")
                        .forward(request, response);
                break;

            // ======================
            // TRANG THÊM TASK
            // ======================
            case "/add-task-page":
                request.getRequestDispatcher("add-task.jsp")
                        .forward(request, response);
                break;

            // ======================
            // TRANG DANH SÁCH TASK
            // ======================
          case "/tasks-page":



   
    //System.out.println("TASK SIZE: " + tasks.size());
    
    TaskDAO dao = new TaskDAO();
    List<Task> tasks = dao.getTasksByUser(user.getId(), user.getRole());
    //System.out.println("TASK SIZE: " + tasks.size());
    request.setAttribute("tasks", tasks);
    request.getRequestDispatcher("tasks.jsp").forward(request, response);
    break;


            // ======================
            // TRANG PROFILE
            // ======================
            case "/profile-page":
                request.getRequestDispatcher("profile.jsp")
                        .forward(request, response);
                break;
                
                //edit-------
             case "/edit-task-page":

    String idStr = request.getParameter("id");

    if (idStr == null || idStr.trim().isEmpty()) {
        response.sendRedirect("tasks-page");
        return;
    }

    int id;
    try {
        id = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
        response.sendRedirect("tasks-page");
        return;
    }

    TaskDAO dao2 = new TaskDAO();
    Task task = dao2.getTaskById(id);

    if (task == null) {
        response.sendRedirect("tasks-page");
        return;
    }

    request.setAttribute("task", task);
    request.getRequestDispatcher("edit-task.jsp")
           .forward(request, response);
    break;

        }
    }
   
}
