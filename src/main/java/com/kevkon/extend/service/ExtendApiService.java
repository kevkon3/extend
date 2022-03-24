package com.kevkon.extend.service;

import com.kevkon.extend.exception.WebServiceException;
import com.kevkon.extend.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

@Service
public class ExtendApiService {
    private final Logger LOGGER = LoggerFactory.getLogger(ExtendApiService.class);

    private final WebClient webClient;

    @Value("${extend.rest.host}")
    private String host;

    @Value("${extend.rest.accept.type}")
    private String acceptType;

    public ExtendApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public LoginResponse login(final String email, final String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(host)
                        .path("/signin")
                        .build())
                .body(Mono.just(loginRequest), LoginRequest.class)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, acceptType)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new WebServiceException("Error calling signin API.", response.statusCode().value())))
                .bodyToMono(LoginResponse.class)
                .block();
    }

    public VirtualCardsResponse getVirtualCards(final String token) throws WebServiceException {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(host)
                        .path("/virtualcards")
                        .build())
                .headers(h -> h.setBearerAuth(token))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, acceptType)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new WebServiceException("Error calling virtualcards API.", response.statusCode().value())))
                .bodyToMono(VirtualCardsResponse.class)
                .block();
    }

    public TransactionResponse getTransactionsForCard(final String token, final String cardId) {
        String statuses = "PENDING,CLEARED,DECLINED,NO_MATCH,AVS_PASS,AVS_FAIL";

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(host)
                        .path("/virtualcards/{cardId}/transactions")
                        .queryParam("status", statuses)
                        .build(cardId))
                .headers(h -> h.setBearerAuth(token))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, acceptType)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new WebServiceException("Error calling virtualcards transactions API.", response.statusCode().value())))
                .bodyToMono(TransactionResponse.class)
                .block();
    }

    public Transaction getTransaction(final String token, final String transactionId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(host)
                        .path("/transactions/{transactionId}")
                        .build(transactionId))
                .headers(h -> h.setBearerAuth(token))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, acceptType)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new WebServiceException("Error calling transactions API.", response.statusCode().value())))
                .bodyToMono(Transaction.class)
                .block();
    }

}
