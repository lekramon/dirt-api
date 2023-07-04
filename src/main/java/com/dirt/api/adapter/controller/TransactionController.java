package com.dirt.api.adapter.controller;


import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.dto.response.TransactionResponse;
import com.dirt.api.usecase.factory.TransactionResponseFactory;
import com.dirt.api.usecase.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> registerTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        final TransactionResponse transactionResponse = TransactionResponseFactory.createTransactionResponse(transactionService.register(transactionRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
    }
}
