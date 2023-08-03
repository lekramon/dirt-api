package com.dirt.api.adapter.controller;


import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.dto.request.UpdateStatusRequest;
import com.dirt.api.adapter.dto.response.TransactionResponse;
import com.dirt.api.usecase.factory.TransactionResponseFactory;
import com.dirt.api.usecase.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        final TransactionResponse transactionResponse = TransactionResponseFactory.createTransactionResponse(transactionService.registerTransaction(transactionRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransactionStatus(@PathVariable long id, @Valid @RequestBody UpdateStatusRequest updateStatusRequest) {
        final TransactionResponse transactionResponse = TransactionResponseFactory.createTransactionResponse(transactionService.updateTransactionStatusById(id, updateStatusRequest));
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
