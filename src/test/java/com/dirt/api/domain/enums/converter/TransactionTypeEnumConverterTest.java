package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.TransactionTypeEnum;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionTypeEnumConverterTest {

    private final TransactionTypeEnumConverter transactionTypeEnumConverter = new TransactionTypeEnumConverter();

    private static final String INVALID_TRANSACTION_TYPE = "BOLETO";
    private static final String EXCEPTION_MESSAGE = "The transactionType: " + INVALID_TRANSACTION_TYPE + " doesn't exist";

    @Test
    public void shouldConvertToDatabaseColumn() {
        final TransactionTypeEnum transactionTypeEnum = TransactionTypeEnum.PIX;
        final Integer databaseColumn = transactionTypeEnumConverter.convertToDatabaseColumn(transactionTypeEnum);
        Assertions.assertEquals(transactionTypeEnum.getTransactionTypeCod(), databaseColumn);
    }

    @Test
    public void shouldReturnNullToDatabaseColumn() {
        final Integer nullDatabaseColumn = transactionTypeEnumConverter.convertToDatabaseColumn(null);
        Assertions.assertNull(nullDatabaseColumn);
    }

    @Test
    public void shouldConvertToEntityAttribute() {
        final int transactionTypeValue = 1;
        final TransactionTypeEnum transactionTypeEnum = transactionTypeEnumConverter.convertToEntityAttribute(transactionTypeValue);
        Assertions.assertEquals(TransactionTypeEnum.fromCode(transactionTypeValue), transactionTypeEnum);
    }

    @Test
    public void shouldNotConvertToEntityAttributeFromCode() {
        final int transactionTypeValue = 4;
        Assertions.assertThrows(EnumNotExistException.class, () -> {
            transactionTypeEnumConverter.convertToEntityAttribute(transactionTypeValue);
        });
    }

    @Test
    public void shouldReturnNullToEntityAttribute() {
        final TransactionTypeEnum transactionTypeEnum = transactionTypeEnumConverter.convertToEntityAttribute(null);
        Assertions.assertNull(transactionTypeEnum);
    }

    @Test
    public void shouldThrowEnumNotExistExceptionToTransactionType() {
        final EnumNotExistException exception = Assertions.assertThrows(EnumNotExistException.class, () -> {
            TransactionTypeEnum.fromValue(INVALID_TRANSACTION_TYPE);
        });

        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(EXCEPTION_MESSAGE, actualMessage);
    }
}