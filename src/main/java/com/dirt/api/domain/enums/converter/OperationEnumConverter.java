package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.exception.EnumNotExistException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OperationEnumConverter implements AttributeConverter<OperationEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OperationEnum operationEnum) {
        switch (operationEnum) {
            case CREDIT:
                return 1;
            case DEBIT:
                return 2;
            default:
                throw new EnumNotExistException("The operation: " + operationEnum + " doesn't exist");
        }
    }

    @Override
    public OperationEnum convertToEntityAttribute(Integer operation) {
        switch (operation) {
            case 1:
                return OperationEnum.CREDIT;
            case 2:
                return OperationEnum.DEBIT;
            default:
                throw new EnumNotExistException("The operation: " + operation + " doesn't exist");
        }
    }
}