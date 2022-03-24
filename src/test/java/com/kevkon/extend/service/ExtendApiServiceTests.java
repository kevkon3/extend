package com.kevkon.extend.service;

import com.kevkon.extend.model.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class ExtendApiServiceTests {

    @Autowired
    private ExtendApiService extendApiService;

    @Test
    public void testLogin() {
        String email = "crowell.kevin@gmail.com";
        String password = "Kevin123456!";
        LoginResponse response = extendApiService.login(email, password);
        Assert.notNull(response, "LoginResponse should not be null");
    }

}
