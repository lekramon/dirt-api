package com.dirt.api.usecase.validator;

import com.dirt.api.adapter.dto.request.TransactionSearch;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionSearchValidatorTest {

    private static final String CAPTURE_METHOD_TYPE = "APP";
    private static final String TRANSACTION_TYPE = "PIX";

    @Test
    public void shouldValidateTransactionSearchWithNullableCaptureMethodType() {
        final TransactionSearch actualTransactionSearch = TransactionSearchValidator.validate(getTransactionSearch(null, TRANSACTION_TYPE));
        final TransactionSearch expectedTransactionSearch = getTransactionSearch(null, TRANSACTION_TYPE);

        assertEquals(expectedTransactionSearch.getTransactionType(), actualTransactionSearch.getTransactionType());
        assertEquals(expectedTransactionSearch.getCaptureMethodType(), actualTransactionSearch.getCaptureMethodType());
        assertThat(actualTransactionSearch).usingRecursiveComparison().isEqualTo(expectedTransactionSearch);
    }

    @Test
    public void shouldValidateTransactionSearchWithNullableTransactionType() {
        final TransactionSearch actualTransactionSearch = TransactionSearchValidator.validate(getTransactionSearch(CAPTURE_METHOD_TYPE, null));
        final TransactionSearch expectedTransactionSearch = getTransactionSearch(CAPTURE_METHOD_TYPE, null);

        assertEquals(expectedTransactionSearch.getTransactionType(), actualTransactionSearch.getTransactionType());
        assertEquals(expectedTransactionSearch.getCaptureMethodType(), actualTransactionSearch.getCaptureMethodType());
        assertThat(actualTransactionSearch).usingRecursiveComparison().isEqualTo(expectedTransactionSearch);
    }

    @Test
    public void shouldValidateTransactionSearchWithNullableParameters() {
        final TransactionSearch actualTransactionSearch = TransactionSearchValidator.validate(getTransactionSearch(null, null));
        final TransactionSearch expectedTransactionSearch = getTransactionSearch(null, null);

        assertEquals(expectedTransactionSearch.getTransactionType(), actualTransactionSearch.getTransactionType());
        assertEquals(expectedTransactionSearch.getCaptureMethodType(), actualTransactionSearch.getCaptureMethodType());
        assertThat(actualTransactionSearch).usingRecursiveComparison().isEqualTo(expectedTransactionSearch);
    }

    private TransactionSearch getTransactionSearch(String captureMethodType, String transactionType) {
        return new TransactionSearch(captureMethodType, transactionType);
    }
}