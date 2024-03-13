package tests;

import src.Interpreter;
import src.Token;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;

/**
 * @author Diego Flores, Victor Pérez, Juan Solís, Nils Muralles
 * @date 05/03/2024
 * @description Clase que se encarga de hacer las pruebas unitarias de la clase Interpreter
 * @version 2.0
 */
public class InterpreterTest {
    
    /**
     * Test 1: Operaciones aritméticas
     * Verifica si la operación de multiplicación funciona correctamente
     */
    @Test
    public void testArithmeticOperation() {
        Interpreter myInterpreter = new Interpreter();
        ArrayList<Token> expression = new ArrayList<Token>();
        expression.add(new Token("*", "OPERATOR"));
        expression.add(new Token("15", "INTEGER"));
        expression.add(new Token("60", "INTEGER"));

        assertEquals(900, myInterpreter.calculateArithmetic(expression));
    }

    /**
     * Test 2: Comparaciones
     * Verifica si la operación de comparación ">=" funciona correctamente
     */
    @Test
    public void testComparator() {
        Interpreter myInterpreter = new Interpreter();
        ArrayList<Token> expression = new ArrayList<Token>();
        expression.add(new Token(">=", "COMPARATOR"));
        expression.add(new Token("14", "INTEGER"));
        expression.add(new Token("20", "INTEGER"));

        assertEquals(false, myInterpreter.compare(expression));
    }

    /**
     * Test 3: Manejar excepción dividir entre cero
     * Verifica si se maneja correctamente la excepción al dividir entre cero
     */
    @Test
    public void DividingByZeroUndefined() {
        Interpreter myInterpreter = new Interpreter();
        ArrayList<Token> expression = new ArrayList<Token>();
        expression.add(new Token("/", "OPERATOR"));
        expression.add(new Token("15", "INTEGER"));
        expression.add(new Token("0", "INTEGER"));

        Exception exception = assertThrows(ArithmeticException.class, () -> myInterpreter.calculateArithmetic(expression));
        assertEquals("Can't divide by zero", exception.getMessage());
    }

    /**
     * Test 4: Generador de lista
     * Verifica si el generador de lista funciona correctamente
     */
    @Test
    public void listGeneratorTest(){
        Interpreter myInterpreter = new Interpreter();
        ArrayList<Token> expression = new ArrayList<Token>();
        expression.add(new Token("list", "LIST"));
        expression.add(new Token("1", "INTEGER"));
        expression.add(new Token("2", "INTEGER"));
        expression.add(new Token("3", "INTEGER"));
        assertEquals("1 2 3", myInterpreter.list(expression));
    }
}