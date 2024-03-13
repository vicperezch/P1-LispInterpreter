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
    private HashMap<String, Token> localVariables;

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
        localVariables = null;
    }

    /**
     * @description Obtiene el nombre de la función
     * @return El nombre de la función
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la función
     * @param name Nuevo nombre de la función
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @description Obtiene el cuerpo de la función
     * @return El cuerpo de la función
     */
    public String getBody() {
        return body;
    }

    /**
     * @description Establece el cuerpo de la función
     * @param body Nuevo cuerpo de la función
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @description Obtiene los parámetros de la función
     * @return Los parámetros de la función
     */
    public ArrayList<String> getParameters() {
        return parameters;
    }

    /**
     * @description Establece los parámetros de la función
     * @param parameters Nueva lista de parámetros de la función
     */
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    /**
     * @description Obtiene las variables locales de la función
     * @return Las variables locales de la función
     */
    public HashMap<String, Token> getLocalVariables() {
        return localVariables;
    }

    /**
     * @description Establece las variables locales de la función
     * @param localVariables Nuevas variables locales de la función
     */
    public void setLocalVariables(HashMap<String, Token> localVariables) {
        this.localVariables = localVariables;
    }
}
