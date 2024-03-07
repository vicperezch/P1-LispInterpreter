package src;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;

/**
 * @author Diego Flores & Victor Pérez
 * @date 19/02/2024
 * @description Clase que se encarga de hacer las validaciones del código
 * @version 2.0
 */
public class Validator {
    private Stack<Token> executionStack;
    private Interpreter interpreter;
    private HashMap<String, String> keyWords;
    private boolean isQuoteExpression;
    private boolean isCondExpression;

    /**
     * @description Constructor de clase
     */
    public Validator() {
        this.executionStack = new Stack<>();
        this.interpreter = new Interpreter();
        this.keyWords = new HashMap<>() {{
            put("(", "PARENTHESIS");
            put(")", "PARENTHESIS");
            put("+", "OPERATOR");
            put("-", "OPERATOR");
            put("*", "OPERATOR");
            put("/", "OPERATOR");
            put(">", "COMPARATOR");
            put("<", "COMPARATOR");
            put(">=", "COMPARATOR");
            put("<=", "COMPARATOR");
            put("!=", "COMPARATOR");
            put("T", "BOOLEAN");
            put("NIL", "BOOLEAN");
            put("equal", "EQUAL");
            put("cond", "COND");
            put("list", "LIST");
            put("quote", "QUOTE");
            put("atom", "ATOM");
        }};
        this.isCondExpression = false;
        this.isQuoteExpression = false;
    }

    /**
     * @description Método que se encarga de llenar la pila y validar las expresiones
     * @return Token con el resultado del código
     */    
    public Token fillStack(String code){
        String number = "";
        String delimiters = "( )";
        ArrayList<Token> expression = new ArrayList<>();

        executionStack.push(new Token("#","#"));
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            if (c == '(') {
                executionStack.push(tokenize(String.valueOf(c)));

            } else if(c == ')') {
                while (!executionStack.peek().getValue().equals("(")) {
                    expression.add(0, executionStack.pop());
                }
        
                executionStack.pop();

                String keyWord = expression.get(0).getTypeValue();

                if (isQuoteExpression) {
                    expression.get(0).setTypeValue("QUOTE");
                    keyWord = expression.get(0).getTypeValue();
                }

                validateExpressionSyntax(expression);
    
                switch (keyWord) {
                    case "BOOLEAN":
                        if (expression.get(0).getValue().equals("T") && isCondExpression) {
                            executionStack.push(expression.get(0));
                            executionStack.push(expression.get(1));
                        }
                        break;

                    case "OPERATOR":
                        executionStack.push(tokenize(String.valueOf(interpreter.calculateArithmetic(expression))));
                        break;

                    case "COMPARATOR":
                        executionStack.push(tokenize(String.valueOf(interpreter.compare(expression))));
                        break;

                    case "EQUAL":
                        executionStack.push(tokenize(String.valueOf(interpreter.compare(expression))));
                        break;
                    
                    case "COND":
                        String result = interpreter.cond(expression);
                        if (result != null) {
                            executionStack.push(tokenize(result));
                        }

                        isCondExpression = false;
                        break;
                    
                    case "LIST":
                        executionStack.push(tokenize(interpreter.list(expression)));
                        break;

                    case "ATOM":
                        executionStack.push(tokenize(String.valueOf(interpreter.atom(expression))));
                        break;

                    case "QUOTE":
                        executionStack.push(tokenize(String.valueOf(interpreter.quote(expression))));
                        break;

                    default:
                        break;
                }

                expression.clear();

            } else if (c != ' ') {
                if (isInteger(String.valueOf(c))) {
                    number += c;

                    if (!isInteger(String.valueOf(code.charAt(i+1)))) {
                        executionStack.push(tokenize(number));
                        number = "";
                    }

                } else {
                    String keyword = "";

                    while (i < code.length() && c != ' ' && !delimiters.contains(String.valueOf(code.charAt(i)))) {
                        keyword += code.charAt(i);
                        i++;
                    }
            
                    if (!keyword.isEmpty()) {
                        executionStack.push(tokenize(keyword));
                    }

                    if (keyword.equals("quote")) {
                        isQuoteExpression = true;

                    } else if (keyword.equals("cond")) {
                        isCondExpression = true;
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
        // Si el valor es una palabra reservada
        if (keyWords.containsKey(value)) {
            return new Token(value, keyWords.get(value));

        // Si el valor es un numero
        } else if (isInteger(value)) {
            return new Token(value, "INTEGER");

        } else if (value.equals("true") || value.equals("t")) {
            return new Token("T", "BOOLEAN");
        
        } else if (value.equals("false")) {
            return new Token("NIL", "BOOLEAN");

        } else if (value.substring(0, 4).equals("list")) {
            return new Token(value.substring(5), "LIST_ELEMENTS");

        } else if (value.charAt(0) == '\'') {
            value = value.replace("\'", "");
            value = value.replace("quote ", "");
            return new Token(value, "STRING");
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
     */
    public void validateExpressionSyntax(ArrayList<Token> expression){
        String keyWord = expression.get(0).getTypeValue();
        String value = expression.get(0).getValue();

        switch (keyWord) {
            case "OPERATOR":
            case "COMPARATOR":
            case "EQUAL":
                if (expression.size() == 3) {
                    if (!expression.get(1).getTypeValue().equals("INTEGER") && expression.get(2).getTypeValue().equals("INTEGER")) {
                        throw new IllegalArgumentException("Not a valid syntax for Lisp: Operands for " + keyWord + " must be of type INTEGER.");
                    }
                } else {
                    throw new IllegalArgumentException("Not a valid syntax for Lisp: Incorrect number of parameters.");
                }
                break;

            case "LIST":
                for (int i = 1; i <= expression.size() - 1; i++){
                    if (!expression.get(i).getTypeValue().equals("INTEGER")) {
                        throw new IllegalArgumentException("Not a valid syntax for Lisp: Elements in LIST must be of type INTEGER.");
                    }
                }
                break;
            
            case "BOOLEAN":
                if (!isCondExpression) {
                    throw new IllegalArgumentException("Not a valid syntax for Lisp: BOOLEAN must be inside a COND expression.");
                }

                if (expression.size() > 2) {
                    throw new IllegalArgumentException("Not a valid syntax for Lisp: Incorrect number of parameters in COND expression.");
                }
                break;

            case "COND":
                String exp = "";
                for (Token token : expression) {
                    exp += token.getTypeValue() + " ";
                }

                if (!exp.matches("^\\s*COND(?:\\s+BOOLEAN\\s+\\w+\\s*)+\\s*")) {
                    throw new IllegalArgumentException("Not a valid syntax for Lisp: Incorrect COND expression.");
                }
                break;

            case "ATOM":  
                if (expression.size() > 2) {
                    throw new IllegalArgumentException("Not a valid syntax for Lisp: Incorrect number of parameters.");
                }
                break;
        
            default:
                throw new IllegalArgumentException("Invalid syntax: " + value + " is not a valid keyword for Lisp.");
        }
    }
}