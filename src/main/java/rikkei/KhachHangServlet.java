package rikkei;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class KhachHangServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<KhachHang> khachHangList = new ArrayList<>();
        khachHangList.add(new KhachHang("Nguyen Van A", "1990-01-01", "Ha Noi", "anh1.jpg"));
        khachHangList.add(new KhachHang("Nguyen Van B", "1991-01-01", "Ha Noi", "anh2.jpg"));
        khachHangList.add(new KhachHang("Nguyen Van C", "1992-01-01", "Ha Noi", "anh3.jpg"));
        khachHangList.add(new KhachHang("Nguyen Van D", "1993-01-01", "Ha Noi", "anh4.jpg"));
        khachHangList.add(new KhachHang("Nguyen Van E", "1994-01-01", "Ha Noi", "anh5.jpg"));

        request.setAttribute("khachHangList", khachHangList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/client.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}