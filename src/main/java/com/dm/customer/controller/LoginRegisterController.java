package com.dm.customer.controller;

import com.dm.customer.dto.UserDetails;
import com.dm.customer.dto.userlogin.LoginRequest;
import com.dm.customer.dto.userlogin.LoginRegisterResponse;
import com.dm.customer.exception.RestApiResponse;
import com.dm.customer.httpclient.HttpResponseWrapper;
import com.dm.customer.model.LoginRegisterModel;
import com.dm.customer.repository.LoginRegisterRepository;
import com.dm.customer.service.LoginRegisterService;
import com.dm.customer.service.impl.LoginRegisterServiceImpl;
import com.dm.customer.util.CDMEnum;
import com.dm.customer.util.CDMLogger;
import com.dm.customer.util.CDMUtility;
import com.dm.customer.util.CDMValidator;
import io.micronaut.core.annotation.ReflectiveAccess;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dm.customer.util.APIRoute.USER_LOGIN;
import static com.dm.customer.util.APIRoute.USER_REGISTER;
import static com.dm.customer.util.CDMUtility.*;

/**
 * Login Controller
 */
@Controller
@CrossOrigin//(origins = {"http://localhost:3000"})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginRegisterController {

    final String className = getClass().getSimpleName();
    @Inject
    CDMLogger cdmLogger;
    @Inject
    CDMValidator cdmValidator;
    @Inject
    LoginRegisterService loginRegisterService;
    @Inject
    LoginRegisterServiceImpl loginRegisterServiceImpl;
    @Inject
    LoginRegisterRepository loginRegisterRepository;

    /**
     * Validate and check user data in postgresql if it present or not
     * @param loginRequest
     * @return
     */
    @ReflectiveAccess
    @Post(value = USER_LOGIN)
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = HttpResponseWrapper.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = HttpResponseWrapper.class)))
    public HttpResponse<RestApiResponse> userLogin(@Body LoginRequest loginRequest) {
        LoginRegisterResponse loginRegisterResponse;
        final String methodName = "userLogin";
        final List<CDMEnum> loginEnumList = new ArrayList<>();

        if (CDMUtility.blankOrNullUserEmail(loginRequest.getUserEmail())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_USER_EMAIL_LOGIN);
            loginEnumList.add(CDMEnum.ERROR_REQUEST_USER_EMAIL_BLANK_OR_NULL);
        } else if (CDMUtility.invalidUserEmail(loginRequest.getUserEmail())) {
            cdmLogger.logs(className, methodName, INVALID_USER_EMAIL_LOGIN);
            loginEnumList.add(CDMEnum.ERROR_REQUEST_USER_EMAIL_IS_INVALID);
        }
        if (CDMUtility.blankOrNullUserPassword(loginRequest.getPassword())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_USER_PASSWORD_LOGIN);
            loginEnumList.add(CDMEnum.ERROR_REQUEST_USER_PASSWORD_BLANK_OR_NULL);
        }
        if (!loginEnumList.isEmpty()) return HttpResponse.badRequest(addMessage(FAILURE, loginEnumList));
        else {
            loginRegisterResponse = loginRegisterService.userLogin(loginRequest);

            if (Optional.ofNullable(loginRegisterResponse).isEmpty() || Optional.ofNullable(loginRegisterResponse.getData()).isEmpty() || loginRegisterResponse.getData().isEmpty()) {
                cdmLogger.logs(className, methodName, INVALID_LOGIN_CREDENTIALS);
                loginEnumList.add(CDMEnum.ERROR_REQUEST_INVALID_LOGIN_CREDENTIALS);
                return HttpResponse.badRequest(addMessage(FAILURE, loginEnumList));
            } else {
                cdmLogger.logs(className, methodName, "Login Successful");
                return HttpResponse.ok(loginRegisterResponse);
            }
        }
    }

    /**
     * Validate and Post Customer Data into postgresql
     * @param loginRegisterModel
     * @return
     */
    @ReflectiveAccess
    @Post(value = USER_REGISTER)
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = HttpResponseWrapper.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = HttpResponseWrapper.class)))
    public HttpResponse<RestApiResponse> userRegister(@Body LoginRegisterModel loginRegisterModel) {

        final String methodName = "userRegister";
        final List<CDMEnum> registerEnumList = new ArrayList<>();

        if (CDMUtility.blankOrNullUserName(loginRegisterModel.getUserName())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_USER_NAME_REGISTER);
            registerEnumList.add(CDMEnum.ERROR_REQUEST_USER_NAME_BLANK_OR_NULL);
        }
        if (CDMUtility.blankOrNullUserEmail(loginRegisterModel.getUserEmail())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_USER_EMAIL_REGISTER);
            registerEnumList.add(CDMEnum.ERROR_REQUEST_USER_EMAIL_BLANK_OR_NULL);
        } else if (CDMUtility.invalidUserEmail(loginRegisterModel.getUserEmail())) {
            cdmLogger.logs(className, methodName, INVALID_USER_EMAIL_REGISTER);
            registerEnumList.add(CDMEnum.ERROR_REQUEST_USER_EMAIL_IS_INVALID);
        } else if (loginRegisterRepository.existsByUserEmail(loginRegisterModel.getUserEmail())) {
            cdmLogger.logs(className, methodName, ALREADY_EXISTS_USER_EMAIL_REGISTER);
            registerEnumList.add(CDMEnum.ERROR_REQUEST_USER_EMAIL_IS_ALREADY_EXISTS);
        }
        if (CDMUtility.blankOrNullUserPassword(loginRegisterModel.getPassword())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_USER_PASSWORD_REGISTER);
            registerEnumList.add(CDMEnum.ERROR_REQUEST_USER_PASSWORD_BLANK_OR_NULL);
        }
        if (!registerEnumList.isEmpty()) return HttpResponse.badRequest(addMessage(FAILURE, registerEnumList));
        else {
            LoginRegisterResponse loginRegisterResponse = new LoginRegisterResponse();
            List<UserDetails> userDetailsList = new ArrayList<>();
            UserDetails userDetails = new UserDetails();
            userDetails.setUserId(String.valueOf(loginRegisterModel.getUserId()));
            userDetails.setUserName(loginRegisterModel.getUserName());
            userDetails.setUserEmail(loginRegisterModel.getUserEmail());
            userDetails.setPassword(loginRegisterModel.getPassword());
            userDetailsList.add(userDetails);
            loginRegisterResponse.setStatus(SUCCESS);
            loginRegisterResponse.setData(userDetailsList);

            loginRegisterServiceImpl.postUserRegister(loginRegisterModel);
            cdmLogger.logs(className, methodName, "User data validated and added successfully");
            return HttpResponse.ok(loginRegisterResponse);
        }
    }
}