package com.kevkon.extend.controller;

import com.kevkon.extend.exception.WebServiceException;
import com.kevkon.extend.model.LoginResponse;
import com.kevkon.extend.model.User;
import com.kevkon.extend.service.ExtendApiService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.session.store-type=none")
public class ExtendControllerTests {

    @MockBean
    private ExtendApiService extendApiService;

    @Autowired
    private ExtendController extendController;

    @Test
    public void testLoginNullUser() {
        MockHttpSession mockSession = new MockHttpSession();

        ResponseEntity<String> response = extendController.login(mockSession, null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testLoginSuccess() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test123");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("token");
        loginResponse.setRefreshToken("refresh");

        MockHttpSession mockSession = new MockHttpSession();
        Mockito.when(extendApiService.login(user.getEmail(), user.getPassword())).thenReturn(loginResponse);

        ResponseEntity<String> response = extendController.login(mockSession, user);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginFail() {
        // TODO: Remove duplicate code
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test123");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("token");
        loginResponse.setRefreshToken("refresh");

        String failMessage = "Failed";
        WebServiceException serviceException = new WebServiceException(failMessage, HttpStatus.BAD_REQUEST.value());

        MockHttpSession mockSession = new MockHttpSession();
        Mockito.when(extendApiService.login(user.getEmail(), user.getPassword())).thenThrow(serviceException);

        ResponseEntity<String> response = extendController.login(mockSession, user);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals(failMessage, response.getBody());
    }

}
