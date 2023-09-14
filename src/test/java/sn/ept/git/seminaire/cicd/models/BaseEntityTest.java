import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.models.BaseEntity;
import sn.ept.git.seminaire.cicd.models.Todo;

import java.util.HashSet;
import java.util.Set;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;


public class BaseEntityTest {
    /************************************** */
    

     @Test
    public void testAllArgsConstructor() {
        BaseEntity entity = new Tag();
        
        String expectedToString = "BaseEntity(id="+entity.getId()+", createdDate=" +entity.getCreatedDate()+", lastModifiedDate="+entity.getLastModifiedDate()+", version=1)";
        /*assertEquals(expectedToString, entity.toString());*/
       

    }

  
    
    @Test
    public void testEqualsAndHashCode() {
    BaseEntity entity = new Tag();
    entity.setVersion(5);
    BaseEntity entitytwo = new Tag();
    /*******************Perfect Equality********************* */
    BaseEntity entitySame = entity;
    
    assertEquals(entity, entitySame);
    /**************************Equality Not perfect****************************** */
    BaseEntity entityEqNotPerfect = new Tag();
    entityEqNotPerfect.setId(entity.getId());
    entityEqNotPerfect.setCreatedDate(entity.getCreatedDate());
    entityEqNotPerfect.setLastModifiedDate(entity.getLastModifiedDate());
    entityEqNotPerfect.setVersion(entity.getVersion());

    assertEquals(entity, entityEqNotPerfect);
     /**************************Equality Not perfect Change Id****************************** */
    BaseEntity entityEqNotPerfect1 = new Tag();
    
    entityEqNotPerfect1.setCreatedDate(entity.getCreatedDate());
    entityEqNotPerfect1.setLastModifiedDate(entity.getLastModifiedDate());
    entityEqNotPerfect1.setVersion(entity.getVersion());

    assertEquals(entity, entityEqNotPerfect1);
     /**************************Equality Not perfect Change Created Date****************************** */
    BaseEntity entityEqNotPerfect2 = new Tag();
    entityEqNotPerfect2.setId(entity.getId());
    
    entityEqNotPerfect2.setLastModifiedDate(entity.getLastModifiedDate());
    entityEqNotPerfect2.setVersion(entity.getVersion());

    assertNotEquals(entity, entityEqNotPerfect2);
     /**************************Equality Not perfect Change Last Date****************************** */
    BaseEntity entityEqNotPerfect3 = new Tag();
    entityEqNotPerfect3.setId(entity.getId());
    entityEqNotPerfect3.setCreatedDate(entity.getCreatedDate());
    
    entityEqNotPerfect3.setVersion(entity.getVersion());

    assertNotEquals(entity, entityEqNotPerfect3);
     /**************************Equality Not perfect Change Version****************************** */
    BaseEntity entityEqNotPerfect4 = new Tag();
    entityEqNotPerfect4.setId(entity.getId());
    entityEqNotPerfect4.setCreatedDate(entity.getCreatedDate());
    entityEqNotPerfect4.setLastModifiedDate(entity.getLastModifiedDate());
    entityEqNotPerfect4.setVersion(7);

    assertNotEquals(entity, entityEqNotPerfect4);
    /*********************************Not Same Types********************************** */
    Todo todo = new Todo();
    assertNotEquals(entity, todo);
    /************************************Null Vlue********************************************* */
    BaseEntity entityNull = (Tag) null;
    assertNotEquals(entityNull, entity);


    assertNotEquals(entity, entitytwo);
    assertNotEquals(entity.hashCode(), entitytwo.hashCode());
   }
  

    
}
