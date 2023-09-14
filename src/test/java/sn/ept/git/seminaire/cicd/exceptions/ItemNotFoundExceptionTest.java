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
public class ItemNotFoundExceptionTest {
    
    @Test
    public void testItemNotFoundException(){
        String default_message = "Impossible de retrouver l'élément recherché";
        ItemNotFoundException instance = new ItemNotFoundException();
        
        assertEquals(default_message, instance.getMessage());
    }
    
    @Test
    public void testItemNotFoundExceptionMessage(){
        String message = "message";
        ItemNotFoundException instance = new ItemNotFoundException(message);
        
        assertEquals(message, instance.getMessage());
    }
    
    @Test
    public void testItemNotFoundExceptionMessageCause(){
        String message = "message";
        Throwable cause =new Throwable("cause");
        ItemNotFoundException instance = new ItemNotFoundException(message, cause);
        
        assertEquals(message, instance.getMessage());
        assertEquals(cause, instance.getCause());
    }
    
    @Test
    public void testItemNotFoundExceptionCause(){
        Throwable cause =new Throwable("cause");
        ItemNotFoundException instance = new ItemNotFoundException(cause);
        
        assertEquals(cause, instance.getCause());
    }
    
    @Test
    public void testFormat(){
        String args[] = {"aaaa %s"};
        String template = "template";
        String expected = String.format(template, args);
        String result = ItemNotFoundException.format(template, args);
        
        assertEquals(expected, result);
    }
    
}
