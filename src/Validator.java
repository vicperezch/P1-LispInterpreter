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
    public Token fillStack(){
        return null;
    }


    /**
     * Convierte un valor a un token
     * @param value Valor a tokenizar
     * @return Token
     */
    public Token tokenize(String value) {
        String operators = "+ - * / ";
        String comparators = "> < >= <= == !=";

        if (operators.contains(value)) {
            return new Token(value, "OPERATOR");

        } else if (comparators.contains(value)) {
            return new Token(value, "COMPARATOR");

        } else if (isInteger(value)) {
            return new Token(value, "INTEGER");
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