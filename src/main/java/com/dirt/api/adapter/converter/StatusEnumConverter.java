package com.dirt.api.adapter.converter;

import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.exception.StatusNotExistException;

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
                throw new StatusNotExistException("The status: " + statusEnum + " doesn't exist");
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
                throw new StatusNotExistException("The status: " + status + " doesn't exist");
        }
    }
}