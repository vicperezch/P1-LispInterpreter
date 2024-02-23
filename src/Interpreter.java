package src;

import java.util.Stack;

/**
 * @author Victor Pérez
 * @date 20/02/2024
 * @description Clase que se encarga de ejecutar el código
 * @version 1.0
 */
public class Interpreter {
    private Validator validator;

    /**
     * @description Constructor de clase
     * @param validator Clase encargada de validar las expresiones
     */
    public Interpreter() {
        this.validator = new Validator();
    }

    /**
     * @description Método que se encarga de realizar operaciones aritméticas
     * 
     */
    public int calculate() {
        Token exp = validator.fillStack();
        int result = 0;
        Stack<Token> stack = new Stack<>();

        for (int i = 0; i < exp.getValue().length(); i++) {
            if (exp.getValue().charAt(i) == '(') {
                stack.push(new Token("(", "PARENTHESIS"));

            } else if (exp.getValue().charAt(i) == ')') {
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

            } else if (Character.isDigit(exp.getValue().charAt(i))) {
                stack.push(new Token(String.valueOf(exp.getValue().charAt(i)), "INTEGER"));

            } else {
                stack.push(new Token(String.valueOf(exp.getValue().charAt(i)), "OPERATOR"));
            }
        }

        return result;
    }
}
