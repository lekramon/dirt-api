package com.dirt.api.cucumber;

import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.dto.response.TransactionResponse;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.TransactionEntity;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTransactionSteps {

    private static final String SERVER = "http://localhost:";
    private final TransactionRepository transactionRepository;
    private final DataSource dataSource;
    private final int serverPort;
    private TransactionRequest transaction;
    private ResponseEntity<Object> transactionResponseEntity;

    public PostTransactionSteps(TransactionRepository transactionRepository, DataSource dataSource, @LocalServerPort int serverPort) {
        this.transactionRepository = transactionRepository;
        this.dataSource = dataSource;
        this.serverPort = serverPort;
    }

    @Dado("que exista uma requisição com os seguintes parâmetros")
    public void shouldExistRequestWithParameters(TransactionRequest transactionRequest) {
        transaction = transactionRequest;
    }

    @Dado("que o serviço esteja indisponível")
    public void serviceIsUnavailable() {
        new JdbcTemplate(dataSource).execute("SHUTDOWN");
    }

    @Quando("o serviço de registro de transações for chamado")
    public void callPostTransactionService() {
        RestAssured.baseURI = SERVER + serverPort;

        final Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(transaction)
                .when()
                .post("/transactions")
                .then()
                .extract().response();

        this.transactionResponseEntity = createResponseEntityFromResponse(response);
    }

    @Então("o serviço de registro deve retornar o status code {int} - {string}")
    public void serviceShouldReturnStatusCode(int expectedStatusCode, String expectedDesCode) {
        assertEquals(expectedStatusCode, transactionResponseEntity.getStatusCodeValue());
        assertEquals(expectedDesCode, HttpStatus.valueOf(transactionResponseEntity.getStatusCodeValue()).getReasonPhrase());
    }

    @Então("a transação deve ser registrada na base de dados com os seguintes dados")
    public void transactionShouldBeRegisteredInDatabase(TransactionEntity transactionEntity) {
        final Optional<TransactionEntity> optionalTransactionEntity = transactionRepository.findById(transactionEntity.getTransactionId());

        final TransactionEntity actualTransactionEntity = optionalTransactionEntity.orElse(null);

        assertThat(actualTransactionEntity).usingRecursiveComparison().ignoringFields("transactionDat").isEqualTo(transactionEntity);
    }

    private ResponseEntity<Object> createResponseEntityFromResponse(Response response) {
        if (response.getStatusCode() == HttpStatus.CREATED.value()) {
            return new ResponseEntity<>(response.as(TransactionResponse.class), HttpStatus.valueOf(response.getStatusCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}