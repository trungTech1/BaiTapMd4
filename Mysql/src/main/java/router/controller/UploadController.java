package router.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "UploadController", value = "/upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UploadController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        //Nhận về 1 danh sách các file đã upload
        Collection<Part> parts = request.getParts();
        List<String> FilNames = new ArrayList<>();
        for (Part part : parts) {
            if (part.getName().equals("files")) {
                String fileName = part.getSubmittedFileName();
                FilNames.add(fileName);
                part.write(uploadPath + File.separator + fileName);
            }
        }
        request.setAttribute("urls", FilNames);
        request.getRequestDispatcher("image.jsp").forward(request, response);
    }
}
