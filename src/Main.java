package src;

import java.io.IOException;
/**
 * @author Juan Solís
 * @date 19/10/2024
 * @description Clase que principal que inicia el flujo del programa
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) {
        try {
            Reader reader = new Reader();
            String code = reader.readFile();
            
            if (!code.isEmpty()) {
                Validator validator = new Validator();
                Token result = validator.fillStack(code);
                System.out.println("Result: " + result.getValue());

            } else {
                System.out.println("The Lisp code is empty.");
            }
            
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
        }
    }
}