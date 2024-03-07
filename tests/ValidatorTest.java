package tests;

import org.junit.Test;
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
    @Test
    public void testTokenize() {
        Validator validator = new Validator();
        Token token = validator.tokenize(">=");

        Assert.assertEquals("COMPARATOR", token.getTypeValue());
    }

    @Test
    public void testOperacionAritmeticaFillStack(){
        String instruccion = "(* (+ (* 45 (/ 10 2)) (- 50 10)) (* 21 2))";
        Validator validator = new Validator();

        Assert.assertEquals("11130", validator.fillStack(instruccion).getValue());
    }

    @Test
    public void testComparacionBooleanaFillStack(){
        String instruccion = "(> 21 2)";
        Validator validator = new Validator();

        Assert.assertEquals("T", validator.fillStack(instruccion).getValue());
    }

    @Test
    public void testOperacionAritmeticaYBooleanaFillStack(){
        String instruccion = "(> (+ (* 45 (/ 10 2)) (- 50 10)) (* 21 2))";
        Validator validator = new Validator();

        Assert.assertEquals("T", validator.fillStack(instruccion).getValue());
    }

    @Test
    public void testEqualKeywordFillStack() {
        String instruccion = "(equal 12 12)";
        Validator validator = new Validator();

        Assert.assertEquals("T", validator.fillStack(instruccion).getValue());
    }

    @Test
    public void testConditional() {
        String instruccion = "(cond ((equal 2 3) 6) (t (+ 1 3)))";
        Validator validator = new Validator();

        Assert.assertEquals("4", validator.fillStack(instruccion).getValue());
    }

    @Test
    public void testListKeyword(){
        String instruccion = "(list 1 2 3 4 5 6)";
        Validator validator = new Validator();

        Assert.assertEquals("1 2 3 4 5 6", validator.fillStack(instruccion).getValue());
    }

    @Test
    public void testAtom(){
        String instruccion = "(atom 1)";
        Validator validator = new Validator();

        Assert.assertEquals("T", validator.fillStack(instruccion).getValue());
    }
}