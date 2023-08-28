package com.dirt.api.adapter.controller;


import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.dto.request.TransactionSearch;
import com.dirt.api.adapter.dto.request.UpdateStatusRequest;
import com.dirt.api.adapter.dto.response.TransactionListResponse;
import com.dirt.api.adapter.dto.response.TransactionResponse;
import com.dirt.api.usecase.factory.TransactionResponseFactory;
import com.dirt.api.usecase.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
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
    public ResponseEntity<TransactionResponse> updateTransactionStatus(@PathVariable("id") long transactionId, @Valid @RequestBody UpdateStatusRequest updateStatusRequest) {
        final TransactionResponse transactionResponse = TransactionResponseFactory.createTransactionResponse(transactionService.updateTransactionStatusById(transactionId, updateStatusRequest));
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable("id") long transactionId) {
        final TransactionResponse transactionResponse = TransactionResponseFactory.createTransactionResponse(transactionService.getTransactionById(transactionId));
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
    }

    @GetMapping
    public ResponseEntity<TransactionListResponse> getTransactionsList(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                       @RequestParam(name = "captureMethodType", required = false) String captureMethodType,
                                                                       @RequestParam(name = "transactionType", required = false) String transactionType) {
        final TransactionSearch transactionSearch = new TransactionSearch(captureMethodType, transactionType);
        final TransactionListResponse transactionListResponse = TransactionResponseFactory.createListTransactionResponse(transactionService.getTransactionList(page, transactionSearch));

        return ResponseEntity.status(HttpStatus.OK).body(transactionListResponse);
    }
}
