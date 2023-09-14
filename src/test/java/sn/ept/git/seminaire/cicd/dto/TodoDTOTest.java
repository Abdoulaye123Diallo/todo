/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Dell vPro i7
 */
public class TodoDTOTest {

    /**
     * Test of equals method, of class TodoDTO.
     */
    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(TodoDTO.class)
                    .withRedefinedSuperclass()
                .verify();
    }
}
