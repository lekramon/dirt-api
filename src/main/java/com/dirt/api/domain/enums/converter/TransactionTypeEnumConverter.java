package com.dirt.api.domain.enums.converter;


import com.dirt.api.domain.enums.TransactionTypeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TransactionTypeEnumConverter implements AttributeConverter<TransactionTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TransactionTypeEnum transactionTypeEnum) {
        if (transactionTypeEnum == null) {
            return null;
        }
        return transactionTypeEnum.getTransactionTypeCod();
    }

    @Override
    public TransactionTypeEnum convertToEntityAttribute(Integer type) {
        if (type == null) {
            return null;
        }

        return TransactionTypeEnum.fromCode(type);
    }
}
