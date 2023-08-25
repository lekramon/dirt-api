package com.dirt.api.usecase.validator;

import com.dirt.api.adapter.dto.request.TransactionSearch;
import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class TransactionSearchValidator {

    private TransactionSearchValidator() {
    }

    public static TransactionSearch validate(TransactionSearch transactionSearch) {
        validateCaptureMethodType(transactionSearch.getCaptureMethodType());
        validateTransactionType(transactionSearch.getTransactionType());
        return transactionSearch;
    }

    private static void validateCaptureMethodType(String captureMethodType) {
        if (captureMethodType != null) {
            CaptureMethodEnum.fromValue(captureMethodType);
        }
    }

    private static void validateTransactionType(String transactionType) {
        if (transactionType != null) {
            TransactionTypeEnum.fromValue(transactionType);
        }
    }

}
