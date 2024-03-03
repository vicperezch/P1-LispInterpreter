package tests;

import src.Interpreter;
import src.Token;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class InterpreterTest {
    
    /**
     * Test 1: Operaciones aritméticas
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

    @Test
    public void listGeneratorTest(){
        Interpreter myInterpreter = new Interpreter();
        ArrayList<Token> expression = new ArrayList<Token>();
        expression.add(new Token("list", "LIST"));
        expression.add(new Token("1", "INTEGER"));
        expression.add(new Token("2", "INTEGER"));
        expression.add(new Token("3", "INTEGER"));
        assertEquals("list 1 2 3", myInterpreter.list(expression));
    }
}
