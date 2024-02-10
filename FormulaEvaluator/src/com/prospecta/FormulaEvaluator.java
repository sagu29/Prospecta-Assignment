package com.prospecta;

import java.util.*;

public class FormulaEvaluator {
    public static void main(String[] args) {
        String formula = "=5+3";
        double result = evaluateFormula(formula);
        System.out.println("Result: " + result);
    }

    public static double evaluateFormula(String formula) {
        if (!formula.startsWith("=")) {
            return Double.parseDouble(formula);
        }

        String expression = formula.substring(1);
        Queue<String> tokens = tokenize(expression);

        return evaluateExpression(tokens);
    }

    private static Queue<String> tokenize(String expression) {
        return new LinkedList<>(Arrays.asList(expression.split("[+\\-*/]")));
    }

    private static double evaluateExpression(Queue<String> tokens) {
        Stack<Double> stack = new Stack<>();

        while (!tokens.isEmpty()) {
            String token = tokens.poll();

            if (token.matches("[+\\-*/]")) {

                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = applyOperator(operand1, operand2, token);
                stack.push(result);
            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }

    private static double applyOperator(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}

