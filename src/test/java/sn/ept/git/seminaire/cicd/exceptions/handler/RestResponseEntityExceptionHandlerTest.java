/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.exceptions.handler;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import sn.ept.git.seminaire.cicd.exceptions.ForbiddenException;
import sn.ept.git.seminaire.cicd.exceptions.InvalidException;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.exceptions.message.ErrorMessage;

public class RestResponseEntityExceptionHandlerTest {

    private RestResponseEntityExceptionHandler exceptionHandler;
    private WebRequest mockRequest;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new RestResponseEntityExceptionHandler();
        mockRequest = mock(WebRequest.class);
        when(mockRequest.getDescription(false)).thenReturn("Description");
    }

    @Test
    public void testHandleItemNotFoundException() {
        ItemNotFoundException ex = new ItemNotFoundException("Item not found");

        ResponseEntity<ErrorMessage> response = exceptionHandler.notFound(ex, mockRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Item not found", response.getBody().getMessage());
    }

    @Test
    public void testHandleItemExistsException() {
        ItemExistsException ex = new ItemExistsException("Item already exists");

        ResponseEntity<ErrorMessage> response = exceptionHandler.conflict(ex, mockRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Item already exists", response.getBody().getMessage());
    }

    @Test
    public void testHandleInvalidException() {
        InvalidException ex = new InvalidException("Invalid request");

        ResponseEntity<ErrorMessage> response = exceptionHandler.badRequest(ex, mockRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid request", response.getBody().getMessage());
    }

    @Test
    public void testHandleForbiddenException() {
        ForbiddenException ex = new ForbiddenException("Access denied");

        ResponseEntity<ErrorMessage> response = exceptionHandler.permissionDenied(ex, mockRequest);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Access denied", response.getBody().getMessage());
    }

    @Test
    public void testHandleGenericException() {
        Exception ex = new Exception("Internal server error");

        ResponseEntity<ErrorMessage> response = exceptionHandler.internalError(ex, mockRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal server error", response.getBody().getMessage());
    }

    @Test
    public void testHandleResponseStatusException() {
        ResponseStatusException ex = new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");

        ResponseEntity<ErrorMessage> response = exceptionHandler.responseStatus(ex, mockRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("401 UNAUTHORIZED \"Unauthorized\"", response.getBody().getMessage());
    }
}