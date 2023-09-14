package sn.ept.git.seminaire.cicd.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.data.TagDTOTestData;
import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.TagMapper;
import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.repositories.TagRepository;
import sn.ept.git.seminaire.cicd.services.impl.TagServiceImpl;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TagServiceTest   {
    @Mock
    TagRepository tagRepository;
    @InjectMocks
    TagServiceImpl service;

    private static TagMapper mapper;

    TagDTO dto ; 
    private static final Map<String, Tag> fakeTagDatabase = new HashMap<>();


    @BeforeAll
    static void beforeAll(){
        log.info(" before all");
        mapper= Mappers.getMapper(TagMapper.class);
    }

    @BeforeEach 
     void beforeEach(){
       log.info(" before each");
        ReflectionTestUtils.setField(service,"mapper",mapper);
        dto = TagDTOTestData.defaultDTO();
        fakeTagDatabase.clear();
    }

    private void mockSaveAndFlush() { 
        Mockito.when(tagRepository.saveAndFlush(Mockito.any(Tag.class))).then(invocation->{
            Instant now = Instant.now();
            Tag tag =invocation.getArgument(0,Tag.class);
            String id =Optional.ofNullable(tag.getId()).orElse(UUID.randomUUID().toString());
            Tag toUpdate =Optional.ofNullable(fakeTagDatabase.getOrDefault(id, null)).orElse(tag);
            toUpdate.setId(id);
            toUpdate.setName(tag.getName());
            toUpdate.setDescription(tag.getDescription());
            toUpdate.setCreatedDate(now);
            toUpdate.setLastModifiedDate(now);
            fakeTagDatabase.put(id, toUpdate);
            return toUpdate;
        });
    }

    private void mockFindByName() {
        Mockito.when(tagRepository.findByName(Mockito.anyString())).then(invocation->{
            String name =invocation.getArgument(0,String.class);
            return fakeTagDatabase
                    .values()
                    .stream()
                    .filter(tag->tag.getName().equals(name))
                    .findFirst();
        });
    }

    private void mockFindByNameWithIdNotEquals() {
        Mockito.when(tagRepository.findByNameWithIdNotEquals(Mockito.anyString(),Mockito.anyString())).then(invocation->{
                   String name =invocation.getArgument(0,String.class);
                   String id =invocation.getArgument(1,String.class);
                   return fakeTagDatabase
                           .values()
                           .stream()
                           .filter(tag->tag.getName().equals(name))
                           .filter(tag->!tag.getId().equals(id))
                           .findFirst();
               });
    }


    private void mockFindAll() {
       Mockito.when(tagRepository.findAll())
                .thenAnswer(inv->List.copyOf(fakeTagDatabase.values()));
    }

    

    private void mockDeleteAll() {

        Mockito.doAnswer( invocation->{
            fakeTagDatabase.clear();
            return null;
        }).when(tagRepository).deleteAll();
    }


 private void mockFindAllPageable() {
     Mockito.when(tagRepository.findAll(Mockito.any(PageRequest.class)))
             .thenAnswer(inv->new PageImpl<>(
                     fakeTagDatabase
                             .values()
                             .stream()
                             .toList())
             );

    }

    private void mockFindById() {
        Mockito.when(tagRepository.findById(Mockito.anyString())).thenAnswer(invocation->{
            String id =invocation.getArgument(0,String.class);
            return Optional.ofNullable(fakeTagDatabase.getOrDefault(id,null));
        });
    }



    private void mockCount() {
        Mockito.when(tagRepository.count())
                .thenReturn(
                        (long) fakeTagDatabase
                                .values()
                                .size()
                );
    }

    private void mockDeleteById() {

        Mockito.doAnswer( invocation->{
            String id =invocation.getArgument(0,String.class);
            fakeTagDatabase.remove(id);
            return null;
        }).when(tagRepository).deleteById(Mockito.anyString());
    }
    @Test
    void save_shouldSaveTag() {
        mockSaveAndFlush();
        mockFindByName();
        dto =service.save(dto);
        assertThat(dto)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void save_withSameName_shouldThrowException() {
        mockFindByName();
        mockSaveAndFlush();
        dto =service.save(dto);
        dto.setName(dto.getName());
        assertThrows(
                ItemExistsException.class,
                () -> service.save(dto)
        );
    }


    @Test
    void update_shouldSucceed() {
        mockSaveAndFlush();
        mockFindByName();
        mockFindByNameWithIdNotEquals();
        mockFindById();
        dto =service.save(dto);
        dto.setName(TestData.Update.name);
        dto.setDescription(TestData.Update.description);
        dto =  service.update(dto.getId(), dto);
        assertThat(dto)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name",dto.getName())
                .hasFieldOrPropertyWithValue("description",dto.getDescription());
    }

  


    @Test
    void update_withBadId_shouldThrowException() {
        mockFindById();
        String badId =UUID.randomUUID().toString();
        assertThrows(
                ItemNotFoundException.class,
                () ->service.update(badId, dto)
        );
    }

    @Test
    void update_withDuplicatedName_shouldThrowException() { 
        mockSaveAndFlush();
        mockFindByName();
        mockFindByNameWithIdNotEquals();
        mockFindById();
        dto =service.save(dto);
        TagDTO dtoBis =service.save(TagDTOTestData.updatedDTO()); 
        assertThrows(
                ItemExistsException.class,
                () ->service.update(dtoBis.getId(), dto)
        );
    }


    @Test
    void findAll_shouldReturnResult() {
        mockSaveAndFlush();
        mockFindAll();
         Tag tag = tagRepository.saveAndFlush(mapper.toEntity(dto));
         dto =mapper.toDTO(tag);
        final List<TagDTO> all = service.findAll();
        assertThat(all)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(dto);
    }

    @Test
    void findAllPageable_shouldReturnResult() {
        mockSaveAndFlush();
        mockFindAllPageable();
        Tag tag = tagRepository.saveAndFlush(mapper.toEntity(dto));
        dto =mapper.toDTO(tag);
        final Page<TagDTO> all = service.findAll(PageRequest.of(0,10));
        assertThat(all)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(dto);
    }

    @Test
    void findById_shouldReturnResult() {
        mockSaveAndFlush();
        mockFindById();
        dto =service.save(dto);
        final Optional<TagDTO> optional = service.findById(dto.getId());
        assertThat(optional)
                .isNotNull()
                .isPresent()
                .get()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void findById_withBadId_ShouldReturnNoResult() {
        mockFindById();
        final Optional<TagDTO> optional = service.findById(UUID.randomUUID().toString());
        assertThat(optional)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void delete_shouldDeleteTag() {
        mockFindByName();
        mockSaveAndFlush();
        mockFindById();
        mockDeleteById();
        dto = service.save(dto);
        String id = dto.getId();
        assertThat(tagRepository.findById(id)).isPresent();
        service.delete(id);
        assertThat(tagRepository.findById(id)).isEmpty();
    }
    
    @Test
    void deleteAll_shouldDeleteAllTags() {
        mockFindByName();
        mockSaveAndFlush();
        mockFindById();
        mockDeleteAll();
        dto = service.save(dto);
        String id = dto.getId();
        assertThat(tagRepository.findById(id)).isPresent();
        service.deleteAll();
        assertThat(tagRepository.findById(id)).isEmpty();
    }



    @Test
    void delete_withBadId_ShouldThrowException() {
        mockFindById();
        String id = UUID.randomUUID().toString();
        assertThrows(
                ItemNotFoundException.class,
                () ->service.delete(id)
        );
    }

}
