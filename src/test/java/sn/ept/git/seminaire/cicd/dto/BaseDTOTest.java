/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.dto;

import java.time.Instant;
import nl.jqno.equalsverifier.EqualsVerifier;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BaseDTOTest {

    @Test
    public void testGetterAndSetter() {

        BaseDTO baseDTO = BaseDTO.builder()
                .id("123")
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .build();

        assertEquals("123", baseDTO.getId());
        assertNotNull(baseDTO.getCreatedDate());
        assertNotNull(baseDTO.getLastModifiedDate());
        assertEquals(1, baseDTO.getVersion());

        baseDTO.setId("456");
        Instant newDate = Instant.now().plusSeconds(10);
        baseDTO.setCreatedDate(newDate);
        baseDTO.setLastModifiedDate(newDate);
        baseDTO.setVersion(2);

        assertEquals("456", baseDTO.getId());
        assertEquals(newDate, baseDTO.getCreatedDate());
        assertEquals(newDate, baseDTO.getLastModifiedDate());
        assertEquals(2, baseDTO.getVersion());
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(BaseDTO.class)
                .withRedefinedSuperclass()
                .verify();
    }

    @Test
    public void testToString() {

        Instant createDate = Instant.now().plusSeconds(10);
        Instant lmDate = Instant.now().plusSeconds(10);

        BaseDTO baseDTO = BaseDTO.builder()
                .id("123")
                .createdDate(createDate)
                .lastModifiedDate(lmDate)
                .version(1)
                .build();

        assertEquals("BaseDTO(id=123, createdDate=" + createDate + ", lastModifiedDate=" + lmDate + ", version=1)", baseDTO.toString());
    }
    
    @Test
    public void testAllArgsConstructor(){
        String id = "123";
        Instant crDate = Instant.now();
        Instant lmDate = Instant.now().plusSeconds(20);
        int version = 1;
        BaseDTO instance = new BaseDTO(id, crDate, lmDate, version);
        
        assertEquals(id, instance.getId());
        assertEquals(crDate, instance.getCreatedDate());
        assertEquals(lmDate, instance.getLastModifiedDate());
        assertEquals(version, instance.getVersion());
    }
}
