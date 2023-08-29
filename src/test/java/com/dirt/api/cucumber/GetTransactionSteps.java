package com.dirt.api.cucumber;

import com.dirt.api.adapter.dto.response.TransactionListResponse;
import com.dirt.api.adapter.dto.response.TransactionResponse;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.TransactionEntity;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTransactionSteps {

    private static final int DEFAULT_PAGE = 0;
    private static final String SERVER = "http://localhost:";
    private final TransactionRepository transactionRepository;
    private final int serverPort;

    private ResponseEntity<TransactionListResponse> responseEntity;

    public GetTransactionSteps(TransactionRepository transactionRepository, @LocalServerPort int serverPort) {
        this.transactionRepository = transactionRepository;
        this.serverPort = serverPort;
    }

    @Dado("que existam as seguintes transações no banco de dados")
    public void queExistamAsSeguintesTransaçõesNoBancoDeDados(List<TransactionEntity> transactionEntity) {
        transactionRepository.saveAll(transactionEntity);
        List<TransactionEntity> transactionEntityOptional = transactionRepository.findAll();
    }

    @Quando("uma requisição de consulta de transações seja realizada")
    public void umaRequisiçãoDeConsultaDeTransaçõesSejaRealizada() {
        getTransactionList(DEFAULT_PAGE);
    }

    @Então("o serviço de listagem deve retornar o status code {int} - {string}")
    public void oServiçoDeListagemDeveRetornarOStatusCode(int expectedStatusCode, String expectedDesCode) {
        assertEquals(expectedStatusCode, responseEntity.getStatusCodeValue());
        assertEquals(expectedDesCode, HttpStatus.valueOf(responseEntity.getStatusCodeValue()).getReasonPhrase());
    }

    @E("as seguintes transações devem ser retornadas para a página {int}")
    public void asSeguintesTransaçõesDevemSerRetornadasParaAPágina(int page, List<TransactionResponse> transactionResponse) {
        getTransactionList(page);

        final TransactionListResponse actualTransactionListResponse = responseEntity.getBody();
        assert actualTransactionListResponse != null;

        final TransactionListResponse expectedTransactionListResponse = new TransactionListResponse(transactionResponse.size(), actualTransactionListResponse.getTotalSize(), page, actualTransactionListResponse.getTotalPages(), transactionResponse);

        assertThat(actualTransactionListResponse).usingRecursiveComparison().isEqualTo(expectedTransactionListResponse);
    }

    private ResponseEntity<TransactionListResponse> createResponseEntityFromResponse(Response response) {
        if (response.getStatusCode() == HttpStatus.OK.value()) {
            return new ResponseEntity<>(response.as(TransactionListResponse.class), HttpStatus.valueOf(response.getStatusCode()));
        }
        return null;
    }

    private void getTransactionList(int page) {
        RestAssured.baseURI = SERVER + serverPort;

        final Response response = given()
                .header("Content-type", "application/json")
                .queryParam("page", page)
                .when()
                .get("/transactions")
                .then()
                .extract().response();

        this.responseEntity = createResponseEntityFromResponse(response);
    }

}
