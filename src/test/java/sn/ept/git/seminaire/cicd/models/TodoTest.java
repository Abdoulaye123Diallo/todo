import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.models.BaseEntity;
import sn.ept.git.seminaire.cicd.models.Todo;

import java.util.HashSet;
import java.util.Set;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.repositories.TodoRepository;



public class TodoTest {

  

    @Test
    public void testAllArgsConstructor() {
        String title = "Titre";
        String description = "Description";
        boolean completed = false;
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag());

        Todo todo = new Todo(title, description, completed, tags);
       
        assertEquals(title, todo.getTitle());
        assertEquals(description, todo.getDescription());
      
    }

     @Test
    public void testUpdateWith() {
        String title = "Titre";
        String description = "Description";
        boolean completed = false;
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag());

        Todo todo = new Todo(title, description, completed, tags);
       
        Todo todoUpdate = new Todo(); todoUpdate.setTitle("titrer");
        todoUpdate = todoUpdate.updateWith(todo);

        assertEquals(todoUpdate.getTitle(), todo.getTitle());
        assertEquals(todoUpdate.getDescription(), todo.getDescription());
    }
   
    @Test
    public void testGettersAndSetters() {
    Todo todo = new Todo();
    
    String title = "TestTag";
    String description = "Description";
    boolean completed = false;

    todo.setTitle(title);
    todo.setCompleted(completed);
    todo.setDescription(description);

    //assertEquals(completed, todo.getCompleted());
    assertEquals(title, todo.getTitle());
    assertEquals(description, todo.getDescription());
}
    
    @Test
    public void testEqualsAndHashCode() {
    Todo todo = new Todo();
    todo.setTitle("titre 1");
    todo.setDescription("description 1");
    /******************************* */
    Todo todoSame = new Todo();
    todoSame.setTitle(todo.getTitle());
    todoSame.setDescription(todo.getDescription());
    todoSame.setCompleted(false);
    todoSame.setTags(todo.getTags());

    assertEquals(todo, todoSame);
    assertEquals(todo.hashCode(), todoSame.hashCode());

     /******************************* */
    Todo todoSameX = new Todo();
    todoSameX.setTitle(todo.getTitle());
    todoSameX.setDescription(null);
    todoSameX.setCompleted(false);
    //todoSameX.setTags(todo.getTags());

    assertNotEquals(todo, todoSameX);
    
    
    /******************************** */
     /**************Change Completed***************** */
    Todo todoSame1 = new Todo();
    todoSame1.setTitle(todo.getTitle());
    todoSame1.setDescription(todo.getDescription());
    todoSame1.setCompleted(true);
    todoSame1.setTags(todo.getTags());

    assertNotEquals(todo, todoSame1);
    /******************************** */
     /*************Change Title****************** */
    Todo todoSame2 = new Todo();
    todoSame2.setTitle("titrer");
    todoSame2.setDescription(todo.getDescription());
    todoSame2.setCompleted(false);
    todoSame2.setTags(todo.getTags());

    assertNotEquals(todo, todoSame2);
    /******************************** */
    /**************Change Tags***************** */
    Todo todoSame3 = new Todo();
    todoSame3.setTitle(todo.getTitle());
    todoSame3.setDescription(todo.getDescription());
    todoSame3.setCompleted(false);
    Set<Tag> tags = new HashSet<>();
    tags.add(new Tag());
    todoSame3.setTags(tags);

    assertNotEquals(todo, todoSame3);
    /******************************** */
     /**************Change Description***************** */
    Todo todoSame4 = new Todo();
    todoSame4.setTitle(todo.getTitle());
    todoSame4.setDescription("Voici une breve description");
    todoSame4.setCompleted(false);
    todoSame4.setTags(todo.getTags());

    assertNotEquals(todo, todoSame4);
    
    /**************************** */
    Todo exactTodo = todo;
    assertEquals(todo, exactTodo);
    /**************************** */
    Tag tag = new Tag();
    assertNotEquals(tag, todo);

    /******************************** */
    Todo nullTodo = null;
    assertNotEquals(todo, nullTodo);

    /******************************** */
    
    Todo todoPrime = new Todo();
    todoPrime.setTitle("titre 2");
    todoPrime.setDescription("description 2");

    assertNotEquals(todo, todoPrime);
    assertNotEquals(todo.hashCode(), todoPrime.hashCode());
}

    
        @Test
        public void testAllArgsConstructorALO() {
        Todo todoPrime = new Todo();
        todoPrime.setTitle("titre");
        todoPrime.setDescription("Description");
        
        
        String expectedToString = "Todo(super=BaseEntity(id=null, createdDate="+todoPrime.getCreatedDate()+", lastModifiedDate="+todoPrime.getLastModifiedDate()+", version=0), title=titre, description=Description, completed=false)";
        assertEquals(expectedToString, todoPrime.toString());
        }

        @Test
        public void testEqualsAndHashCodeAmi() {
        Tag tag1 = new Tag();
        tag1.setName("Tag1");
        tag1.setDescription("Description1");

       
        // Test equals for objects with the same values
        Tag tag3 = new Tag();
        tag3.setName("Tag1");
        tag3.setDescription("Description1");
        assertTrue(tag1.equals(tag3)); //1 DE PLUS



        // Test equals for objects with one null field
        Tag tag5 = new Tag();
        tag5.setName("Tag1");
        tag5.setDescription(null);
        assertFalse(tag1.equals(tag5)); //1 AUGMENTE

       
       }

   /*@Test
    public void testPrePersist() {
        // Create a new Todo entity
        Todo todo = new Todo();
        todo.setTitle("Test Title");
        todo.setDescription("Test Description");
        todo.setCompleted(false);

        // Save the entity to trigger @PrePersist
        todoRepository.save(todo);

        // Fetch the saved entity
        Todo savedTodo = todoRepository.findById(todo.getId()).orElse(null);

        // Check that the createdDate and lastModifiedDate are set
        assertNotNull(savedTodo);
        assertNotNull(savedTodo.getCreatedDate());
        assertNotNull(savedTodo.getLastModifiedDate());
        assertEquals(savedTodo.getCreatedDate(), savedTodo.getLastModifiedDate());
    }*/
    
}
