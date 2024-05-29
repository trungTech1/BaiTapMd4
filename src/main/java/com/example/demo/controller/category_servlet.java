package com.example.demo.controller;

import com.example.demo.dao.CategoryDao;
import com.example.demo.interfaces.ICategoryDao;
import com.example.demo.model.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Category_servlet", value = "/Category_servlet")
public class category_servlet extends HttpServlet {
    private static final ICategoryDao categoryDao = new CategoryDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "LISTCATEGORY":
                    List<Category> categories = categoryDao.getAllCategories();
                    request.setAttribute("categories", categories);
                    request.getRequestDispatcher("views/category_papes/categories_manager.jsp").forward(request, response);
                    break;
                case "ADDCATEGORY":
                    request.setCharacterEncoding("UTF-8");
                    request.getRequestDispatcher("views/category_papes/category_add.jsp").forward(request, response);
                    break;
                case "EDITCATEGORY":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Category category = categoryDao.getCategoryById(id);
                    category.setId(id);
                    categoryDao.saveCategory(category);
                    response.sendRedirect("Category_servlet?action=LISTCATEGORY");
                    break;
                case "DELETECATEGORY":
                   Boolean del = categoryDao.deleteCategory(Integer.parseInt(request.getParameter("id")));
                   if(del){
                       request.setAttribute("message", "Delete success");
                   }else {
                       request.setAttribute("message", "Delete fail");
                   }
                    response.sendRedirect("Category_servlet?action=LISTCATEGORY");
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ADDCATEGORY":
                    categoryDao.saveCategory(getCategory(request));
                    System.out.println("Add category success" + getCategory(request));
                    response.sendRedirect("Category_servlet?action=LISTCATEGORY");
                    break;
                case "EDITCATEGORY":
                    Integer idEdit = Integer.parseInt(request.getParameter("id"));
                    Category categoryEdit = getCategory(request);
                    categoryEdit.setId(idEdit);
                    categoryDao.saveCategory(getCategory(request));
                    response.sendRedirect("Category_servlet?action=LISTCATEGORY");
                    break;
                default:
                    break;
            }
        }
    }
    private Category getCategory(HttpServletRequest request){
        String name = request.getParameter("categoryname");
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        return new Category(null, name, status);
    }
}