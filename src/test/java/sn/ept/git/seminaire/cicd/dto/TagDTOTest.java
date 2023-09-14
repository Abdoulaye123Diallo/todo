/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sn.ept.git.seminaire.cicd.dto;
import nl.jqno.equalsverifier.EqualsVerifier;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Dell vPro i7
 */
public class TagDTOTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(TagDTO.class)
                .withRedefinedSuperclass()
                .verify();
        
        EqualsVerifier.simple().forClass(BaseDTO.class)
                .withRedefinedSuperclass()
                .verify();
    }
    
    @Test
    public void testToString(){
        
        TagDTO instance = new TagDTO();
        
        String name = "Nom";
        String description = "Description";
        int version = 1;
        
        instance.setName(name);
        instance.setDescription(description);
        instance.setVersion(version);
        
        String expected = "TagDTO(name="
                + name
                + ", description="
                + description
                + ")";
        
        assertEquals(expected, instance.toString());
    }

    /*@Test
    public void testValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Crée un TagDTO valide
        TagDTO validTag = TagDTO.builder()
                .name("ValidName")
                .description("ValidDescription")
                .build();

        // Valide le TagDTO valide
        Set<ConstraintViolation<TagDTO>> violations = validator.validate(validTag);
        assertTrue(violations.isEmpty());

        // Crée un TagDTO invalide (nom vide)
        TagDTO invalidTag = TagDTO.builder()
                .name("") // Le nom est requis, donc il est vide ici
                .description("ValidDescription")
                .build();

        // Valide le TagDTO invalide
        violations = validator.validate(invalidTag);
        assertFalse(violations.isEmpty());
    }*/
}
