package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.TransactionTypeEnum;
import com.dirt.api.domain.exception.EnumNotExistException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TransactionTypeEnumConverter implements AttributeConverter<TransactionTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TransactionTypeEnum transactionTypeEnum) {
        switch (transactionTypeEnum) {
            case PIX:
                return 1;
            case TED:
                return 2;
            case DOC:
                return 3;
            default:
                throw new EnumNotExistException("The transaction type: " + transactionTypeEnum + " doesn't exist");
        }
    }

    @Override
    public TransactionTypeEnum convertToEntityAttribute(Integer transactionType) {
        switch (transactionType) {
            case 1:
                return TransactionTypeEnum.PIX;
            case 2:
                return TransactionTypeEnum.TED;
            case 3:
                return TransactionTypeEnum.DOC;
            default:
                throw new EnumNotExistException("The transaction type: " + transactionType + " doesn't exist");
        }
    }
}