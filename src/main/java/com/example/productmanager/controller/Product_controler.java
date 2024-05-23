package com.example.productmanager.controller;

import com.example.productmanager.model.Product;
import com.example.productmanager.service.ProductInterface;
import com.example.productmanager.service.ServiceProduct;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "Product_controler", value = "/product")
public class Product_controler extends HttpServlet {
    private static final ProductInterface productService = new ServiceProduct();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "List":
                    List<Product> products = productService.getAllProduct();
                    request.setAttribute("products", products);
                    request.getRequestDispatcher("/views/ListProducts.jsp").forward(request, response);
                    break;
                case "Add":
                    request.getRequestDispatcher("/views/AddProduct.jsp").forward(request, response);
                    break;
                case "Delete":
                    Integer id1 = Integer.parseInt(request.getParameter("id"));
                    productService.deleteProductById(id1);
                    // Điều hướng về trang list
                    request.setAttribute("message", "Delete product success");
                    response.sendRedirect("/product?action=List");
                    break;
                case "Edit":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Product product = productService.findProductById(id);
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/views/EditProduct.jsp").forward(request, response);
                    break;
                    case "SEARCH":
                    String keyword = request.getParameter("keyword");
                    List<Product> productList = productService.searchByName(keyword);
                    request.setAttribute("products", productList);
                    request.setAttribute("keyword", keyword);
                    request.getRequestDispatcher("/views/ListProducts.jsp").forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "Save":
                    Product product = getProduct(request);
                    productService.saveProduct(product);
                    // Điều hướng về trang list
                    request.setAttribute("message", "Add product success");
                    response.sendRedirect("/product?action=List");
                    break;
                case "Update":
                    Product product1 = getProduct(request);
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    product1.setId(id);
                    productService.saveProduct(product1);
                    // Điều hướng về trang list
                    request.setAttribute("message", "Edit product success");
                    response.sendRedirect("/product?action=List");
                    break;
            }
        }
    }

    private Product getProduct(HttpServletRequest request) {
        Product product = new Product();
        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setDescription(request.getParameter("description"));
        product.setImage(request.getParameter("image"));
        return product;
    }
}
