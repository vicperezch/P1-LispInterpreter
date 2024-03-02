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
    private Interpreter interpreter;

    /**
     * @description Constructor de clase
     */
    public Validator() {
        this.executionStack = new Stack<>();
        executionStack.push(new Token("#","#"));
        this.interpreter = new Interpreter();
    }

    /**
     * @description Método que se encarga de llenar la pila y validar las expresiones
     * @return Token con el resultado del código
     */    
    public Token fillStack(String code){
        String operators = "+ - * / ";
        String comparators = "> < >= <= == !=";
        String number = "";
        String delimiters = "( )";
        ArrayList<Token> expression = new ArrayList<>();
        for (int i = 0; i < code.length(); i++) {
            if(code.charAt(i)=='('){
                executionStack.push(tokenize(String.valueOf(code.charAt(i))));
            }else if(code.charAt(i)==')'){
                expression.add(0,executionStack.pop());
                expression.add(0,executionStack.pop());
                expression.add(0,executionStack.pop());
                executionStack.pop();
                switch (expression.get(0).getTypeValue()) {
                    case "OPERATOR":
                        executionStack.push(tokenize(String.valueOf(interpreter.calculateArithmetic(expression))));
                        break;
                    case "COMPARATOR":
                        executionStack.push(tokenize(String.valueOf(interpreter.compare(expression))));
                        break;
                    case "EQUAL":
                        executionStack.push(tokenize(String.valueOf(interpreter.compare(expression))));
                        break;
                    default:
                        break;
                }
                expression.clear();

            }else if(code.charAt(i)!=' '){
                if(operators.contains(String.valueOf(code.charAt(i))) || comparators.contains(String.valueOf(code.charAt(i)))){
                    executionStack.push(tokenize(String.valueOf(code.charAt(i))));

                }else if(isInteger(String.valueOf(code.charAt(i)))){
                    number+=code.charAt(i);
                    if(!isInteger(String.valueOf(code.charAt(i+1)))){
                        executionStack.push(tokenize(number));
                        number="";
                    }

                } else {
                    String keyword = "";
                    while (i < code.length() && code.charAt(i) != ' ' && !delimiters.contains(String.valueOf(code.charAt(i)))) {
                        keyword += code.charAt(i);
                        i++;
                    }
            
                    if (!keyword.isEmpty()) {
                        executionStack.push(tokenize(keyword));
                    }
                }
            }
        }
        return executionStack.pop();
    }

    /**
     * Convierte un valor a un token
     * @param value Valor a tokenizar
     * @return Token
     */
    public Token tokenize(String value) {
        String operators = "+ - * / ";
        String comparators = "> < >= <= == !=";
        String delimiters = "( )";

        if (operators.contains(value)) {
            return new Token(value, "OPERATOR");

        } else if (comparators.contains(value)) {
            return new Token(value, "COMPARATOR");

        } else if (delimiters.contains(value)) {
            return new Token(value, "PARENTHESIS");

        } else if (isInteger(value)) {
            return new Token(value, "INTEGER");

        }else if(value.equals("true") || value.equals("false")){
            return new Token(value, "BOOLEAN");
            
        }else if (value.equals("equal")){
            return new Token(value, "EQUAL");
        }

        throw new IllegalArgumentException("Valor inválido");
    }


    /**
     * @description Método que valida si un string es un número
     * @param str String a validar
     * @return True o false en función de si es un número
     */
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