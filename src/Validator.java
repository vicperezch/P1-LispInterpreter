package src;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Diego Flores
 * @date 19/02/2024
 * @description Clase que se encarga de hacer las validaciones
 * @version 1.0
 */
public class Validator {

    private Stack<Token> atoms;
    private int counter;
    private String code;
    private Reader reader;

    public Validator(){

    }

    /**
     * @description Metodo que se encarga de validar las expresiones
     * @param expression
     * @return ArrayList<Token>
     */
    public ArrayList<Token> validateExpression(ArrayList<Token> expression){
        String operations = "+-*/";
        String numbers = "0123456789";
        if(expression.get(0).getValue().equals("(") && expression.get(expression.size()-1).getValue().equals(")")){
            if(operations.contains(expression.get(1).getValue())){
                if(numbers.contains(expression.get(2).getValue())){
                    if(numbers.contains(expression.get(3).getValue())) return expression;
                    else return null; 
                }else return null;
            }else return null;    
        }else return null;
    }
}