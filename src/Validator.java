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
    private HashMap<String, String> reservedWords;

    /**
     * @description Constructor de clase
     */
    public Validator() {
        this.executionStack = new Stack<>();
        this.interpreter = new Interpreter();
        this.reservedWords = new HashMap<>() {{
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
            put("true", "BOOLEAN");
            put("false", "BOOLEAN");
            put("equal", "EQUAL");
            put("cond", "COND");
            put("list", "LIST");
            put("quote", "QUOTE");
            put("atom", "ATOM");
        }};

        executionStack.push(new Token("#","#"));
    }

    /**
     * @description Método que se encarga de llenar la pila y validar las expresiones
     * @return Token con el resultado del código
     */    
    public Token fillStack(String code){
        boolean isQuote = false;
        String number = "";
        String delimiters = "( )";
        ArrayList<Token> expression = new ArrayList<>();

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            if (c == '(') {
                executionStack.push(tokenize(String.valueOf(c)));

            } else if(c == ')') {
                while (!executionStack.peek().getValue().equals("(")) {
                    expression.add(0,executionStack.pop());
                }
        
                executionStack.pop();

                String keyWord = expression.get(0).getTypeValue();

                if (isQuote) {
                    expression.get(0).setTypeValue("QUOTE");
                    keyWord = expression.get(0).getTypeValue();
                }
    
                switch (keyWord) {
                    case "BOOLEAN":
                        if (expression.get(0).getValue().equals("true")) {
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
                        executionStack.push(tokenize(String.valueOf(interpreter.cond(expression))));
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
                //Si el caracter existe entre las palabras reservadas
                if (reservedWords.containsKey(String.valueOf(c))) {
                    executionStack.push(tokenize(String.valueOf(c)));

                } else if (isInteger(String.valueOf(c))) {
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
                        isQuote = true;
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
        if (reservedWords.containsKey(value)) {
            return new Token(value, reservedWords.get(value));

        // Si el valor es un numero
        } else if (isInteger(value)) {
            return new Token(value, "INTEGER");

        } else if (value.equals("t")) {
            return new Token("true", "BOOLEAN");
        
        }else if(value.substring(0, 4).equals("list")){
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