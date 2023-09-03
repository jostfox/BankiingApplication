package org.example.dto;

import org.example.converter.Converter;
import org.example.entity.Agreement;

public class AgreementDto implements Converter<Agreement, AgreementDto> {

    @Override
    public AgreementDto toDto(Agreement entity) {
        return null;
    }

    @Override
    public Agreement toEntity(AgreementDto dto) {
        return null;
    }
}
