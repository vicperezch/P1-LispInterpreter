package src;

import java.util.ArrayList;

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
    public Function(String name, ArrayList<String> parameters, String body) {
        this.name = name;
        this.body = body;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }
}
