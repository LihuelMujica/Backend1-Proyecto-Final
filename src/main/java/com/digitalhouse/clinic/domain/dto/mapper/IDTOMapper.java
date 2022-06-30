package com.digitalhouse.clinic.domain.dto.mapper;

import java.util.List;

public interface IDTOMapper<C,D> {
    D toDTO(C c);
    List<D> toDTO(List<C> c);
    C toEntity(D d);
    List<C> toEntity(List<D> d);
}
