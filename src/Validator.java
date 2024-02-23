package src;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Diego Flores & Victor Pérez
 * @date 19/02/2024
 * @description Clase que se encarga de hacer las validaciones
 * @version 1.0
 */
public class Validator {

    private Stack<Token> atoms;
    private int counter;
    private String code;
    private Reader reader;

    public Validator() {
        this.atoms = new Stack<>();
        this.counter = 0;
        this.reader = new Reader();
        this.code = reader.readFile();
    }

    /**
     * @description Método que se encarga de validar las expresiones
     * @param expression
     * @return ArrayList<Token>
     */
    public Token validateExpression(ArrayList<Token> expression){

        if (expression.size() >= 3) {
            if (expression.get(0).getTypeValue().equals("OPERATOR")) {
                if (expression.get(1).getTypeValue().equals("INTEGER") && expression.get(2).getTypeValue().equals("INTEGER")) {
                    return new Token("(" + expression.get(0).getValue() + expression.get(1).getValue() + expression.get(2).getValue() + ")", "INTEGER");
                }
            }
        }

        throw new RuntimeException("Expresión no válida");
    }

    /**
     * @description Método que se encarga de llenar la pila con las expresiones validadas
     * @return Token
     */
    public Token fillStack(){
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == ')') {
                ArrayList<Token> expression = new ArrayList<Token>();
                while (!atoms.peek().getTypeValue().equals("PARENTHESIS")) {
                    expression.addFirst(atoms.pop());
                }

                atoms.pop();
                Token result = validateExpression(expression);
                atoms.push(result);

            } else if (code.charAt(i) != ' ') {
                if (Character.isDigit(code.charAt(i))) {
                    atoms.push(new Token(String.valueOf(code.charAt(i)), "INTEGER"));

                } else if (code.charAt(i) == '(') {
                    atoms.push(new Token(String.valueOf(code.charAt(i)), "PARENTHESIS"));
                
                } else {
                    atoms.push(new Token(String.valueOf(code.charAt(i)), "OPERATOR"));
                }
            }
        }

        return atoms.pop();
    }
}