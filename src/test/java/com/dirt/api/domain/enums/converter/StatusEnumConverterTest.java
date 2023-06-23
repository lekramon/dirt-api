package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StatusEnumConverterTest {

    private final StatusEnumConverter statusEnumConverter = new StatusEnumConverter();

    @Test
    public void shouldConvertToDatabaseColumn() {
        StatusEnum statusEnum = StatusEnum.SUCCESS;
        Integer databaseColumn = statusEnumConverter.convertToDatabaseColumn(statusEnum);
        Assertions.assertEquals(statusEnum.getCodStatus(), databaseColumn);
    }

    @Test
    public void shouldReturnNullToDatabaseColumn() {
        Integer nullDatabaseColumn = statusEnumConverter.convertToDatabaseColumn(null);
        Assertions.assertNull(nullDatabaseColumn);
    }

    @Test
    public void shouldConvertToEntityAttribute() {
        int statusEnumValue = 1;
        StatusEnum statusEnum = statusEnumConverter.convertToEntityAttribute(statusEnumValue);
        Assertions.assertEquals(StatusEnum.fromCode(statusEnumValue), statusEnum);
    }

    @Test
    public void shouldReturnNullToEntityAttribute() {
        StatusEnum statusEnum = statusEnumConverter.convertToEntityAttribute(null);
        Assertions.assertNull(statusEnum);
    }

    @Test
    public void shouldThrowEnumNotExistExceptionToStatus() {
        int statusValue = 6;
        Assertions.assertThrows(EnumNotExistException.class, () -> {
            StatusEnum statusEnum = statusEnumConverter.convertToEntityAttribute(statusValue);
        });
    }
}