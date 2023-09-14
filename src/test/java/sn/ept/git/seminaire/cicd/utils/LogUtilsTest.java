/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Dell vPro i7
 */
public class LogUtilsTest {
    
    @Test
    public void testLogStart() {
        String methodName = "myMethod";
        String className = "MyClass";
        String expectedLog = "MyClass : starts method : myMethod";

        String actualLog = LogUtils.LOG_START
            .replaceFirst("\\{\\}", className)
            .replace("{}", methodName);

        assertEquals(expectedLog, actualLog);
    }
    
}
