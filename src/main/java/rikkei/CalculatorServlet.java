package rikkei;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operator = request.getParameter("operator");
        String operand1Str = request.getParameter("operand1");
        String operand2Str = request.getParameter("operand2");

        if (operator != null && operand1Str != null && operand2Str != null) {
            try {
                double operand1 = Double.parseDouble(operand1Str);
                double operand2 = Double.parseDouble(operand2Str);

                Calculator calculator = new Calculator();
                double result = calculator.calculate(operand1, operand2, operator);

                response.getWriter().println("result " + result);
            } catch (NumberFormatException e) {
                response.getWriter().println("Error: Invalid number format.");
            } catch (ArithmeticException e) {
                response.getWriter().println("Error: " + e.getMessage());
            }
        }
    }
}