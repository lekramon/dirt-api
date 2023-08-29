package com.dirt.api.usecase.factory;

import com.dirt.api.adapter.dto.request.TransactionSearch;
import com.dirt.api.domain.entity.QTransactionEntity;
import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;
import com.querydsl.core.types.dsl.BooleanExpression;

public class TransactionSearchPredicateFactory {

    private TransactionSearchPredicateFactory() {
    }

    public static BooleanExpression createPredicate(TransactionSearch transactionSearch) {

        final QTransactionEntity transactionEntity = QTransactionEntity.transactionEntity;
        BooleanExpression booleanExpression = transactionEntity.isNotNull();

        if (transactionSearch.getCaptureMethodType() != null) {
            booleanExpression = booleanExpression.and(transactionEntity.captureMethod.eq(CaptureMethodEnum.fromValue(transactionSearch.getCaptureMethodType())));
        }

        if (transactionSearch.getTransactionType() != null) {
            booleanExpression = booleanExpression.and(transactionEntity.transactionType.eq(TransactionTypeEnum.fromValue(transactionSearch.getTransactionType())));
        }
        return booleanExpression;
    }
}

