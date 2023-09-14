import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.models.BaseEntity;
import sn.ept.git.seminaire.cicd.models.Todo;


import java.util.HashSet;
import java.util.Set;



import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;



/* ********************************* */
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sn.ept.git.seminaire.cicd.TodoApplication;

@SpringBootTest(classes = {TodoApplication.class})
@Transactional
public class TagTest {
    
  
    @Test
    public void testAllArgsConstructor() {

        String name = "TestTag"; // Assurez-vous que name est de type String
        String description = "Description";
        Set<Todo> todos = new HashSet<>();
        todos.add(new Todo());

        Tag tag = new Tag(name, description, todos);
        
        assertEquals(name, tag.getName());
        assertEquals(description, tag.getDescription());
      
    }
   /* 
    @Test
    public void testGettersAndSetters() {
    Tag tag = new Tag();
    UUID id = UUID.randomUUID();
    String name = "TestTag";
    String description = "Description";

    Set<Todo> todos = new HashSet<>();
    todos.add(new Todo());

    tag.setId(id);
    tag.setName(name);
    tag.setDescription(description);
    tag.setTodos(todos);

    assertEquals(id, tag.getId());
    assertEquals(name, tag.getName());
    assertEquals(description, tag.getDescription());
    assertEquals(todos, tag.getTodos());
}*/
    
       @Test
        public void testEqualsAndHashCode() {
        Tag tag1 = new Tag();
        tag1.setName("Tag1");
        tag1.setDescription("description");
        

        Tag tag2 = new Tag();
        tag2.setName("Tag2");

        
        /********************************** */
        Tag tagfoo  = new Tag();
        tagfoo.setName(tag1.getName());
        tagfoo.setDescription(tag1.getDescription());
        tagfoo.setTodos(tag1.getTodos());
        tagfoo.setLastModifiedDate(tag1.getLastModifiedDate());
        tagfoo.setCreatedDate(tag1.getCreatedDate());
        tagfoo.setId(tag1.getId());

        assertEquals(tag1, tagfoo);

        /********************************** */
        Tag tagSame1 = new Tag();
        tagSame1.setName("Ali");
        tagSame1.setDescription(tag1.getDescription());
        tagSame1.setTodos(tag1.getTodos());
        assertNotEquals(tag1, tagSame1);
         /********************************** */
        Tag tagSame2 = new Tag();
        tagSame2.setDescription("Ali");
        tagSame2.setName(tag1.getName());
        tagSame2.setTodos(tag1.getTodos());
        assertNotEquals(tag1, tagSame2);
         /********************************** */
        Tag tagSame3 = new Tag();
        tagSame3.setDescription(tag1.getDescription());
        tagSame3.setName(tag1.getName());
        Set<Todo> todos = new HashSet<>();
        todos.add(new Todo());

        tagSame3.setTodos(todos);
        assertNotEquals(tag1, tagSame3);
        /********************************** */
        

        // Test equals for two different objects
        Todo todo = new Todo();
        assertFalse(tag1.equals(todo));
        
        // Test equals for two identical objects
        Tag tag3 = tag1; 
        assertTrue(tag1.equals(tag3));

        /********************************** */
        Tag nullTag = null;
        assertNotEquals(nullTag, tag1);

        // Test hashCode for two identical objects
        assertEquals(tag1.hashCode(), tag3.hashCode());
        assertNotEquals(tag1.hashCode(), tag2.hashCode());
    } 

    


    
        @Test
        public void testAllArgsConstructorALO() {
        String name = "TestTag"; // Assurez-vous que name est de type String
        String description = "Description";
        Set<Todo> todos = new HashSet<>();

        Tag tag = new Tag(name, description, todos);
        
        assertEquals(name, tag.getName());
        assertEquals(description, tag.getDescription());
        String expectedToString = "Tag(super=BaseEntity(id=null, createdDate="+tag.getCreatedDate()+", lastModifiedDate="+tag.getLastModifiedDate()+", version=0), name=TestTag, description=Description)";
        assertEquals(expectedToString, tag.toString());
    }


    
}
