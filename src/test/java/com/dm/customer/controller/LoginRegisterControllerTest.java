package com.dm.customer.controller;

import com.dm.customer.dto.userlogin.LoginRequest;
import com.dm.customer.exception.RestApiResponse;
import com.dm.customer.model.LoginRegisterModel;
import com.dm.customer.service.LoginRegisterService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class LoginRegisterControllerTest {

    @Inject
    LoginRegisterController loginRegisterController;

    @Inject
    LoginRegisterService loginRegisterService;

    @Test
    void userLoginWhenUserEmailIsBlankOrNull() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("");
        loginRequest.setPassword("password");
        HttpResponse<RestApiResponse> response = loginRegisterController.userLogin(loginRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void userLoginWhenUserEmailIsInvalid() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("email");
        loginRequest.setPassword("password");
        HttpResponse<RestApiResponse> response = loginRegisterController.userLogin(loginRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void userLoginWhenPasswordIsBlankOrNull() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("user@test.com");
        loginRequest.setPassword("");
        HttpResponse<RestApiResponse> response = loginRegisterController.userLogin(loginRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void userLoginWhenSuccess() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("user@test.com");
        loginRequest.setPassword("password");
        HttpResponse<RestApiResponse> response = loginRegisterController.userLogin(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @ParameterizedTest
    @CsvSource({"1, , user@test.com, password", "1, User, , password", "1, User, user@test.com, password", "1, User, user, "})
    void postUserRegisterWhenInputsAreInvalidAndNullOrBlankThenReturnBadRequest(String userId, String userName, String userEmail, String password) {
        LoginRegisterModel loginRegisterModel = new LoginRegisterModel();
        loginRegisterModel.setUserId(Integer.parseInt(userId));
        loginRegisterModel.setUserName(userName);
        loginRegisterModel.setUserEmail(userEmail);
        loginRegisterModel.setPassword(password);
        HttpResponse<RestApiResponse> response = loginRegisterController.userRegister(loginRegisterModel);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void userRegisterWhenUserAlreadyExists() {
        LoginRegisterModel loginRegisterModel = new LoginRegisterModel();
        loginRegisterModel.setUserId(Integer.parseInt("1"));
        loginRegisterModel.setUserName("User");
        loginRegisterModel.setUserEmail("user@test.com");
        loginRegisterModel.setPassword("password");
        HttpResponse<RestApiResponse> response = loginRegisterController.userRegister(loginRegisterModel);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    @DisplayName("should return bad request when login credentials are invalid")
    void userLoginWhenCredentialsAreInvalid() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("user@example.com");
        loginRequest.setPassword("password");
        HttpResponse<RestApiResponse> response = loginRegisterController.userLogin(loginRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void userRegisterWhenSuccess() {
        LoginRegisterModel loginRegisterModel = new LoginRegisterModel();
        loginRegisterModel.setUserName("User");
        loginRegisterModel.setUserEmail("user@example.com");
        loginRegisterModel.setPassword("password");
        HttpResponse<RestApiResponse> response = loginRegisterController.userRegister(loginRegisterModel);
        assertEquals(HttpStatus.OK, response.getStatus());
    }


}
