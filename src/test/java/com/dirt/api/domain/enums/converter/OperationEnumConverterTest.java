package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperationEnumConverterTest {

    private final OperationEnumConverter operationEnumConverter = new OperationEnumConverter();

    @Test
    public void shouldConvertToDatabaseColumn() {
        OperationEnum operationEnum = OperationEnum.DEBIT;
        Integer databaseColumn = operationEnumConverter.convertToDatabaseColumn(operationEnum);
        Assertions.assertEquals(operationEnum.getOperationCode(), databaseColumn);
    }

    @Test
    public void shouldReturnNullToDatabaseColumn() {
        Integer nullDatabaseColumn = operationEnumConverter.convertToDatabaseColumn(null);
        Assertions.assertNull(nullDatabaseColumn);
    }

    @Test
    public void shouldConvertToEntityAttribute() {
        int operationEnumValue = 1;
        OperationEnum operationEnum = operationEnumConverter.convertToEntityAttribute(operationEnumValue);
        Assertions.assertEquals(OperationEnum.fromCode(operationEnumValue), operationEnum);
    }

    @Test
    public void shouldReturnNullToEntityAttribute() {
        OperationEnum operationEnum = operationEnumConverter.convertToEntityAttribute(null);
        Assertions.assertNull(operationEnum);
    }

    @Test
    public void shouldThrowEnumNotExistExceptionToOperation() {
        int operationValue = 6;
        Assertions.assertThrows(EnumNotExistException.class, () -> {
            OperationEnum operationEnum = operationEnumConverter.convertToEntityAttribute(operationValue);
        });
    }
}