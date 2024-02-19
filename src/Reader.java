package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Juan Solís
 * @date 19/10/2024
 * @description Clase que se encarga de leer el archivo de texto que contiene el código a interpretar
 * @version 1.0
 */

public class Reader {
    private File fileCode;

    /**
     * @Description: Constructor de la clase Reader
     */
    public Reader() {
        fileCode = new File("code.lisp");
    }

    /**
     * @Description: Método que se encarga de leer el archivo de texto que contiene el código a interpretar
     * @return: String con el contenido del archivo
     */
    public String readFile() {
        String code = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileCode));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line + " ");
            }

            code = sb.toString();
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no fue encontrado");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }

        return code;
    }     
}