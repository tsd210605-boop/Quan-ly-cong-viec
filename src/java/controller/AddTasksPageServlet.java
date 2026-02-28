package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/add-task-page")
public class AddTasksPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Nếu chưa login -> về login
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        User currentUser = (User) session.getAttribute("user");

        // Nếu là admin -> load danh sách user để phân công
        if (currentUser.getRole() != null &&
            currentUser.getRole().equalsIgnoreCase("admin")) {

            UserDAO userDAO = new UserDAO();
            List<User> userList = userDAO.getAllUsers();

            request.setAttribute("userList", userList);
        }

        // Forward sang trang thêm task
        request.getRequestDispatcher("add-task.jsp")
               .forward(request, response);
    }
}