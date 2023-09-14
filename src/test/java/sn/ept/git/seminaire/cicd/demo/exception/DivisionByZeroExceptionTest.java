/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.demo.exception;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dell vPro i7
 */
public class DivisionByZeroExceptionTest {
    
    private final String message = "Division by zero is not allowed.";
    private final Throwable cause = new IllegalArgumentException("Invalid argument");
    
    
    @Test
    public void testDivisionByZeroException(){
        DivisionByZeroException exception = new DivisionByZeroException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }
    
    @Test
    public void testConstructorWithMessage() {
        DivisionByZeroException exception = new DivisionByZeroException(message);
        assertEquals(message, exception.getMessage());
    }
    
    @Test
    public void testConstructorWithMessageAndCause() {
        DivisionByZeroException exception = new DivisionByZeroException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
    
    @Test
    public void testConstructorWithCause() {
        DivisionByZeroException exception = new DivisionByZeroException(cause);
        assertEquals(cause, exception.getCause());
    }
    
    @Test
    public void testFullConstructor() {
        DivisionByZeroException exception = new DivisionByZeroException(message,cause, false,false);
        assertFalse(exception.getSuppressed().length>0);
        assertFalse(exception.getStackTrace().length>0);
    }    
}
