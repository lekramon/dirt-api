package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.OperationEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OperationEnumConverter implements AttributeConverter<OperationEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OperationEnum operationEnum) {
        if (operationEnum == null) {
            return null;
        }
        return operationEnum.getOperationCode();
    }

    @Override
    public OperationEnum convertToEntityAttribute(Integer operation) {
        if (operation == null) {
            return null;
        }

        return OperationEnum.fromCode(operation);
    }
}
