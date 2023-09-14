package sn.ept.git.seminaire.cicd.resources;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.data.TagDTOTestData;
import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.repositories.TagRepository;
import sn.ept.git.seminaire.cicd.services.ITagService;
import sn.ept.git.seminaire.cicd.services.impl.TagServiceImpl;
import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import sn.ept.git.seminaire.cicd.utils.TestUtil;
import sn.ept.git.seminaire.cicd.utils.UrlMapping;

import java.time.Instant;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(TagResource.class)
class TagResourceTest {

    @Autowired 
    protected MockMvc mockMvc;

    @MockBean
    private TagServiceImpl service; 

    @Autowired
    private TagResource tagResource;
    private TagDTO dto;
     private TagDTO vm;
    private static final  Instant now = Instant.now();
    private static final Map<String, TagDTO> fakeTagDatabase = new HashMap<>();


    @BeforeAll
    static void beforeAll() {
        log.info(" before all ");
    }

    @BeforeEach
    void beforeEach() {
        log.info(" before each ");
        service.deleteAll();
        vm = TagDTOTestData.defaultDTO();  
        fakeTagDatabase.clear();
    }


    private void mockServiceUpdate() {
        Mockito.when(service.update(Mockito.anyString(),Mockito.any(TagDTO.class)))
                .thenAnswer(invocation -> {
                    String id =invocation.getArgument(0,String.class);
                    TagDTO tag =invocation.getArgument(1,TagDTO.class);
                    TagDTO toUpdate =fakeTagDatabase.getOrDefault(id, null);
                    if(null == toUpdate){
                        throw new ItemNotFoundException();
                    }
                    toUpdate.setId(id);
                    toUpdate.setName(tag.getName());
                    toUpdate.setDescription(tag.getDescription());
                    toUpdate.setCreatedDate(now);
                    toUpdate.setLastModifiedDate(now);
                    fakeTagDatabase.put(id, toUpdate);
                    return toUpdate;
                });
    }



    private void mockServiceSave() {
        Mockito.when(service.save(Mockito.any(TagDTO.class)))
                .thenAnswer(invocation -> {
                    TagDTO tag =invocation.getArgument(0,TagDTO.class);
                    String id =UUID.randomUUID().toString();
                    tag.setId(id);
                    tag.setName(tag.getName());
                    tag.setDescription(tag.getDescription()); 
                    tag.setCreatedDate(now);
                    tag.setLastModifiedDate(now);
                    fakeTagDatabase.put(id, tag);
                    return tag;
                });
    }

    private void mockServiceFindALL() {
        Mockito.when(service.findAll(Mockito.any(Pageable.class)))
                .thenAnswer(invocation -> new PageImpl<>(List.copyOf(fakeTagDatabase.values())));
    }

    private void mockServiceFindById() {
        Mockito.when(service.findById(Mockito.any(String.class)))
                .thenAnswer(invocation ->{
                    String id =invocation.getArgument(0,String.class);
                    return Optional.ofNullable(fakeTagDatabase.getOrDefault(id,null));
                });
    }

    private void mockServiceDelete() {
        Mockito.doAnswer(invocation ->{
                    String id =invocation.getArgument(0,String.class); 
                    TagDTO toDelete= fakeTagDatabase.get(id);
                    if(null == toDelete){
                        throw new ItemNotFoundException();    
                    }
                    fakeTagDatabase.remove(id);
                    return null;
                }).when(service)
                .delete(Mockito.any(String.class));
    }


    @Test
    void findAll_shouldReturnTags() throws Exception {
        mockServiceSave();
        mockServiceFindALL();
        dto = service.save(vm);
        mockMvc.perform(get(UrlMapping.Tag.ALL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content.[0].id").exists())
                .andExpect(jsonPath("$.content.[0].version").exists())
                .andExpect(jsonPath("$.content.[0].name", is(dto.getName())))
                .andExpect(jsonPath("$.content.[0].description").value(dto.getDescription()))
        ;

    }

  /* @Test
    void findById_shouldReturnTag() throws Exception {
        mockServiceSave();
        mockServiceFindById();
        dto = service.save(vm);
        mockMvc.perform(get(UrlMapping.Tag.FIND_BY_ID, dto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                //.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.name", is(dto.getName())))
                .andExpect(jsonPath("$.description").value(dto.getDescription()))
        ;
    }


    @Test
    void findById_withBadId_shouldReturnNotFound() throws Exception {
        mockServiceFindById();
        mockMvc.perform(get(UrlMapping.Tag.FIND_BY_ID, UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }*/


    @Test
    void add_shouldCreateTag() throws Exception {
        mockServiceSave();
        mockMvc.perform(
                        post(UrlMapping.Tag.ADD)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.description").value(vm.getDescription()))
        ;
    }


    @Test
    void update_shouldUpdateTag() throws Exception {             
        mockServiceSave();
        mockServiceUpdate();
        dto = service.save(vm);
        vm.setName(TestData.Update.name);
        vm.setDescription(TestData.Update.description);
        mockMvc.perform(
                        put(UrlMapping.Tag.UPDATE, dto.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.description").value(vm.getDescription()))
        ;
    }

    @Test
    void delete_shouldDeleteTag() throws Exception {
        mockServiceSave();
        mockServiceDelete();
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Tag.DELETE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    void delete_withBadId_shouldReturnNotFound() throws Exception {
        mockServiceSave();
        mockServiceDelete();
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Tag.DELETE, UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}