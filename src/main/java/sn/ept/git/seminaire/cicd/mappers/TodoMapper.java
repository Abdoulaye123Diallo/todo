package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.TodoDTO;
import sn.ept.git.seminaire.cicd.models.Todo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper extends GenericMapper<Todo, TodoDTO> {

}