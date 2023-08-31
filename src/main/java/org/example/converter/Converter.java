package org.example.converter;

public interface Converter <E, D>{

    D toDto(E entity);

    E toEntity(D dto);
}
