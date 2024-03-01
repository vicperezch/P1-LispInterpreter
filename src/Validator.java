package src;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Diego Flores & Victor Pérez
 * @date 19/02/2024
 * @description Clase que se encarga de hacer las validaciones del código
 * @version 2.0
 */
public class Validator {
    private Stack<Token> executionStack;
    private String code;
    private Interpreter interpreter;

    /**
     * @description Constructor de clase
     */
    public Validator() {
        Reader reader = new Reader();
        this.executionStack = new Stack<>();
        this.code = reader.readFile();
        this.interpreter = new Interpreter();
    }

    /**
     * @description Método que se encarga de llenar la pila y validar las expresiones
     * @return Token con el resultado del código
     */

    // Modificar este    
    // public Token fillStack(){
    //     for (int i = 0; i < code.length(); i++) {
    //         if (code.charAt(i) == ')') {
    //             ArrayList<Token> expression = new ArrayList<Token>();
    //             while (!executionStack.peek().getTypeValue().equals("PARENTHESIS")) {
    //                 expression.add(0, executionStack.pop());
    //             }

    //             executionStack.pop();
    //             //Token result = validateExpression(expression);
    //             //executionStack.push(result);

    //         } else if (code.charAt(i) != ' ') {
    //             if (Character.isDigit(code.charAt(i)) && !Character.isDigit(code.charAt(i+1))) {
    //                 executionStack.push(new Token(String.valueOf(code.charAt(i)), "INTEGER"));

    //             } else if (Character.isDigit(code.charAt(i)) && Character.isDigit(code.charAt(i+1))) {
    //                 String multipleDigitCharacter = String.valueOf(code.charAt(i));

    //                 while (Character.isDigit(code.charAt(i + 1))) {
    //                     if (code.charAt(i + 1) != ' ') {
    //                         multipleDigitCharacter = multipleDigitCharacter + String.valueOf(code.charAt(i + 1));
    //                     }
    //                     i++;
    //                 }
    //                 executionStack.push(new Token(multipleDigitCharacter, "INTEGER"));

    //             } else if (code.charAt(i) == '(') {
    //                 executionStack.push(new Token(String.valueOf(code.charAt(i)), "PARENTHESIS"));
                
    //             } else {
    //                 executionStack.push(new Token(String.valueOf(code.charAt(i)), "OPERATOR"));
    //             }
    //         }
    //     }

    //     return executionStack.pop();
    // }

    /**
     * Convierte una expresión en una lista de tokens según su tipo
     * @param expression Expresión a convertir
     * @return ArrayList con los tokens
     */

    // Ejemplo: recibe (< 1 2) en forma de string y retorna un arraylist con 5 tokens y sus tipos correspondientes: (, <, 1, 2, )
    public ArrayList<Token> tokenize(String expression) {
        return null;
    }

    /**
     * @description Método que valida si un string es un número
     * @param str String a validar
     * @return True o false en función de si es un número
     */

    // No sé si este método va a ser necesario al final 
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @description Método que se encarga de validar la estructura de las expresiones
     * @param expression Expresión a validar
     * @return true si la expresión es válida, false en caso contrario
     */
    public boolean validateExpressionSyntax(ArrayList<Token> expression){
        boolean result = false;

        if (expression.size() >= 5) {
            String keyWord = expression.get(1).getTypeValue();

            switch (keyWord) {
                case "OPERATOR":
                    if (expression.get(2).getTypeValue().equals("INTEGER") && expression.get(3).getTypeValue().equals("INTEGER")) {
                        result = true;
                    } else {
                        result = false;
                    }
                    break;

                case "COMPARATOR":
                    if (expression.get(2).getTypeValue().equals("INTEGER") && expression.get(3).getTypeValue().equals("INTEGER")) {
                        result = true;
                    } else {
                        result = false;
                    }
                    break;
                default:
                    result = false;
            }
        }
        return result;
    }
}