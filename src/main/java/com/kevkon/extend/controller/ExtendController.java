package com.kevkon.extend.controller;

import com.kevkon.extend.exception.WebServiceException;
import com.kevkon.extend.model.*;
import com.kevkon.extend.service.ExtendApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class ExtendController {
    private final Logger LOGGER = LoggerFactory.getLogger(ExtendController.class);

    private final String TOKEN = "TOKEN";
    private final String REFRESH_TOKEN = "REFRESH_TOKEN";

    @Autowired
    private ExtendApiService extendApiService;

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpSession session, final @RequestBody User user) {

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found in request.");
        }

        try {
            LoginResponse loginResponse = extendApiService.login(user.getEmail(), user.getPassword());
            LOGGER.info("Logged in, Token = " + loginResponse.getToken());

            // TODO: Automatically refresh asynchronously after any action and save new token?
            session.setAttribute(TOKEN, loginResponse.getToken());
            session.setAttribute(REFRESH_TOKEN, loginResponse.getRefreshToken());

            return ResponseEntity.status(HttpStatus.OK).body("Successfully logged into Extend API");
        } catch (WebServiceException se) {
            return ResponseEntity.status(se.getStatusCode()).body(se.getMessage());
        }
    }

    @GetMapping("/virtualcards")
    public ResponseEntity<VirtualCardsResponse> getVirtualCards(HttpSession session) {

        // TODO: Place into an auth filter that handles the session
        if (session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String token = session.getAttribute(TOKEN).toString();

        try {
            VirtualCardsResponse virtualCardsResponse = extendApiService.getVirtualCards(token);
            return ResponseEntity.status(HttpStatus.OK).body(virtualCardsResponse);
        } catch (WebServiceException se) {
            return ResponseEntity.status(se.getStatusCode()).body(null);
        }
    }

    @GetMapping("/virtualcards/{id}/transactions")
    public ResponseEntity<TransactionResponse> getTransactionsForCard(HttpSession session, final @PathVariable(value="id") String cardId) {

        // TODO: Place into an auth filter that handles the session
        if (session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String token = session.getAttribute(TOKEN).toString();

        try {
            TransactionResponse transactionResponse = extendApiService.getTransactionsForCard(token, cardId);
            return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
        } catch (WebServiceException se) {
            return ResponseEntity.status(se.getStatusCode()).body(null);
        }
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransaction(HttpSession session, final @PathVariable(value="id") String transactionId) {

        // TODO: Place into an auth filter that handles the session
        if (session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String token = session.getAttribute(TOKEN).toString();

        try {
            Transaction transaction = extendApiService.getTransaction(token, transactionId);
            return ResponseEntity.status(HttpStatus.OK).body(transaction);
        } catch (WebServiceException se) {
            return ResponseEntity.status(se.getStatusCode()).body(null);
        }
    }

}
