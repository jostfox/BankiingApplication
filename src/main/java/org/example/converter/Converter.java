package org.example.converter;

public interface Converter <E, D, C>{

    D toDto(E entity);

    //D toDtoIn(E entity);

    E toEntity(C dto);
}
