package src;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Victor Pérez
 * @date 20/02/2024
 * @description Clase que se encarga de ejecutar el código
 * @version 1.0
 */
public class Interpreter {
    // Borrar este parámetro
    private Validator validator;

    /**
     * @description Constructor de clase
     * @param validator Clase encargada de validar las expresiones
     */
    public Interpreter() {
        this.validator = new Validator();
    }

    // Borrar este
    public int calculate() {
        Token rawExpression = validator.fillStack();
        int result = 0;
        Stack<Token> stack = new Stack<>();
        String[] expression = rawExpression.getValue().split(",");

        for (int i = 0; i < expression.length; i++) {
            if (expression[i].equals("(")) {
                stack.push(new Token("(", "PARENTHESIS"));

            } else if (expression[i].equals(")")) {
                int num2 = Integer.parseInt(stack.pop().getValue());
                int num1 = Integer.parseInt(stack.pop().getValue());
                String operator = stack.pop().getValue();

                if (operator.equals("+")) {
                    result = num1 + num2;

                } else if (operator.equals("-")) {
                    result = num1 - num2;

                } else if (operator.equals("*")) {
                    result = num1 * num2;

                } else if (operator.equals("/")) {
                    result = num1 / num2;
                }

                stack.pop();
                stack.push(new Token(String.valueOf(result), "INTEGER"));

            } else if (validator.isInteger(expression[i])) {
                stack.push(new Token(String.valueOf(expression[i]), "INTEGER"));

            } else {
                stack.push(new Token(String.valueOf(expression[i]), "OPERATOR"));
            }
        }

        return result;
    }

    /**
     * @description Método que se encarga de realizar operaciones aritméticas
     * @param expression Expresión a evaluar en forma de tokens
     * @return Resultado de la operación
     */
    public int calculateArithmetic(ArrayList<Token> expression) {
        // Hacer un swtich para cada operador
        return 0;
    }

    /**
     * @description Método que se encarga de comparar dos valores
     * @param expression Comparación a realizar
     * @return Resultado en forma de boolean
     */
    public boolean compare(ArrayList<Token> expression) {
        // Hacer un swtich para cada caso (>, <, >=, <=, equals)
        return false;
    }
}
