package com.shiba;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DiscountServlet", value = "/display-discount")
public class DiscountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("Product_Description");
        String price = request.getParameter("List_Price");
        String discount = request.getParameter("Discount_Percen");
        double price1 = Double.parseDouble(price);
        double discount1 = Double.parseDouble(discount);

        double discountAmount = price1 * discount1 / 100;
        double discountPrice = price1 - discountAmount;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h1>Product Description: " + description + "</h1></body></html>");
        out.println("<html><body><h1>Discount Amount: " + discountAmount + "</h1></body></html>");
        out.println("<html><body><h1>Discount Price: " + discountPrice + "</h1></body></html>");


    }
}