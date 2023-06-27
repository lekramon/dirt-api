package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CaptureMethodEnumConverterTest {

    private final CaptureMethodEnumConverter captureMethodEnumConverter = new CaptureMethodEnumConverter();

    @Test
    public void shouldConvertToDatabaseColumn() {
        CaptureMethodEnum captureMethodEnum = CaptureMethodEnum.WEB;
        Integer databaseColumn = captureMethodEnumConverter.convertToDatabaseColumn(captureMethodEnum);
        Assertions.assertEquals(captureMethodEnum.getCaptureMethodTypeCode(), databaseColumn);
    }

    @Test
    public void shouldReturnNullToDatabaseColumn() {
        Integer nullDatabaseColumn = captureMethodEnumConverter.convertToDatabaseColumn(null);
        Assertions.assertNull(nullDatabaseColumn);
    }

    @Test
    public void shouldConvertToEntityAttribute() {
        int captureMethodValue = 1;
        CaptureMethodEnum captureMethodEnum = captureMethodEnumConverter.convertToEntityAttribute(captureMethodValue);
        Assertions.assertEquals(CaptureMethodEnum.fromCode(captureMethodValue), captureMethodEnum);
    }

    @Test
    public void shouldReturnNullToEntityAttribute() {
        CaptureMethodEnum captureMethodEnum = captureMethodEnumConverter.convertToEntityAttribute(null);
        Assertions.assertNull(captureMethodEnum);
    }

    @Test
    public void shouldThrowEnumNotExistExceptionToCaptureMethod() {
        int captureMethodValue = 6;
        Assertions.assertThrows(EnumNotExistException.class, () -> {
            CaptureMethodEnum captureMethodEnum = captureMethodEnumConverter.convertToEntityAttribute(captureMethodValue);
        });
    }
}