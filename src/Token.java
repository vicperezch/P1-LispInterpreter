package src;

/**
 * @author Nils Muralles
 * @date 19/02/2024
 * @description Clase que modela un Token
 * @version 1.0
 */
public class Token {
    private String value;
    private String typeValue;

    /**
     * @Description Constructor de Token
     */
    public Token(String value, String typeValue) {
        this.value = value;
        this.typeValue = typeValue;

    }

    /**
     * @Description Obtener el valor del Token
     * @return Valor del Token
     */
    public String getValue() {
        return value;
    }

    /**
     * @Description Modificar el valor del Token
     * @param value Valor del Token
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @Description Obtener el tipo del valor del Token
     * @return Tipo del valor del Token
     */
    public String getTypeValue() {
        return typeValue;
    }

    /**
     * @Description Modificar el tipo del valor del Token
     * @param typeValue Tipo del valor del Token
     */
    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}