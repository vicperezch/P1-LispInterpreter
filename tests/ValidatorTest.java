package tests;

import org.junit.Test;
import org.junit.Assert;
import src.Token;
import src.Validator;
import java.util.ArrayList;

/**
 * @author Juan Solís
 * @date 29/02/2024
 * @description Clase que se encarga de hacer las pruebas unitarias de la clase Validator
 * @version 1.0
 */
public class ValidatorTest {
    /**
     * Test 1: Validar sintaxis de la expresión
     */
    @Test
    public void testValidateExpressionSyntax() {
        Validator validator = new Validator();

        ArrayList<Token> expression = new ArrayList<>();
        expression.add(new Token("(", "PARENTHESIS"));
        expression.add(new Token("+", "OPERATOR"));
        expression.add(new Token("5", "INTEGER"));
        expression.add(new Token("7", "INTEGER"));
        expression.add(new Token(")", "PARENTHESIS"));
        boolean result = validator.validateExpressionSyntax(expression);
        Assert.assertEquals(true, result);
    }

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

        Assert.assertEquals("true", validator.fillStack(instruccion).getValue());
    }

    @Test
    public void testOperacionAritmeticaYBooleanaFillStack(){
        String instruccion = "(> (+ (* 45 (/ 10 2)) (- 50 10)) (* 21 2))";
        Validator validator = new Validator();

        Assert.assertEquals("true", validator.fillStack(instruccion).getValue());
    }

    @Test
    public void testEqualKeywordFillStack() {
        String instruccion = "(equal 12 12)";
        Validator validator = new Validator();

        Assert.assertEquals("true", validator.fillStack(instruccion).getValue());
    }

    @Test
    public void testConditional() {
        String instruccion = "(cond ((equal 2 2) 6) (t (+ 2 4))";
        Validator validator = new Validator();

        Assert.assertEquals("6", validator.fillStack(instruccion).getValue());
    }
}