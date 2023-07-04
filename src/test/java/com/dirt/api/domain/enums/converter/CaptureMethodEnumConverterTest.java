package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CaptureMethodEnumConverterTest {

    private final CaptureMethodEnumConverter captureMethodEnumConverter = new CaptureMethodEnumConverter();

    private static final String INVALID_CAPTURE_METHOD = "FAX";
    private static final String EXCEPTION_MESSAGE = "The captureMethodType: " + INVALID_CAPTURE_METHOD + " doesn't exist";

    @Test
    public void shouldConvertToDatabaseColumn() {
        final CaptureMethodEnum captureMethodEnum = CaptureMethodEnum.WEB;
        final Integer databaseColumn = captureMethodEnumConverter.convertToDatabaseColumn(captureMethodEnum);
        Assertions.assertEquals(captureMethodEnum.getCaptureMethodTypeCode(), databaseColumn);
    }

    @Test
    public void shouldReturnNullToDatabaseColumn() {
        final Integer nullDatabaseColumn = captureMethodEnumConverter.convertToDatabaseColumn(null);
        Assertions.assertNull(nullDatabaseColumn);
    }

    @Test
    public void shouldConvertToEntityAttribute() {
        final int captureMethodValue = 1;
        final CaptureMethodEnum captureMethodEnum = captureMethodEnumConverter.convertToEntityAttribute(captureMethodValue);
        Assertions.assertEquals(CaptureMethodEnum.fromCode(captureMethodValue), captureMethodEnum);
    }

    @Test
    public void shouldNotConvertToEntityAttributeFromCode() {
        final int captureMethodValue = 4;
        Assertions.assertThrows(EnumNotExistException.class, () -> {
            captureMethodEnumConverter.convertToEntityAttribute(captureMethodValue);
        });
    }

    @Test
    public void shouldReturnNullToEntityAttribute() {
        final CaptureMethodEnum captureMethodEnum = captureMethodEnumConverter.convertToEntityAttribute(null);
        Assertions.assertNull(captureMethodEnum);
    }

    @Test
    public void shouldThrowEnumNotExistExceptionToCaptureMethodFromValue() {
        final EnumNotExistException exception = Assertions.assertThrows(EnumNotExistException.class, () -> {
            CaptureMethodEnum.fromValue(INVALID_CAPTURE_METHOD);
        });

        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(EXCEPTION_MESSAGE, actualMessage);
    }
}