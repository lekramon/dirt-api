package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StatusEnumConverterTest {

    private final StatusEnumConverter statusEnumConverter = new StatusEnumConverter();

    private static final String INVALID_STATUS = "PROCESSING";
    private static final String EXCEPTION_MESSAGE = "The status: " + INVALID_STATUS + " doesn't exist";

    @Test
    public void shouldConvertToDatabaseColumn() {
        final StatusEnum statusEnum = StatusEnum.SUCCESS;
        final Integer databaseColumn = statusEnumConverter.convertToDatabaseColumn(statusEnum);
        Assertions.assertEquals(statusEnum.getCodStatus(), databaseColumn);
    }

    @Test
    public void shouldReturnNullToDatabaseColumn() {
        final Integer nullDatabaseColumn = statusEnumConverter.convertToDatabaseColumn(null);
        Assertions.assertNull(nullDatabaseColumn);
    }

    @Test
    public void shouldConvertToEntityAttribute() {
        final int statusEnumValue = 1;
        final StatusEnum statusEnum = statusEnumConverter.convertToEntityAttribute(statusEnumValue);
        Assertions.assertEquals(StatusEnum.fromCode(statusEnumValue), statusEnum);
    }

    @Test
    public void shouldNotConvertToEntityAttributeFromCode() {
        final int statusValue = 4;
        Assertions.assertThrows(EnumNotExistException.class, () -> {
            statusEnumConverter.convertToEntityAttribute(statusValue);
        });
    }

    @Test
    public void shouldReturnNullToEntityAttribute() {
        final StatusEnum statusEnum = statusEnumConverter.convertToEntityAttribute(null);
        Assertions.assertNull(statusEnum);
    }

    @Test
    public void shouldThrowEnumNotExistExceptionToStatusFromValue() {
        final EnumNotExistException exception = Assertions.assertThrows(EnumNotExistException.class, () -> {
            StatusEnum.fromValue(INVALID_STATUS);
        });

        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(EXCEPTION_MESSAGE, actualMessage);
    }
}