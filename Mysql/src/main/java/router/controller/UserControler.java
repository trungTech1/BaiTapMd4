package router.controller;

import javax.servlet.ServletException;
import router.service.UserServiceI;
import router.model.User;
import router.service.UserService;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserControler", value = "/users")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ADD":
                    User user = getUserFromRequest(request);
                    userService.addUser(user);
                    response.sendRedirect("users?action=LIST");
                    break;
                case "UPDATE":
                    // Cập nhật user
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    User userUpdate = getUserFromRequest(request);
                    userUpdate.setId(id);
                    userService.addUser(userUpdate);
                    response.sendRedirect("users?action=LIST");
                    break;
                default:
                    break;
            }
        }
    }

private User getUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        String image = request.getParameter("image");
        return new User(name, password, country, image);
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
        List<User> users = userService.sortBy(sortBy);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/views/User.jsp").forward(request, response);
    }
    private User getUserFromRequest(HttpServletRequest request) throws ServletException, IOException {
        final String rootUpload = "C:\\Users\\hoanc\\OneDrive\\Desktop\\Mysql\\src\\main\\webapp\\uploads\\";
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        // Xử lý upload
        Part file = request.getPart("file");
        String uploadPath = getServletContext().getRealPath("/uploads");
        File fileUploads = new File(uploadPath);
        if(!fileUploads.exists()){
            fileUploads.mkdir();
        }
        String image = null;
        if(file.getSize() != 0){
            image = "/uploads/"+ file.getSubmittedFileName();
            file.write(fileUploads+ File.separator+file.getSubmittedFileName());
            file.write(rootUpload+file.getSubmittedFileName());
        }
        return new User(name, password, country, image);
    }
}