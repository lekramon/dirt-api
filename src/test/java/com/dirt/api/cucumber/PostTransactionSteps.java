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
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PostTransactionSteps {

    private static final String SERVER = "http://localhost:";
    private final TransactionRepository transactionRepository;
    private final DataSource dataSource;
    private TransactionRequest transaction;
    private ResponseEntity<Object> transactionResponseEntity;
    @LocalServerPort
    private int serverPort;

    public PostTransactionSteps(TransactionRepository transactionRepository, DataSource dataSource) {
        this.transactionRepository = transactionRepository;
        this.dataSource = dataSource;
    }


    @Dado("que exista uma requisição com os seguintes parâmetros")
    public void queSejaFeitoUmaRequisiçãoComOsSeguintesParâmetros(TransactionRequest transactionRequest) {
        transaction = transactionRequest;
    }

    @Quando("o serviço de registro de transações for chamado")
    public void oServiçoDeRegistroDeTransaçõesForChamado() {
        RestAssured.baseURI = SERVER + serverPort;

        final Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(transaction)
                .when()
                .post("/transaction")
                .then()
                .extract().response();

        this.transactionResponseEntity = createResponseEntityFromResponse(response);
    }

    @Quando("o serviço de registro de transações for chamado e estiver indisponível")
    public void oServiçoDeRegistroDeTransaçõesForChamadoEEstiverIndisponível() {
        new JdbcTemplate(dataSource).execute("SHUTDOWN");

        final Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(transaction)
                .when()
                .post("/transaction")
                .then()
                .extract().response();

        this.transactionResponseEntity = createResponseEntityFromResponse(response);
    }

    @Então("o serviço deve retornar status code {int} - {string}")
    public void oServiçoDeveRetornarStatusCode(int expectedStatusCode, String expectedDesCode) {
        Assertions.assertEquals(expectedStatusCode, transactionResponseEntity.getStatusCodeValue());
        Assertions.assertEquals(expectedDesCode, HttpStatus.valueOf(transactionResponseEntity.getStatusCodeValue()).getReasonPhrase());
    }

    @Então("a transação deve ser registrada na base de dados com os seguintes dados")
    public void aTransaçãoDeveSerRegistradaNaBaseDeDados(TransactionEntity transactionEntity) {
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

