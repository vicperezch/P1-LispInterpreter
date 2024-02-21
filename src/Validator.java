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

    /**
     * @description Método que se encarga de llenar la pila con las expresiones validadas
     * @return Token
     */
    public Token fillStack(){
        ArrayList<Token> tokens = new ArrayList<Token>();
        Stack<Character> instructions = new Stack<Character>();
        Token token = new Token();
        String expressionValidated = "";
        for (int i = 0; i < code.length(); i++) {
            instructions.add(code.charAt(i));
        }
        while (!instructions.empty()) {
            token.setValue(String.valueOf(instructions.pop()));
            tokens.add(token);
            if(token.getValue().equals(")")){
                token.setValue(String.valueOf(instructions.pop()));
                tokens.add(token);
            }else if (token.getValue().contains("(")){
                if(validateExpression(tokens)!=null){
                    for (int i=tokens.size(); i<0; i--) {
                        expressionValidated.concat(tokens.get(i).getValue()+" ");
                    }
                    token.setValue(expressionValidated);
                    atoms.add(token);
                }
                tokens.clear();
            }
        }
        return token;
    }
}