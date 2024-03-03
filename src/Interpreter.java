package src;

import java.util.ArrayList;

/**
 * @author Victor Pérez
 * @date 20/02/2024
 * @description Clase que se encarga de ejecutar el código
 * @version 1.0
 */
public class Interpreter {

    /**
     * @description Constructor de clase
     * @param validator Clase encargada de validar las expresiones
     */
    public Interpreter() {
    }

    /**
     * @description Método que se encarga de realizar operaciones aritméticas
     * @param expression Expresión a evaluar en forma de tokens
     * @return Resultado de la operación
     */
    public int calculateArithmetic(ArrayList<Token> expression){
        switch (expression.get(0).getValue()) {
            case "+":
                return Integer.parseInt(expression.get(1).getValue()) + Integer.parseInt(expression.get(2).getValue());
        
            case "-":
                return Integer.parseInt(expression.get(1).getValue()) - Integer.parseInt(expression.get(2).getValue());

            case "*":
                return Integer.parseInt(expression.get(1).getValue()) * Integer.parseInt(expression.get(2).getValue());

            case "/":
                if (Integer.parseInt(expression.get(2).getValue()) == 0) {
                    throw new ArithmeticException("Can't divide by zero");

                } else {
                    return Integer.parseInt(expression.get(1).getValue()) / Integer.parseInt(expression.get(2).getValue()); 
                }
                
            default:
                throw new IllegalArgumentException("Not a valid operator for Lisp");
        }
    }

    /**
     * @description Método que se encarga de comparar dos valores
     * @param expression Comparación a realizar
     * @return Resultado en forma de boolean
     */
    public boolean compare(ArrayList<Token> expression) {
        switch (expression.get(0).getValue()) {
            case ">":
                if (Integer.parseInt(expression.get(1).getValue()) > Integer.parseInt(expression.get(2).getValue())) {
                    return true;
                } else {
                    return false;
                }
                
            case "<":
                if (Integer.parseInt(expression.get(1).getValue()) < Integer.parseInt(expression.get(2).getValue())) {
                    return true;
                } else {
                    return false;
                }
            
            case ">=":
                if (Integer.parseInt(expression.get(1).getValue()) >= Integer.parseInt(expression.get(2).getValue())) {
                    return true;
                } else {
                    return false;
                }
            
            case "<=":
                if (Integer.parseInt(expression.get(1).getValue()) <= Integer.parseInt(expression.get(2).getValue())) {
                    return true;
                } else {
                    return false;
                }
            
            case "equal":
                if (Integer.parseInt(expression.get(1).getValue()) == Integer.parseInt(expression.get(2).getValue())) {
                    return true;
                } else {
                    return false;
                }
            
            default:
                throw new IllegalArgumentException("Not a valid comparator for Lisp");
        }
    }

    public String cond(ArrayList<Token> expression) {
        return expression.get(1).getValue();
    }

    public String list(ArrayList<Token> expression){
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < expression.size(); i++) {
            list.append(expression.get(i).getValue());
            if(i<expression.size()-1) list.append(" ");
        } 
        return list.toString(); 
    }

    public String quote(ArrayList<Token> expression) {
        ArrayList<String> atoms = new ArrayList<String>();
        for (Token token : expression) {
            atoms.add(token.getValue());
        }
        return "(" + String.join(" ", atoms) + ")";
    }
}
