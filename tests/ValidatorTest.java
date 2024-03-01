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
}