package com.dirt.api.cucumber;

import com.dirt.api.adapter.dto.request.UpdateStatusRequest;
import com.dirt.api.adapter.dto.response.TransactionResponse;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.enums.StatusEnum;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutTransactionSteps {

    private static final String SERVER = "http://localhost:";
    private final TransactionRepository transactionRepository;
    private final int serverPort;
    private ResponseEntity<Object> transactionResponseEntity;

    public PutTransactionSteps(TransactionRepository transactionRepository, @LocalServerPort int serverPort) {
        this.transactionRepository = transactionRepository;
        this.serverPort = serverPort;
    }

    @Dado("que exista uma transação com os seguintes parâmetros no banco de dados")
    public void shouldExistTransactionWithParametersInTheDatabase(TransactionEntity transactionEntity) {
        transactionRepository.save(transactionEntity);
    }

    @Quando("for requisitada uma alteração de status para {string} na transação de id {int}")
    public void callUpdateStatusForTransactionId(String status, int transactionId) {
        RestAssured.baseURI = SERVER + serverPort;

        final Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(getUpdateStatusRequest(status))
                .when()
                .put("/transaction/" + transactionId)
                .then()
                .extract().response();

        this.transactionResponseEntity = createResponseEntityFromResponse(response);
    }

    @Então("o serviço de atualização deve retornar o status code {int} - {string}")
    public void serviceShouldReturnStatusCode(int expectedStatusCode, String expectedDesCode) {
        assertEquals(expectedStatusCode, transactionResponseEntity.getStatusCodeValue());
        assertEquals(expectedDesCode, HttpStatus.valueOf(transactionResponseEntity.getStatusCodeValue()).getReasonPhrase());
    }

    @Então("a transação deve ter o status alterado para {string} na base de dados")
    public void transactionStatusShouldBeUpdatedInDatabase(String status, TransactionEntity transactionEntity) {
        final StatusEnum statusEnum = StatusEnum.fromValue(status);

        final Optional<TransactionEntity> optionalTransactionEntity = transactionRepository.findById(transactionEntity.getTransactionId());

        final TransactionEntity actualTransactionEntity = optionalTransactionEntity.orElse(null);

        assertThat(actualTransactionEntity.getStatus()).usingRecursiveComparison().isEqualTo(statusEnum);
        assertThat(actualTransactionEntity).usingRecursiveComparison().isEqualTo(transactionEntity);
    }

    private ResponseEntity<Object> createResponseEntityFromResponse(Response response) {
        if (response.getStatusCode() == HttpStatus.OK.value()) {
            return new ResponseEntity<>(response.as(TransactionResponse.class), HttpStatus.valueOf(response.getStatusCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    private UpdateStatusRequest getUpdateStatusRequest(String status) {
        final UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();

        updateStatusRequest.setStatus(status);

        return updateStatusRequest;
    }
}
