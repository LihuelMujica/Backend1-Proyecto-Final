package com.digitalhouse.clinic.domain.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface IDTOMapper<C,D> {
    D toDTO(C c);
    default List<D> toDTO(List<C> c){
        return c.stream().map(this::toDTO).collect(Collectors.toList());
    }
    C toEntity(D d);
    default List<C> toEntity(List<D> d){
        return d.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
