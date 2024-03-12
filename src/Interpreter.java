package src;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Victor Pérez
 * @date 20/02/2024
 * @description Clase que se encarga de ejecutar el código
 * @version 1.0
 */
public class Interpreter {
    public static HashMap<String, Token> globalVariables = new HashMap<>();

    
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


    /**
     * @description Método que se encarga de realizar la operación de condicional
     * @param expression Expresión a evaluar
     * @return Resultado del COND
     */
    public String cond(ArrayList<Token> expression) {
        for (int i = 1; i < expression.size(); i++) {
            if (expression.get(i).getTypeValue().equals("BOOLEAN")) {
                if (expression.get(i).getValue().equals("T")) {
                    return expression.get(i + 1).getValue();
                }
            }
        }

        return null;
    }


    public String list(ArrayList<Token> expression){
        StringBuilder list = new StringBuilder();
        for (int i = 1; i < expression.size(); i++) {
            list.append(expression.get(i).getValue());
            if(i<expression.size() - 1) list.append(" ");
        } 
        return list.toString(); 
    }


    public String quote(ArrayList<Token> expression) {
        ArrayList<String> atoms = new ArrayList<String>();
        for (Token token : expression) {
            atoms.add(token.getValue());
        }
        String quotedExpression = String.join(" ", atoms);

        return "'(" + quotedExpression + ")";
    }


    /**
     * @description Método que verifica si la expression es un Atom
     * @param expression
     * @return true si es un Atom o false si no lo es
     */
    public boolean atom(ArrayList<Token> expression) {
        if (expression.size() == 2) {
            if (expression.get(1).getTypeValue().equals("LIST_ELEMENTS")){
                return false;
            } else if (expression.get(1).getTypeValue().equals("INTEGER") || expression.get(1).getTypeValue().equals("STRING") || expression.get(1).getTypeValue().equals("OPERATOR")){
                return true;
            } else {
                throw new IllegalArgumentException("Not a valid Atom for Lisp");
            }
        }
        return false;
    }


    public String setq(ArrayList<Token> expression) {
        globalVariables.put(expression.get(1).getValue(), new Token(String.valueOf(expression.get(2).getValue()), expression.get(2).getTypeValue()));
        return expression.get(1).getValue();
    }


    /**
     * Crea una instancia de la clase función con toda la información necesaria
     * @param expression Expresión DEFUN a evaluar
     * @return Función creada
     */
    public Function defun(ArrayList<Token> expression) {
        String name = expression.get(1).getValue();
        ArrayList<String> parameters = new ArrayList<>();
        
        // Agrega todos los token VARIABLE_NAME antes del primer paréntesis de cierre
        Token current;
        int count = 0;
        while (!(current = expression.get(count)).getValue().equals(")")) {
            if (current.getTypeValue().equals("VARIABLE_NAME")) {
                parameters.add(current.getValue());
            }

            count++;
        }

        // Elimina el nombre de la función de la lista
        parameters.remove(0);

        // Inicia a recorrer después de los parámetros y agrega todos los tokens restantes al cuerpo de la función
        StringBuilder body = new StringBuilder();
        for (int j = count + 1; j < expression.size(); j++) {
            body .append(expression.get(j).getValue() + " ");
        }

        return new Function(name, parameters, body.toString());
    }
}