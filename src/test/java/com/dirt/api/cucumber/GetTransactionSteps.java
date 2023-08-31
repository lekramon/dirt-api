package com.dirt.api.cucumber;

import com.dirt.api.adapter.dto.response.TransactionListResponse;
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

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTransactionSteps {

    private static final int DEFAULT_PAGE = 0;
    private static final String SERVER = "http://localhost:";
    private final TransactionRepository transactionRepository;
    private final int serverPort;
    private ResponseEntity<Object> transactionListResponseEntity;
    private ResponseEntity<Object> transactionResponseEntity;
    private final HashMap<String, String> params = new HashMap<String, String>();

    public GetTransactionSteps(TransactionRepository transactionRepository, @LocalServerPort int serverPort) {
        this.transactionRepository = transactionRepository;
        this.serverPort = serverPort;
    }

    @Dado("que existam as seguintes transações no banco de dados")
    public void shouldExistTransactionInTheDatabase(List<TransactionEntity> transactionEntity) {
        transactionRepository.saveAll(transactionEntity);
    }

    @Quando("uma requisição de consulta de transações for realizada")
    public void callGetTransactionWithDefaultPage() {
        getTransactionList(DEFAULT_PAGE);
    }

    @Quando("uma requisição de consulta de transações com a página {int} for realizada")
    public void callGetTransactionWithPage(int page) {
        getTransactionList(page);
    }

    @Quando("uma requisição de consulta de transações com transactionId {int} seja realizada")
    public void callGetTransactionById(int transactionId) {
        getTransactionById(transactionId);
    }

    @Quando("uma requisição de consulta de transações com os parâmetros {string} valor {string} seja realizada")
    public void givenRequestParameter(String key, String value) {
        params.put(key, value);
        getTransactionList(DEFAULT_PAGE);
    }

    @Quando("uma requisição de consulta de transações com os parâmetros {string} valor {string} e {string} valor {string} seja realizada")
    public void givenRequestParameter(String firstKey, String firstValue, String secondKey, String secondValue) {
        givenRequestParameter(firstKey, firstValue);
        givenRequestParameter(secondKey, secondValue);
    }

    @Então("o serviço de listagem deve retornar o status code {int} - {string}")
    public void getServiceShouldReturnStatusCode(int expectedStatusCode, String expectedDesCode) {
        assertEquals(expectedStatusCode, transactionListResponseEntity.getStatusCodeValue());
        assertEquals(expectedDesCode, HttpStatus.valueOf(transactionListResponseEntity.getStatusCodeValue()).getReasonPhrase());
    }

    @Então("o serviço de listagem pelo id deve retornar o status code {int} - {string}")
    public void getServiceByIdShouldReturnStatusCode(int expectedStatusCode, String expectedDesCode) {
        assertEquals(expectedStatusCode, transactionResponseEntity.getStatusCodeValue());
        assertEquals(expectedDesCode, HttpStatus.valueOf(transactionResponseEntity.getStatusCodeValue()).getReasonPhrase());
    }

    @Então("as seguintes transações devem ser retornadas")
    public void thenReturnTransactions(List<TransactionResponse> transactionResponse) {
        final TransactionListResponse actualTransactionListResponse = (TransactionListResponse) transactionListResponseEntity.getBody();
        assert actualTransactionListResponse != null;

        final TransactionListResponse expectedTransactionListResponse = new TransactionListResponse(transactionResponse.size(), actualTransactionListResponse.getTotalSize(), actualTransactionListResponse.getPage(), actualTransactionListResponse.getTotalPages(), transactionResponse);

        assertThat(actualTransactionListResponse).usingRecursiveComparison().isEqualTo(expectedTransactionListResponse);
    }

    @Então("a seguinte transação deve ser retornada")
    public void thenReturnTransaction(TransactionResponse transactionResponse) {
        final TransactionResponse actualTransactionResponse = (TransactionResponse) transactionResponseEntity.getBody();

        assertThat(actualTransactionResponse).usingRecursiveComparison().isEqualTo(transactionResponse);
    }

    @Então("o conteúdo retornado deve ser vazio")
    public void thenReturnNullContent() {
        final TransactionListResponse actualTransactionListResponse = (TransactionListResponse) transactionListResponseEntity.getBody();
        assert actualTransactionListResponse != null;
        assertEquals(0, actualTransactionListResponse.getTotalSize());
    }

    private ResponseEntity<Object> createListResponseEntityFromResponse(Response response) {
        if (response.getStatusCode() == HttpStatus.OK.value()) {
            return new ResponseEntity<>(response.as(TransactionListResponse.class), HttpStatus.valueOf(response.getStatusCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    private ResponseEntity<Object> createResponseEntityFromResponse(Response response) {
        if (response.getStatusCode() == HttpStatus.OK.value()) {
            return new ResponseEntity<>(response.as(TransactionResponse.class), HttpStatus.valueOf(response.getStatusCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    private void getTransactionList(int page) {
        RestAssured.baseURI = SERVER + serverPort;

        final Response response = given()
                .header("Content-type", "application/json")
                .when()
                .queryParam("page", page)
                .queryParams(params)
                .get("/transactions")
                .then()
                .extract().response();

        this.transactionListResponseEntity = createListResponseEntityFromResponse(response);
    }

    private void getTransactionById(int transactionId) {
        RestAssured.baseURI = SERVER + serverPort;

        final Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get("/transactions/" + transactionId)
                .then()
                .extract().response();

        this.transactionResponseEntity = createResponseEntityFromResponse(response);
    }
}
