package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.models.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public   interface TagMapper extends GenericMapper<Tag, TagDTO> {

}