package tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import src.Token;
import src.Validator;

/**
 * @author Juan Solís
 * @date 29/02/2024
 * @description Clase que se encarga de hacer las pruebas unitarias de la clase Validator
 * @version 1.0
 */
public class ValidatorTest {

    /**
     * Test 1: Tokenización de operadores
     * Verifica si la función tokenize asigna correctamente el tipo a un token de operador
     */
    @Test
    public void testTokenize() {
        Validator validator = new Validator();
        Token token = validator.tokenize(">=");

        Assert.assertEquals("COMPARATOR", token.getTypeValue());
    }

    /**
     * Test 2: Operaciones aritméticas en fillStack
     * Verifica si la función fillStack evalúa correctamente una expresión con operaciones aritméticas
     */
    @Test
    public void testOperacionAritmeticaFillStack(){
        String instruccion = "(* (+ (* 45 (/ 10 2)) (- 50 10)) (* 21 2))";
        Validator validator = new Validator();

        Assert.assertEquals("11130", validator.fillStack(instruccion).getValue());
    }

    /**
     * Test 3: Comparación booleana en fillStack
     * Verifica si la función fillStack evalúa correctamente una expresión con una comparación booleana
     */
    @Test
    public void testComparacionBooleanaFillStack(){
        String instruccion = "(> 21 2)";
        Validator validator = new Validator();

        Assert.assertEquals("T", validator.fillStack(instruccion).getValue());
    }

    /**
     * Test 4: Operaciones aritméticas y booleanas en fillStack
     * Verifica si la función fillStack evalúa correctamente una expresión con operaciones aritméticas y una comparación booleana
     */
    @Test
    public void testOperacionAritmeticaYBooleanaFillStack(){
        String instruccion = "(> (+ (* 45 (/ 10 2)) (- 50 10)) (* 21 2))";
        Validator validator = new Validator();

        Assert.assertEquals("T", validator.fillStack(instruccion).getValue());
    }

    /**
     * Test 5: Uso de la keyword 'equal' en fillStack
     * Verifica si la función fillStack evalúa correctamente una expresión con la keyword 'equal'
     */
    @Test
    public void testEqualKeywordFillStack() {
        String instruccion = "(equal 12 12)";
        Validator validator = new Validator();

        Assert.assertEquals("T", validator.fillStack(instruccion).getValue());
    }

    /**
     * Test 6: Evaluación de una expresión condicional en fillStack
     * Verifica si la función fillStack evalúa correctamente una expresión condicional
     */
    @Test
    public void testConditional() {
        String instruccion = "(cond ((equal 2 3) 6) (t (+ 1 3)))";
        Validator validator = new Validator();

        Assert.assertEquals("4", validator.fillStack(instruccion).getValue());
    }

    /**
     * Test 7: Uso de la keyword 'list' en fillStack
     * Verifica si la función fillStack evalúa correctamente una expresión con la keyword 'list'
     */
    @Test
    public void testListKeyword(){
        String instruccion = "(list 1 2 3 4 5 6)";
        Validator validator = new Validator();

        Assert.assertEquals("1 2 3 4 5 6", validator.fillStack(instruccion).getValue());
    }

    /**
     * Test 8: Función 'atom' en fillStack
     * Verifica si la función fillStack evalúa correctamente una expresión con la función 'atom'
     */
    @Test
    public void testAtom(){
        String instruccion = "(atom 1)";
        Validator validator = new Validator();

        Assert.assertEquals("T", validator.fillStack(instruccion).getValue());
    }

    /**
     * Test 9: Uso de la función 'setq' en fillStack
     * Verifica si la función fillStack evalúa correctamente una expresión con la función 'setq'
     */
    @Test 
    public void testSetq(){
        String instruccion = "(setq x 1) (+ 10 x)";
        Validator validator = new Validator();

        Assert.assertEquals("11", validator.fillStack(instruccion).getValue());
    }

    /**
     * Test 10: Función de multiplicación definida por el usuario en fillStack
     * Verifica si la función fillStack evalúa correctamente una función de multiplicación definida por el usuario
     */
    @Test
    public void testMultiplicationFunction() {
        Validator validator = new Validator();
        String functionDefinition = "(defun multiply (x y) (* x y)) (multiply 8 4)";
    
        assertEquals("32", validator.fillStack(functionDefinition).getValue());
    }

    /**
     * Test 11: Función factorial definida por el usuario en fillStack
     * Verifica si la función fillStack evalúa correctamente una función factorial definida por el usuario con recursividad
     */
    @Test
    public void testFactorialFunction() {
        Validator validator = new Validator();
        Assert.assertEquals("24", validator.fillStack("(defun factorial (x) (cond ((equal x 0) 1) (t (* x (factorial (- x 1)))))) (factorial 4)").getValue());
    }

    /**
     * Test 12: Función sin parámetros definida por el usuario en fillStack
     * Verifica si una función sin parámetros se evalúa correctamente en fillStack
     */
    @Test
    public void testFunctionWithoutParameters() {
        Validator validator = new Validator();
        Assert.assertEquals("T", validator.fillStack("(defun bool () (atom 4)) (bool)").getValue());
    }
}