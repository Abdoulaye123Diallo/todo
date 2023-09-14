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
public class ItemExistsExceptionTest {
    
    @Test
    public void testItemExistsException(){
        String default_message = "Un des éléments que vous tentez d'jouter existe déjà ";
        ItemExistsException instance = new ItemExistsException();
        
        assertEquals(default_message, instance.getMessage());
    }
    
    @Test
    public void testItemExistsExceptionMessage(){
        String message = "message";
        ItemExistsException instance = new ItemExistsException(message);
        
        assertEquals(message, instance.getMessage());
    }
    
    @Test
    public void testItemExistsExceptionMessageCause(){
        String message = "message";
        Throwable cause =new Throwable("cause");
        ItemExistsException instance = new ItemExistsException(message, cause);
        
        assertEquals(message, instance.getMessage());
        assertEquals(cause, instance.getCause());
    }
    
    @Test
    public void testItemExistsExceptionCause(){
        Throwable cause =new Throwable("cause");
        ItemExistsException instance = new ItemExistsException(cause);
        
        assertEquals(cause, instance.getCause());
    }
    
    @Test
    public void testFormat(){
        String args[] = {"aaaa %s"};
        String template = "template";
        String expected = String.format(template, args);
        String result = ItemExistsException.format(template, args);
        
        assertEquals(expected, result);
    }
}
