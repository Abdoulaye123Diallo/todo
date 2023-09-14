/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dell vPro i7
 */
public class InvalidExceptionTest {
    
    
    @Test
    public void testWithMessage() {
        String message = "Exception";
        InvalidException instance = new InvalidException(message);
        
        assertEquals(message, instance.getMessage());
    }
    
}
