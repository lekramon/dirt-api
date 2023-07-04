package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.StatusEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusEnum statusEnum) {
        if (statusEnum == null) {
            return null;
        }
        return statusEnum.getCodStatus();
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer status) {
        if (status == null) {
            return null;
        }

        return StatusEnum.fromCode(status);
    }
}
