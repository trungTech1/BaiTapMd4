package rikkei;

public class Calculator {
    public double calculate(double operand1, double operand2, String operator) throws ArithmeticException {
        switch (operator) {
            case "add":
                return operand1 + operand2;
            case "subtract":
                return operand1 - operand2;
            case "multiply":
                return operand1 * operand2;
            case "divide":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}