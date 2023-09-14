/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.utils;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtilTest {

    @Test
    public void testWrapOrNotFoundWithoutStatus() {
        Optional<String> maybeResponse = Optional.of("Hello, World!");
        ResponseEntity<String> response = ResponseUtil.wrapOrNotFound(maybeResponse);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello, World!", response.getBody());
    }

    @Test
    public void testWrapOrNotFoundWithStatus() {
        Optional<String> maybeResponse = Optional.of("Error!");
        ResponseEntity<String> response = ResponseUtil.wrapOrNotFound(maybeResponse, HttpStatus.BAD_REQUEST);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error!", response.getBody());
    }

    @Test
    public void testWrapOrNotFoundWithStatusAndReason() {
        Optional<String> maybeResponse = Optional.of("Hello World");
        String reason = "Resource not found";
        ResponseEntity<String> response = ResponseUtil.wrapOrNotFound(maybeResponse, HttpStatus.NOT_FOUND, reason);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Hello World", response.getBody());
    }

    @Test
    public void testWrapOrNotFoundWithReason() {
        Optional<String> maybeResponse = Optional.of("Hello, World!");
        String reason = "Ressource non trouvée";

        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, reason);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Hello, World!", responseEntity.getBody());
    }

    @Test
    public void testWrapOrNotFoundWithHeaders() {
        Optional<String> maybeResponse = Optional.of("Custom response");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Value");
        ResponseEntity<String> response = ResponseUtil.wrapOrNotFound(maybeResponse, headers, HttpStatus.ACCEPTED);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Custom response", response.getBody());
        assertEquals("Value", response.getHeaders().getFirst("Custom-Header"));
    }
}
