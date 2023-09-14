/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.exceptions.message;

import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import nl.jqno.equalsverifier.EqualsVerifier;
/**
 *
 * @author Dell vPro i7
 */
public class ErrorMessageTest {

    @Test
    public void testErrorMessageCreation() {
        int expectedStatus = 404;
        Date expectedTimestamp = new Date();
        String expectedMessage = "Not Found";
        String expectedDescription = "The requested resource was not found.";

        ErrorMessage errorMessage = ErrorMessage.of(expectedStatus, expectedTimestamp, expectedMessage, expectedDescription);

        assertEquals(expectedStatus, errorMessage.getStatus());
        assertEquals(expectedTimestamp, errorMessage.getTimestamp());
        assertEquals(expectedMessage, errorMessage.getMessage());
        assertEquals(expectedDescription, errorMessage.getDescription());
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(ErrorMessage.class).verify();
    }

    @Test
    public void testToString() {
        ErrorMessage errorMessage = ErrorMessage.of(404, new Date(), "Not Found", "Description");
        String expectedToString = "ErrorMessage(status=404, timestamp=" + errorMessage.getTimestamp() + ", message=Not Found, description=Description)";

        assertEquals(expectedToString, errorMessage.toString());
    }

    @Test
    public void testSetter() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(404);
        errorMessage.setTimestamp(new Date());
        errorMessage.setMessage("Not Found");
        errorMessage.setDescription("Description");

        assertEquals(404, errorMessage.getStatus());
        assertNotNull(errorMessage.getTimestamp());
        assertEquals("Not Found", errorMessage.getMessage());
        assertEquals("Description", errorMessage.getDescription());
    }

    @Test
    public void testAllArgsConstructor() {
        Date timestamp = new Date();
        ErrorMessage errorMessage = ErrorMessage.of(404, timestamp, "Not Found", "Description");

        assertEquals(404, errorMessage.getStatus());
        assertEquals(timestamp, errorMessage.getTimestamp());
        assertEquals("Not Found", errorMessage.getMessage());
        assertEquals("Description", errorMessage.getDescription());
    }

    @Test
    public void testNoArgsConstructor() {
        ErrorMessage errorMessage = new ErrorMessage();

        assertEquals(0, errorMessage.getStatus());
        assertNull(errorMessage.getTimestamp());
        assertNull(errorMessage.getMessage());
        assertNull(errorMessage.getDescription());
    }

    @Test
    public void testSuperBuilder() {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(404)
                .timestamp(new Date())
                .message("Not Found")
                .description("Description")
                .build();

        assertEquals(404, errorMessage.getStatus());
        assertNotNull(errorMessage.getTimestamp());
        assertEquals("Not Found", errorMessage.getMessage());
        assertEquals("Description", errorMessage.getDescription());
    }
}
