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
        String validatedArithmeticExpression = "";

        if (expression.size() >= 3) {
            if (expression.get(0).getTypeValue().equals("OPERATOR")) {
                if (expression.get(1).getTypeValue().equals("INTEGER") && expression.get(2).getTypeValue().equals("INTEGER")) {
                    for (Token token : expression) {
                        validatedArithmeticExpression =  validatedArithmeticExpression + token.getValue() + ",";
                    }
                    return new Token("(," + validatedArithmeticExpression + ")", "INTEGER");
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
                    expression.add(0, atoms.pop());
                }

                atoms.pop();
                Token result = validateExpression(expression);
                atoms.push(result);

            } else if (code.charAt(i) != ' ') {
                if (Character.isDigit(code.charAt(i)) && !Character.isDigit(code.charAt(i+1))) {
                    atoms.push(new Token(String.valueOf(code.charAt(i)), "INTEGER"));

                } else if (Character.isDigit(code.charAt(i)) && Character.isDigit(code.charAt(i+1))) {
                    String multipleDigitCharacter = String.valueOf(code.charAt(i));

                    while (Character.isDigit(code.charAt(i + 1))) {
                        if (code.charAt(i + 1) != ' ') {
                            multipleDigitCharacter = multipleDigitCharacter + String.valueOf(code.charAt(i + 1));
                        }
                        i++;
                    }
                    atoms.push(new Token(multipleDigitCharacter, "INTEGER"));

                } else if (code.charAt(i) == '(') {
                    atoms.push(new Token(String.valueOf(code.charAt(i)), "PARENTHESIS"));
                
                } else {
                    atoms.push(new Token(String.valueOf(code.charAt(i)), "OPERATOR"));
                }
            }
        }

        return atoms.pop();
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
}