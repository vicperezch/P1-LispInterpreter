package src;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Victor Pérez
 * @date 08/03/2024
 * @description Clase que representa una función definida por el usuario
 */
public class Function {
    private String name;
    private String body;
    private ArrayList<String> parameters;

    /**
     * @description Constructor de la clase
     * @param name Nombre de la función
     * @param body Cuerpo de la función
     * @param parameters Parámetros de la función
     */
    public Function(String name, String body, ArrayList<String> parameters) {
        this.name = name;
        this.body = body;
        this.parameters = parameters;
    }
}
