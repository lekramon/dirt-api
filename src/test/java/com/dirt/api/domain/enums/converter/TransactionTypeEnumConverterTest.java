package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.TransactionTypeEnum;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionTypeEnumConverterTest {

    private final TransactionTypeEnumConverter transactionTypeEnumConverter = new TransactionTypeEnumConverter();

    @Test
    public void shouldConvertToDatabaseColumn() {
        TransactionTypeEnum transactionTypeEnum = TransactionTypeEnum.PIX;
        Integer databaseColumn = transactionTypeEnumConverter.convertToDatabaseColumn(transactionTypeEnum);
        Assertions.assertEquals(transactionTypeEnum.getTransactionTypeCod(), databaseColumn);
    }

    @Test
    public void shouldReturnNullToDatabaseColumn() {
        Integer nullDatabaseColumn = transactionTypeEnumConverter.convertToDatabaseColumn(null);
        Assertions.assertNull(nullDatabaseColumn);
    }

    @Test
    public void shouldConvertToEntityAttribute() {
        int transactionTypeValue = 1;
        TransactionTypeEnum transactionTypeEnum = transactionTypeEnumConverter.convertToEntityAttribute(transactionTypeValue);
        Assertions.assertEquals(TransactionTypeEnum.fromCode(transactionTypeValue), transactionTypeEnum);
    }

    @Test
    public void shouldReturnNullToEntityAttribute() {
        TransactionTypeEnum transactionTypeEnum = transactionTypeEnumConverter.convertToEntityAttribute(null);
        Assertions.assertNull(transactionTypeEnum);
    }

    @Test
    public void shouldThrowEnumNotExistExceptionToTransactionType() {
        int transactionTypeValue = 6;
        Assertions.assertThrows(EnumNotExistException.class, () -> {
            TransactionTypeEnum transactionTypeEnum = transactionTypeEnumConverter.convertToEntityAttribute(transactionTypeValue);
        });
    }
}