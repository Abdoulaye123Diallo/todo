package sn.ept.git.seminaire.cicd.mappers;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericMapper<E, D> {
    E toEntity(D d);

    D toDTO(E e);

    default List<E> toEntitiesList(List<D> dList){
        return dList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default List<D> toDTOlist(List<E> eList){
        return eList.stream().map(this::toDTO).collect(Collectors.toList());
    }

}