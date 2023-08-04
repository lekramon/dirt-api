package com.dirt.api.cucumber;

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

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteTransactionSteps {

    private static final String SERVER = "http://localhost:";
    private final TransactionRepository transactionRepository;
    private final int serverPort;
    private ResponseEntity<Object> transactionResponseEntity;

    public DeleteTransactionSteps(TransactionRepository transactionRepository, @LocalServerPort int serverPort) {
        this.transactionRepository = transactionRepository;
        this.serverPort = serverPort;
    }

    @Dado("que haja uma transação com os seguintes parâmetros no banco de dados")
    public void queHajaUmaTransaçãoComOsSeguintesParâmetrosNoBancoDeDados(TransactionEntity transactionEntity) {
        transactionRepository.save(transactionEntity);
    }

    @Quando("for requisitada que a transação de id {int} seja apagada")
    public void forRequisitadaQueATransaçãoDeIdSejaApagada(int transactionId) {
        RestAssured.baseURI = SERVER + serverPort;

        final Response response = given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete("/transaction/" + transactionId)
                .then()
                .extract().response();

        this.transactionResponseEntity = createResponseEntityFromResponse(response);
    }

    @Então("o serviço de apagar deve retornar o status code {int} - {string}")
    public void oServiçoDeApagarDeveRetornarOStatusCode(int expectedStatusCode, String expectedDesCode) {
        assertEquals(expectedStatusCode, transactionResponseEntity.getStatusCodeValue());
        assertEquals(expectedDesCode, HttpStatus.valueOf(transactionResponseEntity.getStatusCodeValue()).getReasonPhrase());
    }

    @Então("a transação deve ter sido apagada no banco de dados")
    public void aTransaçãoDeveTerSidoApagadaNoBancoDeDados() {
        assertNull(transactionResponseEntity.getBody());
    }

    private ResponseEntity<Object> createResponseEntityFromResponse(Response response) {
        if (response.getStatusCode() == HttpStatus.OK.value()) {
            return new ResponseEntity<>(HttpStatus.valueOf(response.getStatusCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}
