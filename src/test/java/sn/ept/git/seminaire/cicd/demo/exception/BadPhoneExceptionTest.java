/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.demo.exception;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 *Tests pour la classe BadPhoneException
 * @author Dell vPro i7
 */
public class BadPhoneExceptionTest {
    private final String errorMessage = "This is an error message";
    private final Throwable cause = new RuntimeException("Cause of the exception");
    
    
    @Test
    public void testDefaultConstructor() {
        BadPhoneException exception = new BadPhoneException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        BadPhoneException exception = new BadPhoneException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        BadPhoneException exception = new BadPhoneException(errorMessage, cause);
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        BadPhoneException exception = new BadPhoneException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testFullConstructor() {
        BadPhoneException exception = new BadPhoneException(errorMessage, cause, false, false);
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertFalse(exception.getSuppressed().length > 0);
        assertFalse(exception.getStackTrace().length > 0);
    }
    
}
