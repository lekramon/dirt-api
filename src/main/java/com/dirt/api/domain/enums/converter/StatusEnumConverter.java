package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.exception.EnumNotExistException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusEnum statusEnum) {
        switch (statusEnum) {
            case PENDING:
                return 1;
            case CANCELED:
                return 2;
            case SUCCESS:
                return 3;
            default:
                throw new EnumNotExistException("The status: " + statusEnum + " doesn't exist");
        }
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer status) {
        switch (status) {
            case 1:
                return StatusEnum.PENDING;
            case 2:
                return StatusEnum.CANCELED;
            case 3:
                return StatusEnum.SUCCESS;
            default:
                throw new EnumNotExistException("The status: " + status + " doesn't exist");
        }
    }
}