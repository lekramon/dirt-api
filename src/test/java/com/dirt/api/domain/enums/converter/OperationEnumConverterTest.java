package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperationEnumConverterTest {

    private final OperationEnumConverter operationEnumConverter = new OperationEnumConverter();

    private static final String INVALID_OPERATION = "DREBIT";
    private static final String EXCEPTION_MESSAGE = "The operation: " + INVALID_OPERATION + " doesn't exist";

    @Test
    public void shouldConvertToDatabaseColumn() {
        final OperationEnum operationEnum = OperationEnum.DEBIT;
        final Integer databaseColumn = operationEnumConverter.convertToDatabaseColumn(operationEnum);
        Assertions.assertEquals(operationEnum.getOperationCode(), databaseColumn);
    }

    @Test
    public void shouldReturnNullToDatabaseColumn() {
        final Integer nullDatabaseColumn = operationEnumConverter.convertToDatabaseColumn(null);
        Assertions.assertNull(nullDatabaseColumn);
    }

    @Test
    public void shouldConvertToEntityAttribute() {
        final int operationEnumValue = 1;
        final OperationEnum operationEnum = operationEnumConverter.convertToEntityAttribute(operationEnumValue);
        Assertions.assertEquals(OperationEnum.fromCode(operationEnumValue), operationEnum);
    }

    @Test
    public void shouldNotConvertToEntityAttributeFromCode() {
        final int operationValue = 4;
        Assertions.assertThrows(EnumNotExistException.class, () -> {
            operationEnumConverter.convertToEntityAttribute(operationValue);
        });
    }

    @Test
    public void shouldReturnNullToEntityAttribute() {
        final OperationEnum operationEnum = operationEnumConverter.convertToEntityAttribute(null);
        Assertions.assertNull(operationEnum);
    }

    @Test
    public void shouldThrowEnumNotExistExceptionToOperationFromValue() {
        final EnumNotExistException exception = Assertions.assertThrows(EnumNotExistException.class, () -> {
            OperationEnum.fromValue(INVALID_OPERATION);
        });

        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(EXCEPTION_MESSAGE, actualMessage);
    }
}