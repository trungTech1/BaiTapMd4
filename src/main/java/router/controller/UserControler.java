package router.controller;

import router.interfaces.UserServiceI;
import router.model.User;
import router.model.UserUpdate;
import router.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserControler", value = "/users")
public class UserControler extends HttpServlet {
    private static final UserServiceI userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "LIST":
                    List<User> users = userService.getAllUser();
                    request.setAttribute("users", users);
                    request.getRequestDispatcher("/views/User.jsp").forward(request, response);
                    break;
                case "ADD":
                    //điều hướng đến trang thêm mới
                    request.getRequestDispatcher("/views/AddUser.jsp").forward(request, response);
                    break;
                case "UPDATE":
                    // Điều hướng đến trang cập nhật
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    User user = userService.getUserById(id);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/views/EditUser.jsp").forward(request, response);
                    break;
                case "DELETE":
                    // Xóa user
                    Integer idDelete = Integer.parseInt(request.getParameter("id"));
                    userService.deleteUser(idDelete);
                    response.sendRedirect("users?action=LIST");
                    break;
                case "SEARCH":
                    // Tìm kiếm user theo tên
                    getUserByCountry(request, response);
                    break;
                case "LIST_SORT":
                    // Sắp xếp user theo tuy chọn
                    sortBy(request, response);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ADD":
                    addUser(request, response);
                    break;
                case "UPDATE":
                    updateUser(request, response);
                default:
                    break;
            }
        }
    }


    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("name");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        userService.addUser(new User(userName, password, country));
        response.sendRedirect("users?action=LIST");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        UserUpdate userUpdate = new UserUpdate();
        userUpdate.setName(name);
        userUpdate.setPassword(password);
        userUpdate.setCountry(country);
        userService.updateUser(id, userUpdate);
        response.sendRedirect("users?action=LIST");
    }

    private void getUserByCountry(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String keyword = request.getParameter("keyword");
        List<User> users = userService.getUserByCountry(keyword);
        request.setAttribute("users", users);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("/views/User.jsp").forward(request, response);
    }

    private void sortBy(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sortBy = request.getParameter("sortBy");
        System.out.println("sortBy: " + sortBy);
        List<User> users = userService.sortBy(sortBy);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/views/User.jsp").forward(request, response);
    }
}